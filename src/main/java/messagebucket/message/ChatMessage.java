package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.ZonedDateTime;

/**
 * Created by andgra on 2014-12-19.
 */
public class ChatMessage implements Comparable<ChatMessage>{
    private final ContentsAndId contentsAndId;
    private final Endpoints endpoints;

    @JsonCreator
    public ChatMessage(
            @JsonProperty("id") final String id,
            @JsonProperty("text") final String text,
            @JsonProperty("timestamp") final Timestamp dateTime,
            @JsonProperty("from") final Id from,
            @JsonProperty("to") final Id to) {
        this.contentsAndId = new ContentsAndId(id, text, dateTime);
        this.endpoints = new Endpoints(from, to);
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

    @JsonProperty("from")
    public Id from() {
        return endpoints.from();
    }

    @JsonProperty("to")
    public Id to() {
        return endpoints.to();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (!from().equals(that.from())) return false;
        if (!id().equals(that.id())) return false;
        if (!text().equals(that.text())) return false;
        if (!timestamp().equals(that.timestamp())) return false;
        if (!to().equals(that.to())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id().hashCode();
        result = 31 * result + text().hashCode();
        result = 31 * result + timestamp().hashCode();
        result = 31 * result + from().hashCode();
        result = 31 * result + to().hashCode();
        return result;
    }

    public int compareTo(ChatMessage other) {
        Timestamp thisTimeStamp = this.timestamp();
        Timestamp thatTimeStamp = other.timestamp();

        String thisZonedDateTimeString = thisTimeStamp.zonedDateTime();
        String thatZonedDateTimeString = thatTimeStamp.zonedDateTime();

        ZonedDateTime thisZonedDatetime = ZonedDateTime.parse(thisZonedDateTimeString);
        ZonedDateTime thatZonedDatetime = ZonedDateTime.parse(thatZonedDateTimeString);

        return (thisZonedDatetime.compareTo(thatZonedDatetime));
    }
}

