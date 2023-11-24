import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelectoperation {

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
		
			statement = connection.createStatement();
			
			String selectQuery = "Select empId,empName,city,salary FROM employee";
			
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("empId");
				String eName  = resultSet.getString("empName");
				String city = resultSet.getString("city");
				int salary = resultSet.getInt("salary");
				
				System.out.println(id + "\t" + eName + "\t" + city + "\t" + salary + "\t");
			}
			
			resultSet.close();
			
		} 
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
