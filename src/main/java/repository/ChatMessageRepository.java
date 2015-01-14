package repository;

import message.ChatMessage;
import message.Id;

import java.util.List;

/**
 * Created by andgra on 2014-12-28.
 */
public interface ChatMessageRepository {
    void save(ChatMessage chatMessage);

    ChatMessage retrieveMessageById(String id);

    List<ChatMessage> retrieveMessagesByParticipants(String senderId, String recipientId);

    List<ChatMessage> retrieveMessagesByChannel(String channelId);
}
