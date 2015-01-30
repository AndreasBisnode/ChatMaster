package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by andgra on 2014-12-19.
 */
public class ChatMessage {
    private final ContentsAndId contentsAndId;
    private final Endpoints endpoints;

    @JsonCreator
    public ChatMessage(
            @JsonProperty("id") final String id,
            @JsonProperty("text") final String text,
            @JsonProperty("timestamp") final Timestamp dateTime,
            final Endpoints endpoints/*
            @JsonProperty("from") final Id from,
            @JsonProperty("to") final Id to*/) {
        this.contentsAndId = new ContentsAndId(id, text, dateTime);
        //this.endpoints = new Endpoints(from, to);
        this.endpoints =endpoints;
    }

    @JsonUnwrapped  @JsonProperty("id")
    public Id id() {
        return contentsAndId.Id();
    }

    @JsonUnwrapped @JsonProperty("text")
    public Text text() {
        return contentsAndId.text();
    }

    @JsonProperty("timestamp")
    public Timestamp timestamp() {
        return contentsAndId.timestamp();
    }

    /*
    @JsonProperty("from")
    public Id from() {
        return endpoints.from();
    }

    @JsonProperty("to")
    public Id to() {
        return endpoints.to();
    }
    */

    @JsonUnwrapped
    public Endpoints getEndpoints() {
        return endpoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentsAndId, endpoints);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatMessage) {
            final ChatMessage other = (ChatMessage) obj;
            return Objects.equals(contentsAndId, other.contentsAndId) &&
                    Objects.equals(endpoints, other.endpoints);
        }
        return false;
    }
}

