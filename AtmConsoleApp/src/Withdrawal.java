import java.sql.SQLException;
import java.util.Scanner;


public class Withdrawal implements ITransaction{

private double amount;
Scanner scanner = new Scanner(System.in);
Validator validator = new Validator();
	public Withdrawal(double amount) {
		super();
		this.amount = amount;
	}
	
	public Withdrawal() {
		super();
	}

	@Override
	public void execute(String accountId) throws SQLException {
		executeWithdrawal(accountId);
	}

	public synchronized double executeWithdrawal(String accountId) throws SQLException{
		BalanceInquiry bi = new BalanceInquiry();
		boolean canContinue = false;
		while (!canContinue){
			Menu.withdrawalPrompt();
			String withdrawalAmountString = scanner.nextLine();
			if (validator.isValidDollarAmount(withdrawalAmountString)){
				amount = new Double(withdrawalAmountString);
				canContinue = true;
			} else {
				Menu.displayBadDollarAmountResponse();
			}
		}
		double accountBalance = bi.executeBalanceInquiry(accountId);
		if (amount <= accountBalance){
			Dao.withdraw(accountId, amount);
			accountBalance = bi.executeBalanceInquiry(accountId);
			Menu.transactionComplete();
		} else {
			Menu.displayInsufficientFundsMessage();
		}
		return accountBalance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
