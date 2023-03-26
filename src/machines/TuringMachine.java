package machines;

import views.VendingMachine;

import java.util.Arrays;
import java.util.LinkedList;

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
