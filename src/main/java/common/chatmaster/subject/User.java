package common.chatmaster.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andgra on 2015-01-26.
 */
public final class User extends Subject{
    private final String email;
    private final List<Channel> subscriptions;

    public User(String id, String name, String email) {
        super(id, name);
        this.email = email;
        this.subscriptions = new ArrayList<Channel>();
    }
    public String email(){
        return this.email;
    }
    public Channel subscribe(Channel channel){
        subscriptions.add(channel);
        return channel;
    }
    public Channel unsubscribe(Channel channel){
        subscriptions.remove(channel);
        return channel;
    }
}
