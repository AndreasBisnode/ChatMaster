package message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andgra on 2014-12-21.
 */
public class Timestamp {
    private final String dateTime;
    public Timestamp(@JsonProperty("dateTime") String dateTime){
        this.dateTime = dateTime;

    }
    public String toString(){
        return dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }
}
