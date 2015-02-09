package common.messagebucket.repository;

import common.message.ChatMessage;
import common.message.Id;
import common.message.Timestamp;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by andgra on 2014-12-28.
 */
public class ChatMessageRepositoryMap implements ChatMessageRepository {
    private final Map<String, ChatMessage> chatMessageMap = new HashMap<>();
    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageMap.put(chatMessage.id().toString(), chatMessage);
    }

    @Override
    public ChatMessage retrieveMessageById(Id id) {
        return chatMessageMap.get(id.id());
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(Id channelId) {
        return retrieveMessages(null, channelId);
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(Id senderId, Id recipientId) {
        List <ChatMessage> messages = MapRepositoryHelper.retrieveMessagesFromRepository(chatMessageMap, Optional.ofNullable(senderId), recipientId);
        Collections.sort(messages, new Comparator<ChatMessage>() {
            @Override
            public int compare(ChatMessage o1, ChatMessage o2) {
                Timestamp thisTimeStamp = o1.timestamp();
                Timestamp thatTimeStamp = o2.timestamp();
                String thisZonedDateTimeString = thisTimeStamp.zonedDateTime();
                String thatZonedDateTimeString = thatTimeStamp.zonedDateTime();
                ZonedDateTime thisZonedDatetime = ZonedDateTime.parse(thisZonedDateTimeString);
                ZonedDateTime thatZonedDatetime = ZonedDateTime.parse(thatZonedDateTimeString);
                return (thisZonedDatetime.compareTo(thatZonedDatetime));
            }
        });
        Collections.reverse(messages);
        return messages;
    }
}
