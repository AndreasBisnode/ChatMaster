package repository;

import message.ChatMessage;
import message.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andgra on 2014-12-28.
 */
public class ChatMessageRepositoryMap implements ChatMessageRepository {
    private Map<String, ChatMessage> chatMessageMap = new HashMap<String, ChatMessage>();
    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageMap.put(chatMessage.getId().toString(), chatMessage);
    }

    @Override
    public ChatMessage retrieveMessageById(String id) {
        return chatMessageMap.get(id);
    }

    @Override
    public List<ChatMessage> retrieveMessagesByParticipants(String senderId, String recipientId) {
        List<ChatMessage> unsortedMessages = getMessagesFromRepositoryByParticipants(senderId, recipientId);
        return unsortedMessages;
    }

    @Override
    //@Tododenna metod ej klar. måste fråga tommy om datetime. Vad bör användas?
    public List<ChatMessage> retrieveMessagesByChannel(String channelId) {
        List<ChatMessage> unsortedChannelMessages = getMessagesFromRepositoryByChannel(channelId);
        return unsortedChannelMessages;
    }
    private List<ChatMessage> getMessagesFromRepositoryByParticipants(String senderId, String recipientId) {
        List<ChatMessage> participantMessages= new ArrayList<ChatMessage>();
        for (ChatMessage chatMessage: chatMessageMap.values()){
            if(chatMessage.getFrom().getId().equals(senderId) && chatMessage.getTo().getId().equals(recipientId)){
                participantMessages.add(chatMessage);
            }
        }
        return participantMessages;
    }

    private List<ChatMessage> getMessagesFromRepositoryByChannel(String channelId){
        List<ChatMessage> channelMessages= new ArrayList<ChatMessage>();
        for (ChatMessage chatMessage: chatMessageMap.values()){
            if(chatMessage.getTo().getId().equals(channelId)){
                channelMessages.add(chatMessage);
            }
        }
        return channelMessages;
    }


}
