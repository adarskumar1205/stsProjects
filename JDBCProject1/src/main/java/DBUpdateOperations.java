import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUpdateOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Statement statement = null;
		
		try {
			//1.Load the DB driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2. Create the connection
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");) {
		
			//3. create the Statement object 
			statement = connection.createStatement();
			
			int newSal  = 10000;
			int id = 102;
			
			//4. submit the sql queries / execute the sql command
			String updateQuery = "UPDATE employee SET city ='Pune' where empId="+id+" ";
			
			int rowsAffected = statement.executeUpdate(updateQuery);
			
			if(rowsAffected>0) {
				System.out.println("record is updated");
			}
			else {
				System.out.println("record not updated");
			}
			
		} 
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
