import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDemoMain {

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
//			statement = connection.createStatement();
			
			//4. submit the sql queries / execute the sql command 
//			String insertQuery = "INSERT INTO employee values(102,'Vishal','Guwahati',2000)";
//			
//			int rowsAffected = statement.executeUpdate(insertQuery);
//			
//			if(rowsAffected>0) {
//				System.out.println("record id inserted");
//			}
//			else {
//				System.out.println("record not inserted");
//			}
			
			
			String insertQuery = "INSERT INTO employee values(?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			
			preparedStatement.setInt(1, 106);
			preparedStatement.setString(2, "Rohit");
			preparedStatement.setString(3, "BLR");
			preparedStatement.setInt(4, 8000);
			
			
			int rowsAffected = preparedStatement.executeUpdate();		
			
			if(rowsAffected > 0)
				System.out.println("Record is inserted");
			else 
				System.out.println("Record not inserted");
			
			
		} 
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//				//5.close the connection 
//			
//				try {
//					if(connection!=null)
//						connection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
		

	}

}
