package repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import message.ChatMessage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2014-12-28.
 */
public class ChatMessageRepositoryTest {
    ChatMessageRepository chatMessageRepository = new ChatMessageRepositoryMap();
    ObjectMapper mapper = new ObjectMapper();
    ChatMessage chatMessage1;
    ChatMessage chatMessage2;
    ChatMessage chatMessage3;
    @Before
    public void saveThreeChatMessages() throws IOException {
        //save chatmessage
        InputStream resourceAsStream = getClass().getResourceAsStream("/message.json");
        chatMessage1 = mapper.readValue(resourceAsStream, ChatMessage.class);
        chatMessageRepository.save(chatMessage1);

        resourceAsStream = getClass().getResourceAsStream("/message2.json");
        chatMessage2 = mapper.readValue(resourceAsStream, ChatMessage.class);
        chatMessageRepository.save(chatMessage2);

        resourceAsStream = getClass().getResourceAsStream("/message3.json");
        chatMessage3 = mapper.readValue(resourceAsStream, ChatMessage.class);
        chatMessageRepository.save(chatMessage3);
    }
    @Test
    public void retrieveMessages(){
        //retrieve chatmessage
        ChatMessage chatMessageRetrieved1 = chatMessageRepository.retrieveMessageById("messageId3");
        assertEquals(chatMessageRetrieved1, chatMessage3);

        List<ChatMessage> chatMessageListRetrieved3 = chatMessageRepository.retrieveMessagesByChannel("channelId_3");
        assertEquals(chatMessageListRetrieved3.get(0),chatMessage3);

        List<ChatMessage> chatMessageListParticipants = chatMessageRepository.retrieveMessagesByParticipants("id-of-the-sender", "channelId_3");
        assertEquals(chatMessageListParticipants.get(0),chatMessage3);
    }
}
