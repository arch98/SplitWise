package Services;

import Models.*;

import java.util.*;

public class ExpenseService {
    private final Map<String,List<Expense>> groupExpenses;

    public ExpenseService(Map<String,List<Expense>> groupExpenses){
        this.groupExpenses = groupExpenses;
    }

    public List<Expense> getGroupExpenses(String groupId){
        return groupExpenses.get(groupId);
    }

    public PaymentGraph GetPaymentGraph(BalanceMap groupBalances){
        final var graph = new HashMap<User,BalanceMap>();
        PriorityQueue<Map.Entry<User, Balance>> positiveAmounts = new PriorityQueue<>(Comparator.comparingDouble(balance -> balance.getValue().getAmount()));
        PriorityQueue<Map.Entry<User,Balance>> negativeAmounts = new PriorityQueue<>(Comparator.comparingDouble(balance -> balance.getValue().getAmount()));

        for(var balance : groupBalances.getbalances().entrySet()){
            if(balance.getValue().getAmount() > 0) {
                positiveAmounts.add(balance);
            }else{
                negativeAmounts.add(balance);
            }
        }

        while(!positiveAmounts.isEmpty() && !negativeAmounts.isEmpty()){
            final var largestPositive  = positiveAmounts.poll();
            final var largestNegative = negativeAmounts.poll();
            final var negativeAmount = -largestNegative.getValue().getAmount();
            final var postiveAmount = largestPositive.getValue().getAmount();
            graph.putIfAbsent(largestPositive.getKey(),new BalanceMap());
            graph.get(largestPositive.getKey()).getbalances().put(largestNegative.getKey(),new Balance( "USD",Math.min(postiveAmount,negativeAmount)));
            double remaining = postiveAmount - negativeAmount;
            final var remainingAmount = new Balance("USD",remaining);
            if(remaining > 0){
                positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositive.getKey(),remainingAmount));
            }else if(remaining < 0){
                negativeAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(),remainingAmount));
            }
        }

        return new PaymentGraph(graph);
    }
}
