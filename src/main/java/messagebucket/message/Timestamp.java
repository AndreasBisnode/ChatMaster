package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

/**
 * Created by andgra on 2014-12-21.
 */
public class Timestamp {
    private final ZonedDateTime dateTime;
    public Timestamp(@JsonProperty("dateTime") String dateTime){
        this.dateTime = ZonedDateTime.parse(dateTime);
    }

    public String toString(){
        return dateTime.toString();
    }

    @JsonProperty("dateTime")
    public String zonedDateTime() {
        return dateTime.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timestamp timestamp = (Timestamp) o;

        if (dateTime != null ? !dateTime.equals(timestamp.dateTime) : timestamp.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return dateTime != null ? dateTime.hashCode() : 0;
    }

}
