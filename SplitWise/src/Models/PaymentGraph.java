package Models;

import java.util.HashMap;
import java.util.Map;

public class PaymentGraph {
    // User -> User, Amount
    private final Map<User,BalanceMap> allPayments;

    public PaymentGraph(Map<User,BalanceMap>graph){
        allPayments = graph;
    }


}
