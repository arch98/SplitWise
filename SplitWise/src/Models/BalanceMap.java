package Models;

import java.util.HashMap;
import java.util.Map;

public class BalanceMap {
    // User => Amount mapping.
    private final Map<User,Balance> balances;

    public  BalanceMap(){
        balances = new HashMap<User,Balance>();
    }

    public Map<User,Balance> getbalances(){
        return balances;
    }

}
