
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionSingleton engineSingleton = SessionSingleton.getEngineSingleton();
		Dao.init();
		do{
			try{
				AtmEngine.execute();
			} catch(Exception e){
				System.out.println(e.getMessage());
				Menu.unknownErrorPrompt();
			}
			
		} while (engineSingleton.shouldContinue());
		Menu.executeEndOfTransaction();
	}

}
