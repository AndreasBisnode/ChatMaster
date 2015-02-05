package common.messagebucket.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.messagebucket.message.ChatMessage;
import common.messagebucket.message.Id;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2014-12-28.
 */
public class ChatMessageRepositoryTest {
    private final ChatMessageRepository chatMessageRepository = new ChatMessageRepositoryMap();

    @Test
    public void retrieveMessages() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getResourceAsStream("/message.json");
        InputStream resourceAsStream2 = getClass().getResourceAsStream("/message2.json");
        InputStream resourceAsStream3 = getClass().getResourceAsStream("/message3.json");

        JsonNode originalJson = mapper.readTree(resourceAsStream);
        JsonNode originalJson2 = mapper.readTree(resourceAsStream2);
        JsonNode originalJson3 = mapper.readTree(resourceAsStream3);

        ChatMessage latestMessage = mapper.readValue(originalJson.toString(), ChatMessage.class);
        ChatMessage middleMessage = mapper.readValue(originalJson2.toString(), ChatMessage.class);
        ChatMessage oldestMessage = mapper.readValue(originalJson3.toString(), ChatMessage.class);


        chatMessageRepository.save(middleMessage);
        chatMessageRepository.save(latestMessage);
        chatMessageRepository.save(oldestMessage);



        List<ChatMessage> chatMessageListParticipants = (List<ChatMessage>) chatMessageRepository.retrieveMessages(new Id("id-of-the-sender"), new Id("id-of-the-recipient"));
        assertEquals(chatMessageListParticipants.get(0), latestMessage);
        assertEquals(chatMessageListParticipants.get(1), middleMessage);

        List<ChatMessage> chatMessageChannel = (List<ChatMessage>) chatMessageRepository.retrieveMessages(new Id("channelId_3"));
        assertEquals(chatMessageChannel.get(0), oldestMessage);

        assertEquals(chatMessageRepository.retrieveMessageById(new Id("messageId3")),oldestMessage);


    }
}
