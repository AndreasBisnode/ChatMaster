package messagebucket.message;

import java.util.Objects;

/**
 * Created by andgra on 2015-01-23.
 */
public class Content {
    private final Text text;
    private final Timestamp timestamp;

    public Content(String text, Timestamp dateTime) {
        this.text = new Text(text);
        this.timestamp = dateTime;

    }

    public Text text() {
        return this.text;
    }

    public Timestamp timestamp() {
        return this.timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Content) {
            final Content other = (Content) obj;
            return Objects.equals(text, other.text) &&
                    Objects.equals(timestamp, other.timestamp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, timestamp);
    }

}
