public class Customer{
    private String name;
    private int pin;
    private TransactionHistory history;
    public Customer(String name, int pin){
        this.name = name;
        this.pin = pin;
    }
    public String getName(){
        return name;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int newpin){
        pin = newpin;
    }


}