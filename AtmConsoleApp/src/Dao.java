import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	private static final String HOST = "jdbc:postgresql://localhost:5432/ATM_ASSIGNMENT";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Oregonducks900";
	
//	static Map<String, List<ITransaction>> transactions = new HashMap<String, List<ITransaction>>();
//	static List<Account> accounts = new ArrayList<Account>();
	public static void initializeConnection(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void withdraw(String accountId, double amount){
		initializeConnection();
		String sql = "INSERT INTO public.\"TRANSACTION\" (\"ACCOUNT_ID\", \"DEBIT_AMOUNT\")" +
				" VALUES (" + accountId + ", " + amount + ")";
		try {
			Connection con = DriverManager.getConnection( HOST, USERNAME, PASSWORD );
			Statement stmt = con.createStatement( );
			stmt.executeQuery( sql );
		} catch (Exception e){
			
		}
	}
	
	public static void deposit(String accountId, double amount){
		initializeConnection();
		String sql = "INSERT INTO public.\"TRANSACTION\" (\"ACCOUNT_ID\", \"CREDIT_AMOUNT\")" +
				" VALUES (" + accountId + ", " + amount + ")";
		try {
			Connection con = DriverManager.getConnection( HOST, USERNAME, PASSWORD );
			Statement stmt = con.createStatement( );
			stmt.executeQuery( sql );
		} catch (Exception e){
			
		}
	}
	
	public List<ITransaction> inquire(String accountId) throws SQLException{
		initializeConnection();
		String sql = "SELECT * FROM public.\"TRANSACTION\" WHERE \"TRANSACTION\".\"ACCOUNT_ID\" = " + accountId;
		List<ITransaction> transactions = new ArrayList<ITransaction>();
		try {
			Connection con = DriverManager.getConnection( HOST, USERNAME, PASSWORD );
			Statement stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( sql );
			while (rs.next()){
				if (rs.getDouble("CREDIT_AMOUNT") != 0){
					Deposit deposit = new Deposit();
					deposit.setAmount(rs.getDouble("CREDIT_AMOUNT"));
					transactions.add(deposit);
				} else if (rs.getDouble("DEBIT_AMOUNT") != 0){
					Withdrawal withdrawal = new Withdrawal();
					withdrawal.setAmount(rs.getDouble("DEBIT_AMOUNT"));
					transactions.add(withdrawal);
				}
			}
		} catch (Exception e){
			
		}
		return transactions;
	}
	
	public Account getAccountIdByPin(String pin) throws SQLException{
		initializeConnection();
		String sql = "SELECT * FROM public.\"ACCOUNT\" WHERE \"ACCOUNT\".\"PIN\" = '" + pin + "'";
		try {
			Connection con = DriverManager.getConnection( HOST, USERNAME, PASSWORD );
			Statement stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( sql );
			if(rs.next()){
				Account account = new Account(String.valueOf(rs.getInt("ACCOUNT_ID")), pin);
				return account;
			}
			return null;
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
}
