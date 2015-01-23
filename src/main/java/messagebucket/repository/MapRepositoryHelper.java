package messagebucket.repository;

import messagebucket.message.ChatMessage;
import messagebucket.message.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andgra on 2015-01-23.
 */
public class MapRepositoryHelper {
    private final Map<String, ChatMessage> chatMessageMap;

    public MapRepositoryHelper(Map<String, ChatMessage> chatMessageMap) {

        this.chatMessageMap = chatMessageMap;
    }
    // Vad sägs om protected här
    protected List<ChatMessage> retrieveMessagesFromRepository(Optional<String> senderId, String receieverId) {
        List<ChatMessage> messageList= new ArrayList<ChatMessage>();
        for (ChatMessage chatMessage: chatMessageMap.values()){
            messageList = addMessagesToList(messageList, chatMessage, senderId, receieverId);
        }
        return messageList;
    }

    private List<ChatMessage> addMessagesToList(List<ChatMessage> messageList, ChatMessage chatMessage, Optional<String> senderId, String recieverId) {
        Id from = chatMessage.from();
        Id to = chatMessage.to();

        String chatmessageFromId = from.id();
        String chatmessageToId = to.id();

        if (senderId.isPresent() && chatmessageToId.equals(recieverId) && chatmessageFromId.equals(senderId.get())){
                messageList.add(chatMessage);
        }
        if (!senderId.isPresent() && chatmessageToId.equals(recieverId)){
                messageList.add(chatMessage);
        }
        return messageList;
    }
}
