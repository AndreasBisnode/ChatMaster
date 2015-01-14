package message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

/**
 * Created by andgra on 2014-12-19.
 */
public class ChatMessage implements Serializable {
    private Id id;
    private Text text;
    private Timestamp timestamp;
    private Id from;
    private Id to;


    @JsonCreator
    public ChatMessage(@JsonProperty("id")Id id,@JsonProperty("text") Text text, @JsonProperty("timestamp")
    Timestamp dateTime, @JsonProperty("from") Id from, @JsonProperty("to") Id to){
        this.id = id;
        this.text = text;
        this.timestamp = dateTime;
        this.from = from;
        this.to = to;

    }
    public String getId() {
        return id.getId();
    }

    public String getText() {
        return text.getText();
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
    // Frågor Hur fixa jackson utan getters och setters. Hur fungerar unwrapped
}
