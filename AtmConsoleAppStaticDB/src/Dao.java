import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {

	
	static Map<String, List<ITransaction>> transactions = new HashMap<String, List<ITransaction>>();
	static List<Account> accounts = new ArrayList<Account>();
	
	public static void withdraw(String accountId, double amount){
		List<ITransaction> relevantTransactions = transactions.get(accountId);
		ITransaction currentWithdrawal = new Withdrawal(amount);
		relevantTransactions.add(currentWithdrawal);
		transactions.put(accountId, relevantTransactions);
	}
	
	public static void deposit(String accountId, double amount){
		List<ITransaction> relevantTransactions = transactions.get(accountId);
		ITransaction currentDeposit = new Deposit(amount);
		relevantTransactions.add(currentDeposit);
		transactions.put(accountId, relevantTransactions);
	}
	
	public List<ITransaction> inquire(String accountId){
		return transactions.get(accountId);
	}
	
	public Account getAccountIdByPin(String pin){
		for (Account account : accounts){
			if (account.getPin().equals(pin)){
				return account;
			}
		}
		return null;
	}
	
	public static void init(){
		Account account1 = new Account("1234", "1234");
		Account account2 = new Account("9876", "9876");
		accounts.add(account1);
		accounts.add(account2);
		ITransaction transaction1 = new Deposit(75);
		ITransaction transaction2 = new Deposit(100);
		List<ITransaction> transactions1 = new ArrayList<ITransaction>();
		List<ITransaction> transactions2 = new ArrayList<ITransaction>();
		transactions1.add(transaction1);
		transactions2.add(transaction2);
		transactions.put("1234", transactions1);
		transactions.put("9876", transactions2);
	}
}
