package machines;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<String> actions = new ArrayList<String>();
    private long till;
    private int change;

    private boolean isOwner = false;



    public Result(List<String> actions, long till, int change) {
        this.actions = actions;
        this.till = till;
        this.change = change;
    }

    public Result() {
    }

    public void addActions(String action){
        this.actions.add(action);
    }
    public long getTill() {
        return till;
    }

    public void setTill(long till) {
        this.till = till;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        for (String element : actions){
            return element + "\nChange: "+change;
        }
        return "No actions";
    }
}
