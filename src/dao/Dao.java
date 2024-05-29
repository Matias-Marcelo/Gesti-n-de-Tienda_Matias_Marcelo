package dao;

import java.io.IOException;
import java.sql.SQLException;

import model.Employee;

public interface Dao {
	
	// Method to establish a connection to the database
    public void connect() throws SQLException;

    // Method to retrieve an employee from the database using employee ID and password
    public Employee getEmployee(int employeeId, String password) throws SQLException;

    // Method to close the connection to the database
    public void disconnect() throws SQLException;
	
	
}
