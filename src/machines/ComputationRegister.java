package machines;

import java.util.Arrays;
import java.util.LinkedList;

public class ComputationRegister {
    private final LinkedList<String> money = new LinkedList<>(Arrays.asList("α", "β", "γ"));
    private  final LinkedList<String> items = new LinkedList<>(Arrays.asList("N", "F", "K", "S"));
    private  LinkedList<String> tape;
    //new variables related to dispensing items
    public int moneyEntered = 0;
    public int change = 0;
    public LinkedList<String> requestedItems =new LinkedList<>();
    public int flag = 0;

    public ComputationRegister(LinkedList<String> tape) {
        this.tape = tape;
    }

    public void sumMoney()
    {
        LinkedList<String> copy = new LinkedList<>(tape);
        for(String s : copy)
        {
            if(money.contains(s)) {
                switch (s) {
                    case "α":
                        moneyEntered += 5;
                        break;
                    case "β":
                        moneyEntered += 10;
                        break;
                    case "γ":
                        moneyEntered += 20;
                        break;
                    default:
                        System.out.println("Value not defined. Start over.\n");//throw new IllegalStateException("Unexpected value: " + s);
                }
            }
        }
        System.out.println("Money Available: " + moneyEntered);
    }
    public void verify(){
        LinkedList<String> tapeCopy = new LinkedList<>(tape);
        for(String s : tapeCopy) {
            if(money.contains(s)) {
                tapeCopy.set(tapeCopy.indexOf(s),"1");
            }else {
                //this is to separate items from coins while verifying.
                // To be used in fulfilling request in  order they came
                requestedItems.add(s);
                tapeCopy.set(tapeCopy.indexOf(s),"0");

            }
        }


        //aabyfnfa
        //11110001
        //ensuring both a coin and item is entered
        if(tapeCopy.get(0)=="1" && tapeCopy.get(tapeCopy.size()-1)!= "0")
        {
            System.out.println("Invalid output. Please restart and select item last this time.\n");
        }
        else if(tapeCopy.get(0)=="1" && tapeCopy.get(tapeCopy.size()-1)== "0")
        {
            System.out.println("CHECKING BODY OF STRING\n");

            for(int i = 0; i< tapeCopy.size(); i++) {
                int j = i + 1;
                while (j < tapeCopy.size())
                {
                    if (tapeCopy.get(i) == "0" && tapeCopy.get(j) == "1")
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
                calcCost();

              }
        }
        else
        {
            System.out.println("Invalid input. Please enter COINS first.\n");
        }
    }

    //function to calculate change after deducting items as they are entered
    //or possibly issue refund
    public void calcCost()
    {
        change = moneyEntered;
        System.out.println("List of requested items:\n");
        for(String i : requestedItems)
        {
            if(items.contains(i) )
            {
                //checks if enough money is left from what is entered to get current/next item
                    switch (i) {
                        case "N":
                            if(change >= 10)
                            {
                                change -= 10;
                                System.out.println("NAPKIN dispensed.\n");
                            }
                            else
                            {
                                System.out.println("Not enough money to get NAPKIN.\n");
                            }

                            break;
                        case "F":
                            if(change >= 15)
                            {
                                change -= 15;
                                System.out.println("FORK dispensed.\n");
                            }
                            else
                            {
                                System.out.println("Not enough money to get FORK.\n");
                            }
                            break;
                        case "S":
                            if(change >= 20)
                            {
                                change -= 20;
                                System.out.println("SPOON dispensed\n");
                            }
                            else
                            {
                                System.out.println("Not enough money to get SPOON.\n");
                            }
                            break;
                        case "K":

                            if(change >= 25)
                            {
                                change -= 25;
                                System.out.println("KNIFE dispensed.\n");
                            }
                            else
                            {
                                System.out.println("Not enough money to get FORK.\n");
                            }
                            break;
                        default:
                            System.out.println("Not enough money entered to purchase items.\n");
                    }
                }
           // System.out.println(i);
            System.out.println("Change: " + change);
        }
    }

    public LinkedList<String> getMoney() {
        return money;
    }

    public LinkedList<String> getItems() {
        return items;
    }
}

