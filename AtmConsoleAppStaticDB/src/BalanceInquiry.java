import java.util.List;

public class BalanceInquiry implements ITransaction {

	Dao dao = new Dao();

	public void execute(String accountId) {
		double accountBalance = executeBalanceInquiry(accountId);
		Menu.displayAccountBalance(accountBalance);
	}

	public double executeBalanceInquiry(String accountId) {
		List<ITransaction> relevantTransactions = dao.inquire(accountId);
		return calculateBalance(relevantTransactions);
	}

	public double calculateBalance(List<ITransaction> transactions) {
		double accountBalance = 0;
		for (ITransaction transaction : transactions) {
			if (transaction instanceof Deposit) {
				accountBalance += transaction.getAmount();
			} else if (transaction instanceof Withdrawal) {
				accountBalance -= transaction.getAmount();
			}
		}
		return accountBalance;
	}

	public double getAmount() {
		return 0;
	}
}
