package messagebucket.message;

/**
 * Created by andgra on 2015-01-23.
 */
public class Endpoints {
    private final Id from;
    private final Id to;

    public Endpoints(Id from, Id to) {
        this.from = from;
        this.to = to;
    }
    public Id from(){
        return from;
    }
    public Id to(){
        return to;
    }
}
