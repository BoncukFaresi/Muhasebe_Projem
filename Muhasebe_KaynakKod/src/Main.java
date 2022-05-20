import java.sql.SQLException;

public class Main {
	
		

	public static void main(String[] args) throws SQLException {
		//new LogIn();
		dbConnect conn = new dbConnect();
		new AnaSayfa(1);
		
	}

}
