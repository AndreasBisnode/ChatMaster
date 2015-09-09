package common.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by andgra on 2014-12-21.
 */
public class Timestamp {
    private final ZonedDateTime dateTime;
    public Timestamp(){
        this.dateTime = ZonedDateTime.now();
    }
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
        if(o instanceof Timestamp){

            final Timestamp zonedDateTime1 = (Timestamp) o;
            return Objects.equals(this.zonedDateTime(), zonedDateTime1.zonedDateTime());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

}
