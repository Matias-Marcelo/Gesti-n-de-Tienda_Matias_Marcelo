package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;
import model.Product;

public interface Dao {
	
	// Method to establish a connection to the database
    public void connect();

    // Method to retrieve an employee from the database using employee ID and password
    public Employee getEmployee(int employeeId, String password);

    // Method to close the connection to the database
    public void disconnect();
    
    //Method to create an ArrayList and get the inventory.
    public ArrayList<Product> getInventory();
    
    //Method to return a boolean if the process is correct or not.
    public boolean writeInventory(ArrayList<Product> inventory);
    
    public void addProduct(Product product);
    
    public void updateProduct(Product product);
    
    public void deleteProduct(int productId);
    
    
    

	
}
