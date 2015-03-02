package common.chatmaster.service;

import common.chatmaster.subject.Channel;
import common.chatmaster.subject.User;
import common.message.ChatMessage;

import java.util.List;

/**
 * Created by andgra on 2015-01-29.
 */
public interface ChatService {
    public List<ChatMessage> retrieveMessages(Channel channel);
    public List<ChatMessage> retrieveMessages(User user);
    public ChatMessage sendMessage(ChatMessage chatMessage);

}
