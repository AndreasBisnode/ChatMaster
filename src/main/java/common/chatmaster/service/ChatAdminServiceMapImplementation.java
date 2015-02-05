package common.chatmaster.service;

import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.messagebucket.message.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andgra on 2015-01-28.
 */
public class ChatAdminServiceMapImplementation implements ChatAdminService {
    private final Map<String, Subject> subjectMap = new HashMap<>();

    @Override
    public Optional<Subject> addUser(User user) {
        return addSubjectToMap(user);
    }

    @Override
    public Optional<Subject> addChannel(Channel channel) {
        return addSubjectToMap(channel);

    }
    @Override
    public Optional<User> retrieveUser(Id userId) {
        return Optional.ofNullable((User)subjectMap.get(userId.id()));
    }

    @Override
    public Optional<Channel> retrieveChannel(Channel channel) {
        Id id = channel.id();
        return Optional.ofNullable((Channel)subjectMap.get(id.id()));
    }

    @Override
    // Nästan samma kod i denna och nästa metod. Hur kan man slå ihop dem?
    public Optional<Channel> subscribeChannel(User user,Channel channel) {
        Optional<Channel> returnObject = Optional.ofNullable(null);
        Optional<Channel> channelInDb = retrieveChannel(channel);
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
        Optional<Channel> channelInDb = retrieveChannel(channel);
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

    private Optional<Subject> addSubjectToMap(Subject subject){
        String id = subject.id().toString();
        Optional<Subject> returnobject = Optional.ofNullable(null);
        if (!subjectMap.containsKey(id)){
            subjectMap.put(id,subject);
            returnobject = Optional.ofNullable(subject);
        }
        return returnobject;
    }
}
