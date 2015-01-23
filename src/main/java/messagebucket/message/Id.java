package messagebucket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id id1 = (Id) o;

        if (id != null ? !id.equals(id1.id) : id1.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
