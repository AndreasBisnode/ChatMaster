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
final class MapRepositoryHelper {
    //Gör statisk metoder

    private MapRepositoryHelper(){

    }
    static List<ChatMessage> retrieveMessagesFromRepository(final Map<String, ChatMessage> chatMessageMap, Optional<String> senderId, String receieverId) {
        List<ChatMessage> messageList= new ArrayList<>();
        for (ChatMessage chatMessage: chatMessageMap.values()){
            messageList = addMessagesToList(messageList, chatMessage, senderId, receieverId);
        }
        return messageList;
    }

    private static List<ChatMessage> addMessagesToList(List<ChatMessage> messageList, ChatMessage chatMessage, Optional<String> senderId, String recieverId) {
        Id from = chatMessage.getEndpoints().from();
        Id to = chatMessage.getEndpoints().to();

        String chatmessageFromId = from.id();
        String chatmessageToId = to.id();


        if (messageIsMeantForDialog(senderId) && chatmessageToId.equals(recieverId) && chatmessageFromId.equals(senderId.get())){
                messageList.add(chatMessage);
        }

        if (messageIsMeantForChannel(senderId) && chatmessageToId.equals(recieverId)){
                messageList.add(chatMessage);
        }
        return messageList;
    }

    private static boolean messageIsMeantForDialog(Optional<String> senderId) {
        return senderId.isPresent();
    }
    private static boolean messageIsMeantForChannel(Optional<String> senderId) {
        return !senderId.isPresent();
    }
}
