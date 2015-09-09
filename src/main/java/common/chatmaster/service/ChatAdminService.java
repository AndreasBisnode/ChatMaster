package common.chatmaster.service;

import common.chatmaster.exception.ResourceAlreadyExistsException;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.message.Id;

import java.util.Optional;

/**
 * Created by andgra on 2015-01-28.
 */
public interface ChatAdminService {
    Optional<Subject> addUser(User user) throws ResourceAlreadyExistsException;
    Optional<Subject> addChannel(Channel channel) throws ResourceAlreadyExistsException;
    Optional<User> retrieveUser(Id userId);
    Optional<Channel> retrieveChannel(Id channelId);
    Optional<Channel> subscribeChannel(User user, Channel channel);
    Optional<Channel> unsubscribeChannel(User user, Channel channel);

}
