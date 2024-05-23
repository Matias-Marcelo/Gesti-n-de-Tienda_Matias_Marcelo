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
		String url = "jdbc:mysql://localhost:8889/COMPANY";
		String user = "root";
		String pass = "password";
		//this.connection = DriverManager.getConnection(url, user, pass);
		this.connection = DriverManager.getConnection(url, user, pass);

	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		Employee employee = null;
		// prepare query
		String query = "select * from employee where id = ? ";
		try(PreparedStatement ps = connection.prepareStatement(query)){
			// set id to search for
			ps.setInt(1,employeeId);
		  	//System.out.println(ps.toString());
			try (ResultSet rs = ps.executeQuery()) {
	        	if (rs.next()) {
	        		employee =  new Employee();            		            				
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
		if (connection != null) {
			connection.close();
		}
	}

}
