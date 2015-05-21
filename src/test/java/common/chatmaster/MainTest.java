package common.chatmaster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.chatmaster.service.ChatAdminService;
import common.chatmaster.service.ChatService;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.SubjectFactory;
import common.chatmaster.subject.User;
import common.message.ChatMessage;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2015-02-05.
 */
public class MainTest {
    @Test
    public void mainTest() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        ChatAdminService chatAdminService = (ChatAdminService) applicationContext.getBean("chatAdminService");
        ChatService chatService = (ChatService) applicationContext.getBean("chatService");
        ObjectMapper mapper = (ObjectMapper) applicationContext.getBean("objectMapper");


        InputStream resourceAsStream;
        JsonNode originalJson;
        
        
        //Create users
        User knatte = SubjectFactory.createUser("knatte", "knatte@mail.com");
        User fnatte = SubjectFactory.createUser("fnatte", "fnatte@mail.com");
        User tjatte = SubjectFactory.createUser("tjnatte", "tjatte@mail.com");
        User kalle = SubjectFactory.createUser("kalle", "kalle@mail.com");
        User kajsa = SubjectFactory.createUser("kajsa", "kajsa@mail.com");
        //Channels
        Channel all = SubjectFactory.createChannel("all");
        Channel children = SubjectFactory.createChannel("children");
        
        chatAdminService.addUser(knatte);
        chatAdminService.addUser(fnatte);
        chatAdminService.addUser(tjatte);
        chatAdminService.addUser(kalle);
        chatAdminService.addUser(kajsa);
        
        chatAdminService.addChannel(all);
        chatAdminService.addChannel(children);
        // subsciptions
        chatAdminService.subscribeChannel(knatte, all);
        chatAdminService.subscribeChannel(knatte, children);
        chatAdminService.subscribeChannel(fnatte, all);
        chatAdminService.subscribeChannel(fnatte, children);
        chatAdminService.subscribeChannel(tjatte, all);
        chatAdminService.subscribeChannel(tjatte, children);
        chatAdminService.subscribeChannel(kalle, all);
        chatAdminService.subscribeChannel(kajsa, all);
                
        //chatmessages
        resourceAsStream = getClass().getResourceAsStream("/mainTest/knatteToKalle.json");
        originalJson = mapper.readTree(resourceAsStream);
        ChatMessage knatteToKalle = mapper.readValue(originalJson.toString(), ChatMessage.class);
        
        resourceAsStream = getClass().getResourceAsStream("/mainTest/kalleToAll.json");
        originalJson = mapper.readTree(resourceAsStream);
        ChatMessage kalleToAll = mapper.readValue(originalJson.toString(), ChatMessage.class);
        
        resourceAsStream = getClass().getResourceAsStream("/mainTest/kalleToKnatteRespons.json");
        originalJson = mapper.readTree(resourceAsStream);
        ChatMessage kalleToKnatteRespons = mapper.readValue(originalJson.toString(), ChatMessage.class);
        
        resourceAsStream = getClass().getResourceAsStream("/mainTest/fnatteToChildren.json");
        originalJson = mapper.readTree(resourceAsStream);
        ChatMessage fnatteToChildren = mapper.readValue(originalJson.toString(), ChatMessage.class);
        
        //sendMessages
        chatService.sendMessage(knatteToKalle);
        chatService.sendMessage(kalleToAll);
        chatService.sendMessage(kalleToKnatteRespons);
        chatService.sendMessage(fnatteToChildren);

        assertEquals(chatService.retrieveMessages(knatte).size(), 4);
        assertEquals(chatService.retrieveMessages(all).size(), 1);
        assertEquals(chatService.retrieveMessages(kalle).size(), 3);
        assertEquals(chatService.retrieveMessages(fnatte).size(), 2);

        assertEquals(chatService.retrieveMessages(tjatte).size(), 2);
        chatAdminService.unsubscribeChannel(tjatte, all);
        assertEquals(chatService.retrieveMessages(tjatte).size(), 1);
        
        
    }
}
