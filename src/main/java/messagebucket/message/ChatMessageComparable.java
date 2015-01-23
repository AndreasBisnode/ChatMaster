package messagebucket.message;

import java.time.ZonedDateTime;

/**
 * Created by andgra on 2015-01-23.
 */
public class ChatMessageComparable implements Comparable<ChatMessage> {
    private ChatMessage host;

    //Vad sägs om detta? Hur annars göra? inte immutable
    public void defineHost(ChatMessage host){
        this.host = host;
    }

    @Override
    public int compareTo(ChatMessage other) {
        Timestamp thisTimeStamp = host.timestamp();
        Timestamp thatTimeStamp = other.timestamp();
        String thisZonedDateTimeString = thisTimeStamp.zonedDateTime();
        String thatZonedDateTimeString = thatTimeStamp.zonedDateTime();
        ZonedDateTime thisZonedDatetime = ZonedDateTime.parse(thisZonedDateTimeString);
        ZonedDateTime thatZonedDatetime = ZonedDateTime.parse(thatZonedDateTimeString);

        return (thisZonedDatetime.compareTo(thatZonedDatetime));
    }
    //har ej implementerat one dot per line
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        if (!host.from().equals(that.from())) return false;
        if (!host.id().equals(that.id())) return false;
        if (!host.text().equals(that.text())) return false;
        if (!host.timestamp().equals(that.timestamp())) return false;
        if (!host.to().equals(that.to())) return false;
        return true;
    }
    //har ej implementerat one dot per line
    @Override
    public int hashCode() {
        int result = host.id().hashCode();
        result = 31 * result + host.text().hashCode();
        result = 31 * result + host.timestamp().hashCode();
        result = 31 * result + host.from().hashCode();
        result = 31 * result + host.to().hashCode();
        return result;
    }
}
