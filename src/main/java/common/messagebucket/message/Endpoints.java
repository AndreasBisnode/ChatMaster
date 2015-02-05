/*
package common.messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;




public class Endpoints {
    private final Id from;
    private final Id to;

    @JsonCreator
    public Endpoints(@JsonProperty("from")final Id from,@JsonProperty("to") final Id to) {
        this.from = from;
        this.to = to;
    }
    @JsonProperty("from")
    public Id from(){
        return this.from;
    }

    @JsonProperty("to")
    public Id to(){
        return to;
    }

 @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Endpoints) {
            final Endpoints other = (Endpoints) obj;
            return Objects.equals(from, other.from) &&
                    Objects.equals(from, other.from);
        }
        return false;
    }

}
*/
