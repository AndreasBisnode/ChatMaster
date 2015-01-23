package messagebucket.repository;

import messagebucket.message.ChatMessage;
import messagebucket.message.Id;

import java.util.*;

/**
 * Created by andgra on 2014-12-28.
 */
public class ChatMessageRepositoryMap implements ChatMessageRepository {
    private final Map<String, ChatMessage> chatMessageMap = new HashMap<String, ChatMessage>();
    private final MapRepositoryHelper mapRepositoryHelper = new MapRepositoryHelper(chatMessageMap);
    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageMap.put(chatMessage.id().toString(), chatMessage);
    }

    @Override
    public ChatMessage retrieveMessageById(String id) {
        return chatMessageMap.get(id);
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(String senderId, String recipientId) {
        List <ChatMessage> messages = mapRepositoryHelper.retrieveMessagesFromRepository(Optional.ofNullable(senderId), recipientId);
        Collections.sort(messages);
        Collections.reverse(messages);
        return messages;
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(String channelId) {
        return retrieveMessages(null, channelId);
    }
}
