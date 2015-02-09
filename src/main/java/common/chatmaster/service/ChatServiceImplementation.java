package common.chatmaster.service;

import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.message.ChatMessage;
import common.message.Id;
import common.messagebucket.repository.ChatMessageRepository;

import java.util.*;

/**
 * Created by andgra on 2015-01-29.
 */
public class ChatServiceImplementation implements ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatAdminService chatAdminService;

    public ChatServiceImplementation(ChatMessageRepository chatMessageRepository, ChatAdminService chatAdminService){
        this.chatMessageRepository = chatMessageRepository;
        this.chatAdminService = chatAdminService;
    }
    @Override
    public List<ChatMessage> retriveMessages(Channel channel) {
        return (List<ChatMessage>) chatMessageRepository.retrieveMessages(channel.id());
    }

    @Override
    public List<ChatMessage> retriveMessages(User user) {
        //retrive messages to and from user
        Collection messagesToAndFromUsers = chatMessageRepository.retrieveMessages(user.id());
        Collection subscriptionmessages = getSubscritionMessages(user);

        messagesToAndFromUsers.addAll(subscriptionmessages);
        Set<ChatMessage> set = new HashSet<ChatMessage>(messagesToAndFromUsers);
        
        return (List<ChatMessage>) new ArrayList<ChatMessage>(set);
        
        
    }

    private Collection getSubscritionMessages(User user) {
        List<Channel> channels = user.subscriptions();
        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        for(Channel channel: channels){
            chatMessages.addAll(retriveMessages(channel));
        }
        return chatMessages;
    }

    @Override
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        ChatMessage returnobject = null;
       
        //endpointsExists fungerar ej måste fixas till
          if (endpointsExist(chatMessage)){
              chatMessageRepository.save(chatMessage);
              returnobject = chatMessage;
            
            }
                
            
        
        return returnobject;
    }

    private boolean endpointsExist(ChatMessage chatMessage) {
        Id sender = chatMessage.from();
        Id receiver = chatMessage.to();
        // to is a channel
        
        if(chatAdminService.retrieveSubject(sender).isPresent() || chatAdminService.retrieveChannel(sender).isPresent()){
            if(chatAdminService.retrieveSubject(receiver).isPresent() || chatAdminService.retrieveChannel(receiver).isPresent()){
                return true;
            }
        }
        return false;

    }
}
