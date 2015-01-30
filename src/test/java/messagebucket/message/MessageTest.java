package messagebucket.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2014-12-19.
 */
public class MessageTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void roundtripMessageTest() throws IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/message.json");
        JsonNode originalJson = mapper.readTree(resourceAsStream);
        ChatMessage chatMessage = mapper.readValue(originalJson.toString(), ChatMessage.class);

        assertEquals(chatMessage.id().toString(), "some-generated-message-id");
        assertEquals(chatMessage.text().toString(), "This is a message");
        assertEquals(chatMessage.timestamp().toString(), "2014-12-12T06:21:06.879+01:00[Europe/Stockholm]");
        assertEquals(chatMessage.getEndpoints().from().toString(), "id-of-the-sender");
        assertEquals(chatMessage.getEndpoints().to().toString(), "id-of-the-recipient");

        String serializedJson = mapper.writeValueAsString(chatMessage);
        JsonNode deSerializedJson = mapper.readTree(serializedJson);
        assertEquals(originalJson, deSerializedJson);

    }
    @Test
    public void compareToTest() throws IOException {
        InputStream resourceAsStreamLate = getClass().getResourceAsStream("/message2.json");
        InputStream resourceAsStreamEarly = getClass().getResourceAsStream("/message3.json");
        JsonNode originalJsonLate = mapper.readTree(resourceAsStreamLate);
        JsonNode originalJsonEarly = mapper.readTree(resourceAsStreamEarly);
        ChatMessage chatMessageLate = mapper.readValue(originalJsonLate.toString(), ChatMessage.class);
        ChatMessage chatMessageEarly = mapper.readValue(originalJsonEarly.toString(), ChatMessage.class);

     /*   assert(chatMessageEarly.compareTo(chatMessageLate) == -1);
        assert(chatMessageLate.compareTo(chatMessageEarly) == 1);
        assert(chatMessageEarly.compareTo(chatMessageEarly) == 0);*/

        List<ChatMessage> list = new ArrayList<ChatMessage>();
        list.add(chatMessageLate);
        list.add(chatMessageEarly);
        assertEquals(list.get(0),chatMessageLate);
      //  Collections.sort(list);
//        assertEquals(list.get(0), chatMessageEarly);
    }
    @Test
    public void equalMessagesTest() throws IOException{
        ChatMessage chatMessage1;
        ChatMessage chatMessage2;
        try(InputStream resourceAsStream = getClass().getResourceAsStream("/message.json")){
            chatMessage1 = mapper.readValue(resourceAsStream, ChatMessage.class);
        }
        try(InputStream resourceAsStream = getClass().getResourceAsStream("/message.json")){
            chatMessage2 = mapper.readValue(resourceAsStream, ChatMessage.class);
        }
        assertEquals(chatMessage1, chatMessage2);
    }

}

