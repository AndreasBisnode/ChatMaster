package common.chatmaster.subject;

import common.message.Id;

/**
 * Created by andgra on 2015-02-05.
 */
public class SubjectFactory {
    public static User createUser(String name, String email){
        return new User(new Id(name),name, email);
    }
    public static Channel createChannel(String name){
        return new Channel(new Id(name),name);
    }
}
