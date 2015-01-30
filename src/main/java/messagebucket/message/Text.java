package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by andgra on 2015-01-14.
 */
public class Text{

    private final String text;

    @JsonCreator
    public Text(@JsonProperty("id") String text){
        this.text = Objects.requireNonNull(text);
    }

    @JsonProperty("text")
    public String text() {
        return text;
    }
    public String toString(){
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Text){

            final Text text1 = (Text) o;
            return Objects.equals(this.text(), text());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
