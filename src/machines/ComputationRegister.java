package machines;

import views.VendingMachine;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ComputationRegister {
    private final LinkedList<String> money = new LinkedList<>(Arrays.asList("α", "β", "γ"));
    private  final LinkedList<String> items = new LinkedList<>(Arrays.asList("N", "F", "K", "S"));
    private  LinkedList<String> tape;
    public int moneyEntered = 0;//stores money entered per use
    public int till =0;//stores total money entered into vending machine
    public int change = 0;
    public LinkedList<String> requestedItems =new LinkedList<>();//list to identify and separate requested items in each order
    public int flag = 0;
    public static  CounterRegister napkinCounter = new CounterRegister("Napkin",VendingMachine.napkinCount);
    public static CounterRegister forkCounter = new CounterRegister("Fork",VendingMachine.forkCount);
    public static CounterRegister knifeCounter = new CounterRegister("Knife",VendingMachine.knifeCount);
    public static CounterRegister spoonCounter = new CounterRegister("Spoon",VendingMachine.spoonCount);

    private Result result = new Result();


    public ComputationRegister(LinkedList<String> tape) {

        this.tape = tape;
        this.sumMoney();


    }

    private void sumMoney()
    {
        LinkedList<String> TapeCopy = new LinkedList<>(tape);
        for(String s : TapeCopy)
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
                        Object[] options = { "TRY AGAIN" };
                        JOptionPane.showOptionDialog(null,"Invalid input please try again",
                                "Input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,
                                null,options,options[0]);
                }
            }
        }
        till+=moneyEntered;
    }
    //function to verify overall string input
    public Result verify(){
        for (String tapeElement :  this.tape){
        }
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
            Object[] options = { "TRY AGAIN" };
            JOptionPane.showOptionDialog(null,"Invalid output. Please restart and select item last this time.\n",
                    "Input Output", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,
                    null,options,options[0]);
            //if invalid request, reduce till by value entered then reset value entered
            till-=moneyEntered;
            moneyEntered = 0;
        }
        else if(tapeCopy.get(0)=="1" && tapeCopy.get(tapeCopy.size()-1)== "0")
        {

            for(int i = 0; i< tapeCopy.size(); i++) {
                int j = i + 1;
                while (j < tapeCopy.size())
                {
                    if (tapeCopy.get(i) == "0" && tapeCopy.get(j) == "1")
                    {
                        //if invalid request, reduce till by value entered then reset value entered
                        till-=moneyEntered;
                        moneyEntered =0;
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
                    //if invalid request, reduce till by value entered then reset value entered
                    till-=moneyEntered;
                    moneyEntered=0;
                    break;
                }

            }
             if (flag == 1)
             {
               result= this.calcCost();

              }
        }
        /*verifying user input
        need to implement registers to reset till and each item count
        */
        else if (tape.get(0).equals("N"))
        {
            boolean flag= false;
                //call function to verify owner
            result.addActions("Verifying Owner...");
            flag = verifyOwner(tape);

            if (flag == true){
                result.setOwner(true);

            }else {
                result.setOwner(false);
                JOptionPane.showMessageDialog(null,"Invalid Owner. Please try again");
            }
        }
        else
        {
            result.addActions("Invalid input. Please enter COINS first.");
        }
        return result;
    }

    /*function to calculate change after deducting items as they are entered
    or possibly issue refund*/
    private Result calcCost()
    {
        change = moneyEntered;
        for(String i : requestedItems)
        {
            if(items.contains(i) )
            {
                //checks if enough money is left from what is entered to get current/next item
                    switch (i) {
                        case "N":
                            if(change >= 10 && VendingMachine.napkinCount>0)
                            {
                                change -= 10;
                                result.addActions("NAPKIN dispensed");
                                int count = napkinCounter.decrement();
                                VendingMachine.updateNapkinCount(count);
                            }
                            else if(VendingMachine.napkinCount==0){
                                result.addActions("NAPKIN out of stock");

                            }else {
                                result.addActions("Not enough money to get NAPKIN");
                            }

                            break;
                        case "F":
                            if(change >= 15 && VendingMachine.forkCount>0)
                            {
                                change -= 15;
                                result.addActions("FORK dispensed");
                                int count = forkCounter.decrement();
                                VendingMachine.updateForkCount(count);                            }
                            else if(VendingMachine.forkCount==0) {
                                result.addActions("FORK out of stock");
                            }else{
                                result.addActions("Not enough money to get FORK");
                            }
                            break;
                        case "S":
                            if(change >= 20 && VendingMachine.spoonCount>0)
                            {
                                change -= 20;
                                result.addActions("SPOON dispensed");
                                int count = spoonCounter.decrement();
                                VendingMachine.updateSpoonCount(count);                            }
                            else if(VendingMachine.spoonCount==0) {
                                result.addActions("SPOON out of stock");
                            }else{
                                result.addActions("Not enough money to get SPOON");
                            }
                            break;
                        case "K":

                            if(change >= 25 && VendingMachine.knifeCount>0)
                            {
                                change -= 25;
                                result.addActions("KNIFE dispensed");
                                int count = knifeCounter.decrement();
                                VendingMachine.updateKnifeCount(count);
                            }
                            else if(VendingMachine.knifeCount==0) {
                                result.addActions("KNIFE out of stock");
                            }else {
                                result.addActions("Not enough money to get FORK");
                            }
                            break;
                        default:
                            result.addActions("Not enough money entered to purchase items");
                    }
                }
        }
        till-=change;
        result.setChange(change);
        result.setTill(till);

        return result;
    }

    public boolean verifyOwner(LinkedList<String> tape)
    {
        LinkedList<String> ownerString = new LinkedList<>(List.of("N","K","S","F","F","S","K","S"));
        if(tape.equals(ownerString))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public LinkedList<String> getMoney() {
        return money;
    }

    public LinkedList<String> getItems() {
        return items;
    }
}

