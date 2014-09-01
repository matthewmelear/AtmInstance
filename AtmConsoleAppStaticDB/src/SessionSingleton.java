
public class SessionSingleton {

	private Dao dao = new Dao();
	private static SessionSingleton sessionSingleton = new SessionSingleton();
	private boolean isFirstTransaction;
	private boolean shouldContinue;
	private Account account;
	
	private SessionSingleton(){
		this.isFirstTransaction = true;
		this.shouldContinue = false;
		this.account = null;
	}

	public static synchronized SessionSingleton getEngineSingleton() {
		if (sessionSingleton == null){
			return new SessionSingleton();
		}
		return sessionSingleton;
	}
	
	public void login(String pin){
		sessionSingleton.setAccount(dao.getAccountIdByPin(pin));
	}
	

	public boolean isFirstTransaction() {
		return isFirstTransaction;
	}

	public void setIsFirstTransaction(boolean isFirstTransaction) {
		this.isFirstTransaction = isFirstTransaction;
	}

	public boolean shouldContinue() {
		return shouldContinue;
	}

	public void setShouldContinue(boolean shouldContinue) {
		this.shouldContinue = shouldContinue;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
