
public class Menu {

	public static void welcomeCustomer(boolean isFirstTransaction) {
		if (isFirstTransaction){
			System.out.println("*******************************************************");
			System.out.println("************WELCOME TO FIRST NATIONAL BANK*************");
			System.out.println("*******************************************************");
		} else {
			System.out.println("********************WELCOME  BACK**********************");
		}
	}

	public static void promptForPin() {
		System.out.println("");
		System.out.println("To Access your account, please enter your PIN:");
	}

	public static void promptTransactionType() {
		System.out.println("Please choose the type of transaction you would like:");
		System.out.println("Enter 1 for Deposit");
		System.out.println("Enter 2 for Withdrawal");
		System.out.println("Enter 3 for Balance Inquiry");
		
	}

	public static void promptTransactionAmount(String transactionType) {
		System.out.println("Please enter the amount you would like to " + transactionType.toLowerCase() + ":");
	}

	public static void unknownErrorPrompt() {
		System.out.println("We apologize, something seems to have gone wrong in our system.");
		System.out.println("We have rolled back your complete transaction.");
		System.out.println("Please re-enter your pin:");
	}

	public static void promptInvalidTransactionAmount() {
		System.out.println("Invalid dollar amount.");	
	}

	public static void promptRequestAnotherTransaction() {
		System.out.println("");
		System.out.println("Would you like to submit another transaction?");
		System.out.println("Enter 1 for yes");
		System.out.println("Enter 2 for no");
	}
	
	public static void transactionComplete(){
		System.out.println("Transaction Complete.");
	}

	public static void executeEndOfTransaction() {
		System.out.println("Goodbye!");
	}

	public static void promptBadTransactionTypeSelection() {
		System.out.println("Oops, that was not a valid entry.");
	}

	public static void promptBadTransactionDesiredInput() {
		System.out.println("Oops, that was not a valid entry.");
	}

	public static void displayAccountBalance(double balance) {
		System.out.println("Your account balance is $" + balance);		
	}

	public static void displayInsufficientFundsMessage() {
		System.out.println("Insufficient Funds");
	}

	public static void withdrawalPrompt() {
		System.out.println("How much would you like to withdraw?");
	}

	public static void displayBadDollarAmountResponse() {
		System.out.println("Invalid Dollar Amount");
	}

	public static void depositPrompt() {
		System.out.println("How much would you like to deposit?");
		
	}

	public static void badPinEntryPrompt() {
		System.out.println("Invalid PIN entry");
	}
	
}
