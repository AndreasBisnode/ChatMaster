package common.chatmaster.service.Impl;

import common.chatmaster.exception.ResourceAlreadyExistsException;
import common.chatmaster.service.ChatAdminService;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.message.Id;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andgra on 2015-01-28.
 */
@Controller
public class ChatAdminServiceMapImpl implements ChatAdminService {
    private final Map<String, Subject> subjectMap = new HashMap<>();

    @Override
    public Optional<Subject> addUser(User user) throws ResourceAlreadyExistsException {
        return addSubjectToMap(user);
    }

    @Override
    public Optional<Subject> addChannel(Channel channel) throws ResourceAlreadyExistsException {
        return addSubjectToMap(channel);

    }
    @Override
    public Optional<User> retrieveUser(Id userId) {
        try {
            return Optional.ofNullable((User)subjectMap.get(userId.id()));
        }catch (ClassCastException e){
            return Optional.ofNullable(null);
        }
        
    }

    @Override
    public Optional<Channel> retrieveChannel(Id channelId) {
        return Optional.ofNullable((Channel)subjectMap.get(channelId.id()));
    }

    @Override
    public Optional<Channel> subscribeChannel(User user, Channel channel) {
        Optional<Channel> returnObject = Optional.ofNullable(null);
        Optional<Channel> channelInDb = retrieveChannel(channel.id());
        Optional<User> userInDb = retrieveUser(user.id());

        if (itemsExistsInDB(userInDb, channelInDb)){
            userInDb.get().subscribe(channelInDb.get());
            returnObject = channelInDb;
        }
        return returnObject;
    }

    @Override
    public Optional<Channel> unsubscribeChannel(User user, Channel channel) {
        Optional<Channel> returnObject = Optional.ofNullable(null);
        Optional<Channel> channelInDb = retrieveChannel(channel.id());
        Optional<User> userInDb = retrieveUser(user.id());

        if (itemsExistsInDB(userInDb, channelInDb)){
            userInDb.get().unsubscribe(channelInDb.get());
            returnObject = channelInDb;
        }
        return returnObject;
    }

    private boolean itemsExistsInDB(Optional<User> user, Optional<Channel> channel) {
        return user.isPresent() && channel.isPresent();
    }

    //Item already exists
    private Optional<Subject> addSubjectToMap(Subject subject) throws ResourceAlreadyExistsException {
        Id id = subject.id();
        String key = id.toString();
        Optional<Subject> returnobject = Optional.ofNullable(null);
        if (subjectMap.containsKey(key)){
            throw new ResourceAlreadyExistsException("A subject with this Id allready exists!");
        }
        if (!subjectMap.containsKey(key)){
            subjectMap.put(key,subject);
            returnobject = Optional.ofNullable(subject);
        }
        return returnobject;
    }
}
