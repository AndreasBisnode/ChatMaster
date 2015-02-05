package common.chatmaster.subject;

import common.messagebucket.message.Id;

/**
 * Created by andgra on 2015-01-28.
 */
public class Subject {
    // Kan vi diskutera eventuella fördelar/ nackdelar med att ha denna superklass?
    private final Id id;
    private final String name;
    public Subject(String id, String name){
        this.id = new Id(id);
        this.name = name;
    }
    public Id id(){
        return id;
    }
    public String nickname(){
        return name;
    }
}
