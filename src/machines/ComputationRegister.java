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
        //aabyfnfa
        //11110001
        //ensuring both a coin and item is entered
        if(tape.get(0)=="1" && tape.get(tape.size()-1)!= "0")
        {
            System.out.println("Invalid output. Please restart and select item last this time.\n");
        }
        else if(tape.get(0)=="1" && tape.get(tape.size()-1)== "0")
        {
            System.out.println("CHECKING BODY OF STRING\n");

            for(int i = 0; i< tape.size(); i++) {
                int j = i + 1;
                while (j < tape.size())
                {
                    if (tape.get(i) == "0" && tape.get(j) == "1")
                    {
                        System.out.println("Coin entered after items. This is an invalid input.\n");
                        flag = -1;
                        break;
                    }
                    else
                    {
                        flag = 1;
                    }
                    j++;
                }

                if (flag == -1) {
                    System.out.println("Invalid input found. Aborting request.\n");
                    break;
                }

            }
             if (flag == 1)
             {
                System.out.println("Valid input. Processing request.\n");
              }
        }
        else
        {
            System.out.println("Invalid input. Please enter COINS first.\n");
        }
    }
    public LinkedList<String> getMoney() {
        return money;
    }

    public LinkedList<String> getItems() {
        return items;
    }
}

