package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

/**
 * Created by andgra on 2015-01-23.
 */
public class ChatMessageComparable extends ChatMessage implements Comparable<ChatMessage> {

    public ChatMessageComparable(@JsonProperty("id") String id, @JsonProperty("text") String text, @JsonProperty("timestamp") Timestamp dateTime, @JsonProperty("from") Id from, @JsonProperty("to") Id to) {
        super(id, text, dateTime, from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (!this.from().equals(that.from())) return false;
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
