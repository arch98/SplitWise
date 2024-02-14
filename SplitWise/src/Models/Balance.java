package Models;

public class Balance {
    private final String currency;
    private final double amount;

    public Balance(String currency,double amount){
        this.currency = currency;
        this.amount = amount;
    }

    public  double getAmount(){
        return amount;
    }


}
