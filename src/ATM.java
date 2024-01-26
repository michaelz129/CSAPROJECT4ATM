import java.util.Scanner;
public class ATM {
    private Customer customer;
    private Account savingAccount;
    private Account checkingAccount;
    private TransactionHistory history;
    private int SCount;
    public static final Scanner SCANNER = new Scanner(System.in);
    public ATM(){
        customer = null;
        savingAccount=null;
        checkingAccount=null;
        history = new TransactionHistory();
        SCount = 0;
    }
    public void start(){
        System.out.println("Welcome to Scammer ATM \nEnter your name: ");
        String name = SCANNER.nextLine();
        int pin=0;
        while(String.valueOf(pin).length()!=4){
            System.out.println("Enter 4 number that's going to be your pin");
            pin = SCANNER.nextInt();
            SCANNER.nextLine();
            ConsoleUtility.clearScreen();
        }

        customer = new Customer(name, pin);
        savingAccount = new Account("saving", 0, customer, history);
        checkingAccount = new Account("checking", 0, customer, history);
        mainMenu();
    }
    public void mainMenu(){
        int choice = 0;
        while(choice!=7){
            System.out.print("Enter your pin: ");
            int pin = SCANNER.nextInt();
            ConsoleUtility.clearScreen();
            while(pin != customer.getPin()){
                System.out.println("Incorrect pin, try again");
                pin = SCANNER.nextInt();
                ConsoleUtility.clearScreen();
            }
            System.out.println(ConsoleUtility.PURPLE +"Main Menu: \n1. Withdraw money \n2. Deposit money \n3. Transfer money between accounts \n4. Get account balance \n5. Get transaction history \n6. Change PIN \n7.Exit"+ConsoleUtility.RESET);
            choice = SCANNER.nextInt();
            SCANNER.nextLine();
            ConsoleUtility.clearScreen();
            if(choice == 1){
                int accountNum = accountChoices("w");
                if (accountNum == 1){
                    savingAccount.withdraw();
                }
                else if(accountNum == 2){
                    checkingAccount.withdraw();
                }
            }
            else if(choice == 2){
                int accountNum = accountChoices("d");
                if (accountNum == 1){
                    savingAccount.deposit();
                }
                else if(accountNum == 2){
                    checkingAccount.deposit();
                }
            }
            else if(choice == 3){
                System.out.println("Which account would you like to transfer from? \n1.Saving \n2.Checking?");
                int choice2 = SCANNER.nextInt();
                System.out.println(ConsoleUtility.GREEN+"How much would you like to transfer?"+ConsoleUtility.RESET);
                double amount = SCANNER.nextDouble();
                amount = ConsoleUtility.roundToHundredth(amount);
                if (choice2==1){
                    savingAccount.transferMoneyInto(checkingAccount, amount);
                }
                else if(choice2==2){
                    checkingAccount.transferMoneyInto(savingAccount, amount);
                }
            }
            else if(choice == 4){
                ConsoleUtility.clearScreen();
                System.out.println("Saving Account Balances: " + savingAccount.getBalance());
                System.out.println("Checking Account Balance: " + checkingAccount.getBalance());
                String transaction =  "Transcation ID: " + history.IDCreator(SCount,"S") + "\nChecked Balances  " + "\n------------------------------------";
                history.addTransaction(transaction);
                SCount++;
            }
            else if(choice == 5){
                String transaction =  "Transcation ID: " + history.IDCreator(SCount,"S") + "\nChecked History  " + "\n------------------------------------";
                history.addTransaction(transaction);
                SCount++;
                history.printTransactionHistory();
            }
            else if(choice == 6){
                int newPin=0;
                while(String.valueOf(newPin).length()!=4) {
                    System.out.println("Ente123r 4 number that's going to be your new pin");
                    newPin = SCANNER.nextInt();
                    customer.setPin(newPin);
                    SCANNER.nextLine();
                    ConsoleUtility.clearScreen();
                }
                String transaction =  "Transcation ID: " + history.IDCreator(SCount,"S") + "\nPin Updated  " + "\n------------------------------------";
                history.addTransaction(transaction);
                SCount++;
            }       else if(choice==7){
                System.out.println("Thank you for using Scammer ATM");
            }

        }



    }

    public int accountChoices(String type){
        String test ="";
        if (type.equals("w")){
            test = "withdraw from";
        }
        else if(type.equals("d")){
            test="deposit to";
        }
        System.out.println("Which account would "+ test +": \n1. Saving Account \n2. Checking Account");
        int choice = SCANNER.nextInt();
        return choice;
    }


}