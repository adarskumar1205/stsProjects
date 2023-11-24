
public class DBDemoMain {
	public static void main(String[] args) {
		
		//1.Load the DB driver class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
