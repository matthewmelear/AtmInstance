
public class Validator {

	public boolean isValidTransactionTypeDecision(String transactionTypeDecision) {
		return transactionTypeDecision.equals("1") ||
				transactionTypeDecision.equals("2") ||
				transactionTypeDecision.equals("3");
	}

	public boolean isValidTransactionDesiredInput(String transactionDesired) {
		return transactionDesired.equals("1") ||
			   transactionDesired.equals("2");
	}

	public boolean isValidDollarAmount(String amount) {
		
		boolean hasValidCents = true;
		boolean isValidNumber = true;
		if (amount.contains(".")){
			hasValidCents = amount.substring(amount.indexOf(".")).length() == 2;
		} 
		
		try {
		  double postCastAmount = Double.parseDouble(amount);
		} catch (Exception e){
			isValidNumber = false;
		}
		
		return hasValidCents && isValidNumber;
	}
	
}
