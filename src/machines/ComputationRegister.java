package machines;

import java.util.Arrays;
import java.util.LinkedList;

public class ComputationRegister {
    private final LinkedList<String> money = new LinkedList<>(Arrays.asList("α", "β", "γ"));
    private  final LinkedList<String> items = new LinkedList<>(Arrays.asList("N", "F", "K", "S"));
    private  LinkedList<String> tape;
    //new variables related to dispensing items
    public int moneyEntered = 0;//stores money entered per use
    public int till =0;//stores total money entered into vending machine
    public int change = 0;
    public LinkedList<String> requestedItems =new LinkedList<>();//list to identify and separate requested items in each order
    public int flag = 0;//used for verifying input

    public ComputationRegister(LinkedList<String> tape) {
        this.tape = tape;
    }

    //function to calculate sum of money in each request
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
        till+=moneyEntered;//updating till
        System.out.println("Money Available: " + moneyEntered);
    }
    //function to verify overall string input
    public void verify(String input){
        System.out.println("input:"+input);
        LinkedList<String> tapeCopy = new LinkedList<>(tape);
        for(String s : tapeCopy) {
            if(money.contains(s)) {
                tapeCopy.set(tapeCopy.indexOf(s),"1");//if char on tape represents money, replace with 1
            }else {
                /*this is to separate items from coins while verifying.
                 To be used in fulfilling request in  order they came*/
                requestedItems.add(s);
                tapeCopy.set(tapeCopy.indexOf(s),"0");//if char on tape represents item, replace with 0

            }
        }
        //ensuring both a coin and item is entered in that order,ie coins then items
        if(tapeCopy.get(0)=="1" && tapeCopy.get(tapeCopy.size()-1)!= "0")
        {
            System.out.println("Invalid output. Please restart and select item last this time.\n");
            //if invalid request, reduce till by value entered then reset value entered
            till-=moneyEntered;
            moneyEntered = 0;
            System.out.println("Money Available: " + moneyEntered);
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
                        //if invalid request, reduce till by value entered then reset value entered
                        till-=moneyEntered;
                        moneyEntered =0;
                        System.out.println("Money Available: " + moneyEntered);
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
                    //if invalid request, reduce till by value entered then reset value entered
                    till-=moneyEntered;
                    moneyEntered=0;
                    System.out.println("Money Available: " + moneyEntered);
                    break;
                }

            }
             if (flag == 1)
             {
                System.out.println("Valid input. Processing request.\n");
                calcCost();

              }
        }
        /*verifying user input
        need to implement registers to reset till and each item count
        */
        else if (tape.get(0).equals("N"))
        {
                //call function to verify owner
                System.out.println("Verifying owner****\n");
                verifyOwner(input);
        }
        else
        {
            System.out.println("Invalid input. Please enter COINS first.\n");
        }
    }

    /*function to calculate change after deducting items as they are entered
    or possibly issue refund*/
    public void calcCost()
    {
        change = moneyEntered;
        System.out.println("List of requested items:\n");
        for(String i : requestedItems)
        {
            System.out.println(i);
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
            till-=change;
            System.out.println("Till: "+till);
            System.out.println("Change: " + change);
        }
    }

    public void verifyOwner(String input)
    {
        if(input.equals("NKSFFSKS"))
        {
            System.out.println("Welcome Owner!\n");
            System.out.println("Till at log in : $"+till);
        }
        else
        {
            System.out.println("Invalid code.Access denied.\n");
        }
    }
    public LinkedList<String> getMoney() {
        return money;
    }

    public LinkedList<String> getItems() {
        return items;
    }
}

