package common.chatmaster.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.AppConfig;
import common.chatmaster.exception.ResourceAlreadyExistsException;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.SubjectFactory;
import common.chatmaster.subject.User;
import common.message.ChatMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2015-02-05.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})

@Controller
public class MainTest {

    User knatte;
    User fnatte;
    User tjatte;
    User kalle;
    User kajsa;
    //Channels
    Channel all;
    Channel children;



    @Autowired
    private ChatAdminService chatAdminService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void initSubjects(){
        knatte = SubjectFactory.createUser("knatte", "knatte@mail.com");
        fnatte = SubjectFactory.createUser("fnatte", "fnatte@mail.com");
        tjatte = SubjectFactory.createUser("tjnatte", "tjatte@mail.com");
        kalle = SubjectFactory.createUser("kalle", "kalle@mail.com");
        kajsa = SubjectFactory.createUser("kajsa", "kajsa@mail.com");
        //Channels
        all = SubjectFactory.createChannel("all");
        children = SubjectFactory.createChannel("children");

    }

    @Test
    public void mainTest() throws IOException, ResourceAlreadyExistsException {
        InputStream resourceAsStream;
        JsonNode originalJson;


        
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

        Assert.assertEquals(chatService.retrieveMessages(knatte).size(), 4);
        Assert.assertEquals(chatService.retrieveMessages(all).size(), 1);
        Assert.assertEquals(chatService.retrieveMessages(kalle).size(), 3);
        Assert.assertEquals(chatService.retrieveMessages(fnatte).size(), 2);

        Assert.assertEquals(chatService.retrieveMessages(tjatte).size(), 2);
        chatAdminService.unsubscribeChannel(tjatte, all);
        Assert.assertEquals(chatService.retrieveMessages(tjatte).size(), 1);
        
        
    }
}
