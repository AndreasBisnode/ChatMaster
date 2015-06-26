package common.messagebucket.repository;

import common.message.ChatMessage;
import common.message.Id;
import common.message.Timestamp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by andgra on 2014-12-28.
 */

@Repository
public class ChatMessageRepositoryMap implements ChatMessageRepository {
    private final Map<String, ChatMessage> chatMessageMap = new HashMap<>();
    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageMap.put(chatMessage.id().toString(), chatMessage);
    }

    @Override
    public final ChatMessage retrieveMessageById(final Id id) {
        return chatMessageMap.get(id.id());
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(final Id channelId) {
        return retrieveMessages(null, channelId);
    }

    @Override
    public Collection<ChatMessage> retrieveMessages(final Id senderId, final Id recipientId) {
        final List <ChatMessage> messages = MapRepositoryHelper.retrieveMessagesFromRepository(chatMessageMap, Optional.ofNullable(senderId), recipientId);
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
