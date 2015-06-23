package common.chatmaster.service.Impl;

import common.chatmaster.service.ChatAdminService;
import common.chatmaster.service.ChatService;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.User;
import common.message.ChatMessage;
import common.message.Id;
import common.message.Timestamp;
import common.messagebucket.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by andgra on 2015-01-29.
 */
@Component
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatAdminService chatAdminService;


    @Autowired
    public ChatServiceImpl(ChatMessageRepository chatMessageRepository, ChatAdminService chatAdminService){
        this.chatMessageRepository = chatMessageRepository;
        this.chatAdminService = chatAdminService;
    }
    @Override
    public List<ChatMessage> retrieveMessages(Channel channel) {
        return (List<ChatMessage>) chatMessageRepository.retrieveMessages(channel.id());
    }

    @Override
    public List<ChatMessage> retrieveMessages(User user) {
        Collection<ChatMessage> dialogMessages = chatMessageRepository.retrieveMessages(user.id());
        Collection<ChatMessage> subscriptionMessages = getSubscritionMessages(user);
        
        dialogMessages.addAll(subscriptionMessages);
        Set<ChatMessage> noDuplicates = new HashSet<ChatMessage>(dialogMessages);
        List<ChatMessage> messages = new ArrayList<ChatMessage>(noDuplicates);

        messages.sort(new Comparator<ChatMessage>() {
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
        }.reversed());
        return messages;
    }

    private Collection<ChatMessage> getSubscritionMessages(User user) {
        List<Channel> channels = user.subscriptions();
        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        for(Channel channel: channels){
            chatMessages.addAll(retrieveMessages(channel));
        }
        return chatMessages;
    }

    @Override
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        ChatMessage returnobject = null;
        
          if (endpointsExist(chatMessage)){
              chatMessageRepository.save(chatMessage);
              returnobject = chatMessage;
            }
        
        return returnobject;
    }

    private boolean endpointsExist(ChatMessage chatMessage) {
        Id sender = chatMessage.from();
        Id receiver = chatMessage.to();
       
        if(senderIsPresent(sender) && receiverIsPresent(receiver)){
            return true;
        }
        return false;
    }

    private boolean receiverIsPresent(Id receiver) {
        return chatAdminService.retrieveUser(receiver).isPresent() || chatAdminService.retrieveChannel(receiver).isPresent();
    }

    private boolean senderIsPresent(Id sender) {
        return chatAdminService.retrieveUser(sender).isPresent() || chatAdminService.retrieveChannel(sender).isPresent();
    }
}
