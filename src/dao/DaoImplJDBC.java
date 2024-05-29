package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

public class DaoImplJDBC implements Dao{

	private Connection connection;

	@Override
	public void connect() throws SQLException {
		// TODO Auto-generated method stub
		// Define connection parameters
		String url = "jdbc:mysql://localhost:8889/shop";
		String user = "root";
		String pass = "root";
		//this.connection = DriverManager.getConnection(url, user, pass);
        // Establish a connection to the database
		this.connection = DriverManager.getConnection(url, user, pass);

	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		Employee employee = null;
        // Prepare the SQL query
		String query = "select * from employee where employeeId = ? and password = ?";
		try(PreparedStatement ps = connection.prepareStatement(query)){
            // Set parameters for the query
			ps.setInt(1,employeeId);
			ps.setString(2, password);
            // Execute the query and get the result set
			try (ResultSet rs = ps.executeQuery()) {
                // If a matching employee is found, create an Employee object
	        	if (rs.next()) {
	        		employee = new Employee(rs.getInt(1),rs.getString(2));            		            				
	        	}
	        }

		}catch(SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
        // Close the database connection if it is not null
		if (connection != null) {
			connection.close();
		}
	}

}
