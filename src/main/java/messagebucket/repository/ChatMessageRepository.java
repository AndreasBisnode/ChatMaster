package messagebucket.repository;

import messagebucket.message.ChatMessage;
import messagebucket.message.Id;

import java.util.Collection;
import java.util.List;

/**
 * Created by andgra on 2014-12-28.
 */
public interface ChatMessageRepository {
    void save(ChatMessage chatMessage);

    ChatMessage retrieveMessageById(String id);

    Collection<ChatMessage> retrieveMessages(String senderId, String recipientId);

    Collection<ChatMessage> retrieveMessages(String channelId);
}
