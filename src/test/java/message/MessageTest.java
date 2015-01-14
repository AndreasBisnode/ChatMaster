package message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import message.ChatMessage;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2014-12-19.
 */
public class MessageTest {

    @Test
    public void roundtripMessageTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getResourceAsStream("/message.json");

        ChatMessage chatMessage = mapper.readValue(resourceAsStream, ChatMessage.class);
        assertEquals(chatMessage.getId().toString(), "some-generated-message-id");
        assertEquals(chatMessage.getText(), "This is a message");
        assertEquals(chatMessage.getTimestamp().toString(), "2014-12-12T06:21:06.879+01:00[Europe/Stockholm]");
        assertEquals(chatMessage.getFrom().toString(), "id-of-the-sender");
        assertEquals(chatMessage.getTo().toString(), "id-of-the-recipient");


        //JsonNode jsonNode =
        Writer strWriter = new StringWriter();
        mapper.writeValue(strWriter, chatMessage);
        String chatMessageJSON = strWriter.toString();
        System.out.println("dd"+chatMessageJSON);
        assertEquals(chatMessageJSON,"{\"id\":\"some-generated-message-id\",\"text\":\"This is a message\",\"timestamp\":{\"dateTime\":\"2014-12-12T06:21:06.879+01:00[Europe/Stockholm]\"},\"from\":{\"id\":\"id-of-the-sender\"},\"to\":{\"id\":\"id-of-the-recipient\"}}");
        JsonNode node = mapper.readTree(chatMessageJSON);
        assertEquals(node.get("id").toString(), "\"some-generated-message-id\"");
        assertEquals(node.get("text").toString(), "\"This is a message\"");
        assertEquals(node.get("timestamp").toString().trim(), "{" +
                "\"dateTime\":\"2014-12-12T06:21:06.879+01:00[Europe/Stockholm]\"" +
                "}".trim());
        assertEquals(node.get("from").toString(),
                "{\"id\":\"id-of-the-sender\"}");
        assertEquals(node.get("to").toString(),
               "{\"id\":\"id-of-the-recipient\"}");

    }
}

