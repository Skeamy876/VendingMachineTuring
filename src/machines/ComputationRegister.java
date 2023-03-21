package machines;

import java.util.Arrays;
import java.util.LinkedList;

public class ComputationRegister {
    private final LinkedList<String> money = new LinkedList<>(Arrays.asList("α", "β", "γ"));
    private  final LinkedList<String> items = new LinkedList<>(Arrays.asList("N", "F", "K", "S"));
    private  LinkedList<String> tape;

    public ComputationRegister(LinkedList<String> tape) {
        this.tape = tape;
    }

    public void verify(){
        LinkedList<String> tapeCopy = new LinkedList<>(tape);
        for(String s : tape) {
            if(money.contains(s)) {
                tape.set(tape.indexOf(s),"1");
            }else {
                tape.set(tape.indexOf(s),"0");
            }
        }

        int flag = 0;
        //aabyfnf
        //11110001

        if (tape.get(0)!="0"){
            for (int i = 1; i < tape.size(); i++) {
                for (int j = i+1; j < tape.size()-1; j++) {
                    if (tape.get(i)=="0" && tape.get(j)=="1") {
                        flag = -1; //invalid
                        break;
                    }else {
                        flag = 1; //valid
                    }
                    break;
                }
                break;
            }
        }

        if (flag == 1){
            System.out.println("VaLID INPUT");
        }else{
            System.out.println("NOT Valid INPUT");
        }
    }

    public LinkedList<String> getMoney() {
        return money;
    }

    public LinkedList<String> getItems() {
        return items;
    }
}

