package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by andgra on 2014-12-19.
 */
public class Id{
    private final String id;


    @JsonCreator
    public Id(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String toString(){
        return id;
    }

    @JsonProperty("id")
    public String id(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Id){

            final Id id1 = (Id) o;
            return Objects.equals(this.id(), id1.id());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
