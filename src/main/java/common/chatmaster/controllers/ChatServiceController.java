package common.chatmaster.controllers;

import common.chatmaster.service.ChatAdminService;
import common.chatmaster.service.ChatService;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.message.ChatMessage;
import common.message.Id;
import common.message.Text;
import common.message.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2015-06-26.
 */

@RestController
@Service
public class ChatServiceController {

    private final ChatService chatService;
    private final ChatAdminService chatAdminService;

    @Autowired
    public ChatServiceController(ChatService chatService, ChatAdminService chatAdminService) {
        this.chatService = chatService;
        this.chatAdminService = chatAdminService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(ResourceNotFoundException e) {
        //logger.error(e);
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    public List<ChatMessage> retrieveMessages(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<User> user = chatAdminService.retrieveUser(new Id(id));
        Optional<Channel> channel = chatAdminService.retrieveChannel(new Id(id));

        if (user.isPresent()) {
            return chatService.retrieveMessages(user.get());
        }

        if (channel.isPresent()) {
            return chatService.retrieveMessages(channel.get());
        } else throw new ResourceNotFoundException("Unable to find subject with id " + id);

    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ChatMessage sendMessage(final String text,
                                   final String from,
                                   final String to) {
        ChatMessage chatMessage = new ChatMessage(text, new Id(from), new Id(to));
        return chatService.sendMessage(chatMessage);
    }
}
