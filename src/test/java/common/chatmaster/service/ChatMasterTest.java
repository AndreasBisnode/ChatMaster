package common.chatmaster.service;

import common.chatmaster.subject.Channel;
import common.chatmaster.subject.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.message.ChatMessage;
import common.message.Id;
import common.messagebucket.repository.ChatMessageRepositoryMap;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by andgra on 2015-01-28.
 */
public class ChatMasterTest {
    User user1;
    User user2;
    ChatAdminService chatAdminService;
    Channel channel1;
    Channel channel2;
    @Before
    public void setup(){
        chatAdminService = new ChatAdminServiceMapImplementation();
        user1 = new User(new Id("id 1"),"user1","user@mail");
        user2 = new User(new Id("id 3"),"user2","user@mail");
        channel1 = new Channel(new Id("id 2"),"channel1");
        channel2 = new Channel(new Id("id 4"),"channel2");

    }
    @Test
    public void ChatAdminServiceTest(){


        assertEquals(user1, chatAdminService.addUser(user1).get());
        assertEquals(false, chatAdminService.addUser(user1).isPresent());

        assertEquals(channel1, chatAdminService.addChannel(channel1).get());
        assertEquals(false, chatAdminService.addChannel(channel1).isPresent());

        assertEquals(user1, chatAdminService.retrieveUser(user1.id()).get());
        assertEquals(false, chatAdminService.retrieveUser(user2.id()).isPresent());

        assertEquals(channel1, chatAdminService.retrieveChannel(channel1.id()).get());
        assertEquals(false, chatAdminService.retrieveChannel(channel2.id()).isPresent());

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

         resourceAsStream = getClass().getResourceAsStream("/message4.json");
         originalJson = mapper.readTree(resourceAsStream);
         ChatMessage chatMessageWNoEndpoint = mapper.readValue(originalJson.toString(), ChatMessage.class);
         resourceAsStream = getClass().getResourceAsStream("/message5.json");
         originalJson = mapper.readTree(resourceAsStream);
         ChatMessage chatMessage5 = mapper.readValue(originalJson.toString(), ChatMessage.class);

        chatAdminService.addUser(new User(new Id("id-of-the-sender"), "d", "d"));
        chatAdminService.addUser(new User(new Id("id-of-the-recipient"), "d", "d"));

        ChatService chatService = new ChatServiceImplementation(new ChatMessageRepositoryMap(), chatAdminService);

        assertEquals(chatMessage, chatService.sendMessage(chatMessage));
        assertEquals(chatMessage5, chatService.sendMessage(chatMessage5));

        assertEquals(null, chatService.sendMessage(chatMessageWNoEndpoint));



        assertEquals(chatService.retrieveMessages(new User(new Id("id-of-the-sender"), "dsd", "sdfdf")).size(), 2);
    }
}
