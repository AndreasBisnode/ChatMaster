package messagebucket.message;

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
}
