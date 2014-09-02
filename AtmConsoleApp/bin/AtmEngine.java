import java.util.Scanner;


public class AtmEngine {

	static SessionSingleton sessionSingleton = SessionSingleton.getEngineSingleton();
	
	public static void execute() throws Exception{
		
		Menu.welcomeCustomer(sessionSingleton.isFirstTransaction());
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Validator validator = new Validator();
		
		//initialize the transaction, take in the pin and setAccountNumber
		while(sessionSingleton.getAccount() == null){
			sessionSingleton.setIsFirstTransaction(false);
			Menu.promptForPin();
			String pinEntered;
			pinEntered = scanner.nextLine();
			sessionSingleton.login(pinEntered);
			if (sessionSingleton.getAccount() == null){
				Menu.badPinEntryPrompt();
			}
		}
		
		String transactionTypeDecision = "";
		sessionSingleton.setShouldContinue(false);

		//Prompts for transaction type, reads transaction type, stays in loop if transaction type is invalid value
		while(!sessionSingleton.shouldContinue()){
			Menu.promptTransactionType();
			transactionTypeDecision = scanner.nextLine();
			if (validator.isValidTransactionTypeDecision(transactionTypeDecision)){
				sessionSingleton.setShouldContinue(true);
			} else {
				Menu.promptBadTransactionTypeSelection();
			}
		} 
	
		ITransaction currentTransaction = translateTransactionMenuOptionIntoTransactionType(transactionTypeDecision);
		currentTransaction.execute(sessionSingleton.getAccount().getAccountId());
		sessionSingleton.setShouldContinue(false);
		
		while(!sessionSingleton.shouldContinue()){
			Menu.promptRequestAnotherTransaction();
			String transactionDesired = scanner.nextLine();
			if (validator.isValidTransactionDesiredInput(transactionDesired)){
				sessionSingleton.setShouldContinue(isTransactionDesired(transactionDesired));
				break;
			} else {
				Menu.promptBadTransactionDesiredInput();
			}
		}

	}
	
	private static boolean isTransactionDesired(String transactionDesired) {
		return transactionDesired.toUpperCase().equals("1");
	}

	private static ITransaction translateTransactionMenuOptionIntoTransactionType(String menuOption) {
		if (menuOption.equals("1")){
			return new Deposit();
		} else if (menuOption.equals("2")){
			return new Withdrawal();
		} else if (menuOption.equals("3")){
			return new BalanceInquiry();
		}
		return null;
	}
}
