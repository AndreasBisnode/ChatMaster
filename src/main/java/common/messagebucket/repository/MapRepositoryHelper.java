package common.messagebucket.repository;

import common.messagebucket.message.ChatMessage;
import common.messagebucket.message.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andgra on 2015-01-23.
 */
final class MapRepositoryHelper {

    private MapRepositoryHelper(){

    }
    static List<ChatMessage> retrieveMessagesFromRepository(final Map<String, ChatMessage> chatMessageMap, Optional<Id> senderId, Id receieverId) {
        List<ChatMessage> messageList= new ArrayList<>();
        for (ChatMessage chatMessage: chatMessageMap.values()){
            messageList = addMessagesToList(messageList, chatMessage, senderId, receieverId);
        }
        return messageList;
    }

    private static List<ChatMessage> addMessagesToList(List<ChatMessage> messageList, ChatMessage chatMessage, Optional<Id> senderId, Id recieverId) {
        Id from = chatMessage.from();
        Id to = chatMessage.to();

        if (messageIsMeantForDialog(senderId) && to.equals(recieverId) && from.equals(senderId.get())){
                messageList.add(chatMessage);
        }

        if (messageIsMeantForAllMessage(senderId) && (to.equals(recieverId) || from.equals(recieverId))){
                messageList.add(chatMessage);
        }
        return messageList;
    }

    private static boolean messageIsMeantForDialog(Optional<Id> senderId) {
        return senderId.isPresent();
    }
    private static boolean messageIsMeantForAllMessage(Optional<Id> senderId) {
        return !senderId.isPresent();
    }
}
