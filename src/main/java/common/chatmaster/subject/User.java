package common.chatmaster.subject;

import common.message.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andgra on 2015-01-26.
 */
public final class User extends Subject{
    private final String email;
    private final List<Channel> subscriptions;

    public User(final Id id, final String name, final String email) {
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
    public List<Channel> subscriptions(){
        return subscriptions;
    }

    public List<Channel> getSubscriptions() {
        return subscriptions;
    }

    public String getEmail() {

        return email;
    }
}
