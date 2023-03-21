package machines;

import java.util.Arrays;
import java.util.LinkedList;

public class TuringMachine {
    private LinkedList<String> inputString;
    private final String language [] = {"α", "β", "γ", "N", "K", "F", "S"};
    private final String meaningOfLanguage [] = {"5", "10", "20", "Napkin", "Knife", "Fork", "Spoon"};

    public TuringMachine(LinkedList<String> tape) {
        this.inputString = tape;

        LinkedList<String> money = new LinkedList<>(Arrays.asList("α", "β", "γ"));
        LinkedList<String> items = new LinkedList<>(Arrays.asList("N", "F", "K", "S"));

        //copy of tape
        LinkedList<String> tapeCopy = new LinkedList<>();

        for(String s : tape) {
            if(money.contains(s)) {
                tapeCopy.add(s);
                tape.set(tape.indexOf(s), "$");
            }

        }
        for(String s : tape) {
            System.out.println("tape"+ s);
        }





//        int moneyIndex = -1;
//        int itemsIndex = -1;
//        for (String s : tape) {
//            if (moneyIndex == -1 && money.contains(s)) {
//                moneyIndex = money.indexOf(s);
//            }
//            if (itemsIndex == -1 && items.contains(s)) {
//                itemsIndex = items.indexOf(s);
//            }
//            if (moneyIndex != -1 && itemsIndex != -1) {
//                break;
//            }
////        }
//
//        if (moneyIndex != -1 && itemsIndex != -1 && moneyIndex < itemsIndex) {
//            System.out.println("Money comes before items");
//
//        } else {
//            System.out.println("Money does not come before items");
//        }


    }










    public String runResult() {

        return "invalid input";
    }
}
