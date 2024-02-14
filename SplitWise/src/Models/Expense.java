package Models;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Expense {

    private final BalanceMap userBalances;
    private final String title;
    private final String description;


    public  Expense(BalanceMap userBalances,String title,String description){
        this.userBalances = userBalances;
        this.title = title;
        this.description = description;
    }

    public BalanceMap getUserBalances(){
        return userBalances;
    }

}
