package common.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Objects;

/**
 * Created by andgra on 2014-12-19.
 */
public class ChatMessage {
    private final Id id;
    private final Text text;
    private final Timestamp dateTime;
    private final Id from;
    private final Id to;

    @JsonCreator
    public ChatMessage(
            @JsonProperty("id") final String id,
            @JsonProperty("text") final String text,
            @JsonProperty("timestamp") final Timestamp dateTime,
            @JsonProperty("from")  final Id from,
            @JsonProperty("to") final Id to) {
        this.id = new Id(id);
        this.text = new Text(text);
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
    }
    //@Todo ska jag ha två constructorer?
    public ChatMessage(
            final String text,
            final Id from,
            final Id to) {
        this.text = new Text(text);
        this.dateTime = new Timestamp();
        this.id = new Id(this.dateTime.toString());
        this.from = from;
        this.to = to;
    }


    @JsonUnwrapped  @JsonProperty("id")
    public Id id() {
        return this.id;
    }

    @JsonUnwrapped @JsonProperty("text")
    public Text text() {
        return this.text;
    }

    @JsonProperty("timestamp")
    public Timestamp timestamp() {
        return this.dateTime;
    }


    @JsonProperty("from")
    public Id from() {
        return this.from;
    }

    @JsonProperty("to")
    public Id to() {
        return this.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateTime, from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatMessage) {
            final ChatMessage other = (ChatMessage) obj;
            return Objects.equals(id, other.id) &&
                    Objects.equals(text, other.text)&&
                    Objects.equals(dateTime, other.dateTime)&&
                    Objects.equals(from, other.from)&&
                    Objects.equals(to, other.to);
        }
        return false;
    }
}

