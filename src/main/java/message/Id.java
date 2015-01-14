package message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

/**
 * Created by andgra on 2014-12-19.
 */
public class Id implements Serializable{
    String id;


    @JsonCreator
    public Id(String id) {
        this.id = id;
    }
    @JsonCreator
    public Id(@JsonProperty("id")Id id) {
        this.id = id.toString();
    }
    public String toString(){
        return id;
    }
    @JsonUnwrapped
    public String getId(){
        return id;
    }
}
