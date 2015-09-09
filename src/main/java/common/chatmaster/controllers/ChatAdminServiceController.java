
package common.chatmaster.controllers;

import common.chatmaster.exception.ResourceAlreadyExistsException;
import common.chatmaster.service.ChatAdminService;
import common.chatmaster.subject.Channel;
import common.chatmaster.subject.Subject;
import common.chatmaster.subject.User;
import common.message.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Created by andgra on 2015-06-26.
 */



@RestController
@Service
public class ChatAdminServiceController {

    private ChatAdminService chatAdminService;

    @Autowired
    public ChatAdminServiceController(ChatAdminService chatAdminService){
        this.chatAdminService = chatAdminService;
    }

    // Skicka tillbaka meddelande
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public void handleError(ResourceAlreadyExistsException e) {
        //Logga felmeddelande

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Subject createUser(String id,String name,String email) throws ResourceAlreadyExistsException {
        User user = new User(new Id(id), name, email);
        try {
            return chatAdminService.addUser(user).get();
        } catch (ResourceAlreadyExistsException e) {
            throw new ResourceAlreadyExistsException(e.getMessage());
        }
    }

    @RequestMapping(value = "/channels", method = RequestMethod.POST)
    @ResponseBody
    public Subject createChannel(String id,String name) throws ResourceAlreadyExistsException {
        Channel channel = new Channel(new Id(id), name);
        try {
            return chatAdminService.addChannel(channel).get();
        } catch (ResourceAlreadyExistsException e) {
            throw new ResourceAlreadyExistsException(e.getMessage());
        }
    }

    //Skicka tillbaka rätt meddelande
    @RequestMapping(value = "/users/{userId}/subscribe/{channelId}", method = RequestMethod.PUT)
    @ResponseBody
    public Channel subscribeChannel(@PathVariable("userId") String userId, @PathVariable("channelId")String channelId)  {
        Optional<User> user = chatAdminService.retrieveUser(new Id(userId));
        Optional<Channel> channel = chatAdminService.retrieveChannel(new Id(channelId));

           return chatAdminService.subscribeChannel(user.get(), channel.get()).get();
    }

    //Skicka tillbaka rätt meddelande
    @RequestMapping(value = "/users/{userId}/unsubscribe/{channelId}", method = RequestMethod.PUT)
    @ResponseBody
    public Channel unsubscribeChannel(@PathVariable("userId") String userId, @PathVariable("channelId")String channelId)  {
        Optional<User> user = chatAdminService.retrieveUser(new Id(userId));
        Optional<Channel> channel = chatAdminService.retrieveChannel(new Id(channelId));

        return chatAdminService.unsubscribeChannel(user.get(), channel.get()).get();
    }
}


