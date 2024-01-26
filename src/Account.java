import java.io.Console;
import java.net.ConnectException;
import java.util.Scanner;
public class Account{

    private String type;
    private double balance;
    private Customer customer;
    private int ACount;
    private TransactionHistory history;
    public static final Scanner SCANNER = new Scanner(System.in);


    public Account(String type, double balance, Customer customer, TransactionHistory history){
        this.type = type;
        this.balance=balance;
        this.customer=customer;
        ACount=0;
        this.history = history;
    }

    public void deposit(){
        despoitOrWithdraw("deposit");
        ACount++;
    }

    public void withdraw(){
        despoitOrWithdraw("withdraw");
        ACount++;
    }

    public void transferMoneyInto(Account accountTo,double amount){
        System.out.println("Transferring....");
        try{
            Thread.sleep(500);
            ConsoleUtility.clearScreen();
        } catch(Exception e){
            System.out.println("Error");
        }
        ConsoleUtility.coolDotThing();
        if(this.balance>=amount){
            this.balance-=amount;
            accountTo.balance+=amount;
            String transcation = "Transcation ID: " + history.IDCreator(ACount,"A") + "\nTransfered " +amount+" from "+ConsoleUtility.Uppercase(this.getType())+" to " + ConsoleUtility.Uppercase(accountTo.getType()) + "account\n------------------------------------";
            history.addTransaction(transcation);
            ACount++;
            System.out.println("Successfully transfered "+amount+" from "+this.getType()+" to " + accountTo.getType());
        }
        else{
            String transcation = "Transcation ID: " + history.IDCreator(ACount,"A") + "\nFailed to transfered " +amount+" from "+this.getType()+" to " + accountTo.getType() + "due to insufficent fund\n------------------------------------";
            history.addTransaction(transcation);
            ACount++;
            System.out.println("Failed to transfered "+amount+" from "+this.getType()+" to " + accountTo.getType() +"due to insufficent fund");
        }

    }

    /* Withdraw and Despoit has the same foundation code */
    public void despoitOrWithdraw(String DOW){
        String transaction="";
        System.out.println(ConsoleUtility.CYAN +"How much would you like to "+DOW+"?");;
        double amount = SCANNER.nextDouble();
        amount = ConsoleUtility.roundToHundredth(amount);
        SCANNER.nextLine();
        System.out.println(ConsoleUtility.Uppercase(DOW) + "ing...");
        try{
            Thread.sleep(500);
            ConsoleUtility.clearScreen();
        } catch(Exception e){
            System.out.println("Error");
        }
        ConsoleUtility.coolDotThing();
        if (DOW.equals("deposit")){
            balance += amount;
            transaction = "Transcation ID: " + history.IDCreator(ACount,"A") +  "\nAccount Type:" + ConsoleUtility.Uppercase(this.type) +"\nDeposited " +amount+"\n------------------------------------";
            history.addTransaction(transaction);
        }
        else if (DOW.equals("withdraw")){
            if(balance-amount<0){
                System.out.println("You do not have enough money to withdraw that amount.");
                transaction = "Transcation ID: " + history.IDCreator(ACount,"A") + "\nAccount Type:" + ConsoleUtility.Uppercase(this.type) +"\nWithdrawal failed due to insufficent fund \n------------------------------------";
                history.addTransaction(transaction);
            }
            else if(amount%5!=0){
                System.out.println("Next time when withdrawing an amount withdraw an amount that's a multiple of 5.");
                transaction = "Transcation ID: " + history.IDCreator(ACount,"A") + "\nAccount Type:" + ConsoleUtility.Uppercase(this.type) +"\nWithdrawal failed due to withdrawing money that's not a multiple of 5 \n------------------------------------";
                history.addTransaction(transaction);
            }
            else{
                balance -= amount;
                transaction = "Transcation ID: " + history.IDCreator(ACount,"A") +"\nAccount Type:" + ConsoleUtility.Uppercase(this.type) + "\nWithdrawn " +amount+"\n------------------------------------";
                history.addTransaction(transaction);
            }
        }
        System.out.println(ConsoleUtility.Uppercase(DOW) +" complete!,your new balance is: " + balance+ConsoleUtility.RESET);
    }
    public double getBalance(){
        return balance;
    }
    public String getType(){
        return type;
    }

}


