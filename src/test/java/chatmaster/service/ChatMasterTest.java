package chatmaster.service;

import chatmaster.subject.Channel;
import chatmaster.subject.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import messagebucket.message.ChatMessage;
import messagebucket.repository.ChatMessageRepositoryMap;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by andgra on 2015-01-28.
 */
public class ChatMasterTest {
    @Test
    public void ChatAdminServiceTest(){
        ChatAdminService chatAdminService = new ChatAdminServiceMapImplementation();
        User user1 = new User("id 1","user1","user@mail");
        User user2 = new User("id 3","user2","user@mail");
        Channel channel1 = new Channel("id 2","channel1");
        Channel channel2 = new Channel("id 4","channel2");

        assertEquals(user1, chatAdminService.addUser(user1).get());
        assertEquals(false, chatAdminService.addUser(user1).isPresent());

        assertEquals(channel1, chatAdminService.addChannel(channel1).get());
        assertEquals(false, chatAdminService.addChannel(channel1).isPresent());

        assertEquals(user1, chatAdminService.retrieveUser(user1).get());
        assertEquals(false, chatAdminService.retrieveUser(user2).isPresent());

        assertEquals(channel1, chatAdminService.retrieveChannel(channel1).get());
        assertEquals(false, chatAdminService.retrieveChannel(channel2).isPresent());

        assertEquals(channel1, chatAdminService.subscribeChannel(user1, channel1).get());
        assertEquals(false, chatAdminService.subscribeChannel(user1, channel2).isPresent());
        assertEquals(false, chatAdminService.subscribeChannel(user2, channel1).isPresent());

        assertEquals(channel1, chatAdminService.unsubscribeChannel(user1, channel1).get());
        assertEquals(false, chatAdminService.unsubscribeChannel(user1, channel2).isPresent());


    }
    @Test
    public void ChatServiceTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getResourceAsStream("/message.json");
        JsonNode originalJson = mapper.readTree(resourceAsStream);
        ChatMessage chatMessage = mapper.readValue(originalJson.toString(), ChatMessage.class);

        ChatService chatService = new ChatServiceImplementation(new ChatMessageRepositoryMap());

        assertEquals(chatMessage, chatService.sendMessage(chatMessage));
        assertEquals(null, chatService.sendMessage(chatMessage));
    }
}
