import java.sql.SQLException;


public interface ITransaction{
	
	public void execute(String accountId) throws SQLException;
	public double getAmount();
}
