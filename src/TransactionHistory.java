import java.util.ArrayList;
public class TransactionHistory{
    private ArrayList <String>TransactionHistorys;

    public TransactionHistory(){
        TransactionHistorys = new ArrayList<>();
        TransactionHistorys.add(ConsoleUtility.RED+"Transaction History:\n------------------------------------");
    }
    public void addTransaction(String transaction){
        TransactionHistorys.add(transaction);
    }

    public void printTransactionHistory(){
        for(String transaction:TransactionHistorys){
            System.out.println(transaction);
        }
    }
    public String IDCreator(int count,String type){
        String ID = "";
        String zero = "";
        if(count<10){
            zero+="000";
        }
        else if(count<100){
            zero+="00";
        }
        else if(count<1000){
            zero+="0";
        }

        if (type.equals("A")){
            ID = "A"+zero+count;
        }
        else if (type.equals("S")){
            ID = "S"+zero+count;
        }
        return ID;
    }
}

