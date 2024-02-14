package Services;
import Models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class GroupService {
    private final ExpenseService expenseService;
    private  final Map<String, Group> groups;

    public GroupService(ExpenseService expenseService){
        this.expenseService = expenseService;
        this.groups = new HashMap<String,Group>();
    }

    public PaymentGraph getGroupPaymentGraph(String groupid, String userid) throws IllegalAccessException {
        BalanceMap resultExpense = getBalances(groupid,userid);
        return expenseService.GetPaymentGraph(resultExpense);
    }

    public BalanceMap getBalances(final String groupId, final String userid) throws IllegalAccessException{
        if(!groups.get(groupId).getUsers().contains(userid)){
            throw new IllegalAccessException();
        }
        List<Expense> GroupexpenseList = expenseService.getGroupExpenses(groupId);
        return sumofAlltheGroupExpenses(GroupexpenseList);
    }

    public BalanceMap sumofAlltheGroupExpenses(List<Expense> expenses){
        Map<User, Balance> resultBalances = new HashMap<User,Balance>();
        for(Expense expense : expenses){
            for(var balance : expense.getUserBalances().getbalances().entrySet()){
                final var user = balance.getKey();
                final var amount = balance.getValue();
                resultBalances.put(resultBalances.getOrDefault(user,null).add(amount));
            }
        }
        return null;
    }
}
