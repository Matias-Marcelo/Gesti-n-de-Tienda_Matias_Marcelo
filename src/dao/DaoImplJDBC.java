package dao;


import java.lang.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplJDBC implements Dao{
	private ArrayList<Product> products = new ArrayList<>();  // Initialize products

	private Connection connection;

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3307/shop";
		String user = "root";
		String pass = "";
		//this.connection = DriverManager.getConnection(url, user, pass);
        // Establish a connection to the database
		try {
			
			this.connection = DriverManager.getConnection(url, user, pass);
			
		}catch(SQLException e){
			e.printStackTrace();
		}

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
	public void disconnect() {
		// TODO Auto-generated method stub
        // Close the database connection if it is not null
		
		if (connection != null) {
			try {
				connection.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Product> getInventory(){
		//SQL query
		String query = "select * from inventory";
		//Variable to store the product
		Product product = null;
	
		//Initialize connection	
		connect();
		   
		try(Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);){
			//Iterate through the query results
			while (rs.next()) {
				
				product = new Product();
				 //Add a new product to the list
				Amount amount = new Amount(rs.getDouble("wholesalerPrice"));
				products.add(new Product(rs.getString("name"), amount, rs.getBoolean("avaible"), rs.getInt("stock"), new Amount(amount.getValue() * 2)));
			}
			//Disconnect
			disconnect();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//Return the list of products
		return products;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory){
		// TODO Auto-generated method stub
		//Prepare SQL Query
		String query = "INSERT INTO historical_inventory (id_product, name, wholesalerPrice, avaible, stock, created_at) VALUES (?, ?, ?, ?, ?, ?)";
		//Initialize connection
		connect();
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			//Iterate though the inventory
			for (Product product : inventory) {
				 //Set the parameters for each product
				stmt.setInt(1,product.getId());
				stmt.setString(2, product.getName());
				Amount amount = new Amount();
				
				amount = product.getWholesalerPrice();
				stmt.setDouble(3, amount.getValue());
				stmt.setBoolean(4, product.isAvailable());
				stmt.setInt(5, product.getStock());
				stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
				stmt.executeUpdate();
			}
			//Close connection
			disconnect();

			return true;

		}catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		
}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		//Prepare SQL Query
		String query = "INSERT INTO inventory (id, name, wholesalerPrice, avaible, stock) VALUES (?, ?, ?, ?, ?)";
		//Initialize connection
		connect();
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			
            //Set the parameters for the product

				
				stmt.setInt(1,product.getId());
				stmt.setString(2, product.getName());
				Amount amount = new Amount();
				
				amount = product.getWholesalerPrice();
				stmt.setDouble(3, amount.getValue());
				stmt.setBoolean(4, product.isAvailable());
				stmt.setInt(5, product.getStock());
				stmt.executeUpdate();
			
				//Close connection
				disconnect();

		
	
		}catch (SQLException e) {
			e.printStackTrace();
		

		}
			
		
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		//Prepare SQL Query
		String query = "UPDATE inventory SET stock = ? WHERE name = ?";
		//Initialize connection
		connect();
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			
			
            //Set the parameters for the update

				stmt.setInt(1,product.getStock());
				stmt.setString(2, product.getName());
				stmt.executeUpdate();
			
			//Close connection
				disconnect();

	
		}catch (SQLException e) {
			e.printStackTrace();
		

		}
		
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		//Prepare SQL Query
		String query = "DELETE FROM inventory WHERE id = ?";
		//Initialize connection
		connect();
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			
            // Set the productId for delete
				
				stmt.setInt(1,productId);
				stmt.executeUpdate();
			
				//Close connection
				disconnect();

		
	
		}catch (SQLException e) {
			e.printStackTrace();
		

		}
		
	}
}
