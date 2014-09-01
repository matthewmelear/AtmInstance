import java.util.Scanner;


public class Deposit implements ITransaction{

	private double amount;
	Scanner scanner = new Scanner(System.in);
	Validator validator = new Validator();
	
	public Deposit(double amount) {
		super();
		this.amount = amount;
	}

	public Deposit() {
		super();
	}
	
	@Override
	public void execute(String accountId) {
		executeDeposit(accountId);
	}

	public double executeDeposit(String accountId){
		BalanceInquiry bi = new BalanceInquiry();
		boolean canContinue = false;
		while (!canContinue){
			Menu.depositPrompt();
			String depositAmountString = scanner.nextLine();
			if (validator.isValidDollarAmount(depositAmountString)){
				amount = new Double(depositAmountString);
				canContinue = true;
			} else {
				Menu.displayBadDollarAmountResponse();
			}
		}
		double accountBalance = bi.executeBalanceInquiry(accountId);
		if (amount <= accountBalance){
			Dao.deposit(accountId, amount);
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
