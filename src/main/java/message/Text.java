package message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by andgra on 2015-01-14.
 */
public class Text implements Serializable{
    private final String text;

    @JsonCreator
    public Text(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
