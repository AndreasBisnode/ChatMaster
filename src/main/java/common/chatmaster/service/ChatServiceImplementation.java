package common.chatmaster.service;

import common.chatmaster.subject.Channel;
import common.chatmaster.subject.User;
import common.messagebucket.message.ChatMessage;
import common.messagebucket.repository.ChatMessageRepository;

import java.util.List;
import java.util.Optional;

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
        return (List<ChatMessage>) chatMessageRepository.retrieveMessages(user.id());
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
        Optional<User> sender  = chatAdminService.retrieveUser(chatMessage.from());
        Optional<User> receiver  = chatAdminService.retrieveUser(chatMessage.to());
        if(sender.isPresent() && receiver.isPresent()){
            return true;
        }
        return false;

    }
}
