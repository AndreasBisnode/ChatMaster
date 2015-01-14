package message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

/**
 * Created by andgra on 2014-12-19.
 */
public class ChatMessage implements Serializable {
    @JsonUnwrapped private Id id;
    private String text;
    private Timestamp timestamp;
    private Id from;
    private Id to;


    @JsonCreator
    public ChatMessage(@JsonProperty("id")Id id,@JsonProperty("text") String text, @JsonProperty("timestamp")
    Timestamp dateTime, @JsonProperty("from") Id from, @JsonProperty("to") Id to){
        this.id = id;
        this.text = text;
        this.timestamp = dateTime;
        this.from = from;
        this.to = to;

    }
    @JsonUnwrapped public Id getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Id getFrom() {
        return from;
    }

    public Id getTo() {
        return to;
    }
}
