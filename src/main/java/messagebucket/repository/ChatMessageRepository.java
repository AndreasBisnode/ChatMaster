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

    ChatMessage retrieveMessageById(Id id);

    Collection<ChatMessage> retrieveMessages(Id senderId, Id recipientId);

    Collection<ChatMessage> retrieveMessages(Id channelId);
}
