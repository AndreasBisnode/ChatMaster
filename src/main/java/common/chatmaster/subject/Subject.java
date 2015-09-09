package common.chatmaster.subject;

import common.message.Id;

import java.io.Serializable;

/**
 * Created by andgra on 2015-01-28.
 */
public class Subject implements Serializable{
    private final Id id;
    private final String name;
    public Subject(final Id id,final String name){
        this.id = id;
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public Id id(){
        return id;
    }
    public String nickname(){
        return name;
    }
}
