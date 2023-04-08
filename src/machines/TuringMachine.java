package machines;

import java.util.LinkedList;

/**
 * Created on 25/01/21 12:00 pm by Authors:
 * 1. Ackeem Mclennon 1803349
 * 2. Monique Bennett 2004188
 * 3. Christian Willams 1903419

 */

public class TuringMachine {
    private LinkedList<String> inputString;
    private final String language [] = {"α", "β", "γ", "N", "K", "F", "S"};
    private final String meaningOfLanguage [] = {"5", "10", "20", "Napkin", "Knife", "Fork", "Spoon"};
    private Result result;

    public TuringMachine(LinkedList<String> inputString) {
        this.inputString = inputString;
        ComputationRegister computationRegister = new ComputationRegister(inputString);
        this.result = computationRegister.verify();
    }

    public Result runResult(){
        return result;
    }












    public long returnTill() {
            return 0;
    }
}
