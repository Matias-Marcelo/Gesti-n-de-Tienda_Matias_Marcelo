package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImplJDBC;
import main.Logable;

//Create class employee on package model, extended of abstract class Person, also implemented interface Logable
public class Employee extends Person implements Logable {
	// Attribute private int employeeId
	private int employeeId;
	private Dao dao;
	private String password;

	// Implement method login, if user is 123 and password = test then return true,
	// else return false

	// Constructor
	public Employee() {
        // Assign a DaoImplJDBC object to the dao attribute
		this.dao = new DaoImplJDBC();
	}

	public Employee(int employeeId, String password) {
        // Constructor to initialize employeeId and password
		this.employeeId = employeeId;
		this.password = password;

	}

	@Override
	public boolean login(int user, String password) throws SQLException {
        // Implement method login
		dao = new DaoImplJDBC();
		Employee employee = null;
		try {
			System.out.println("Estableciendo conexión con la base de datos...");
			dao.connect();
			System.out.println("Conectado.");
            // Retrieve employee from the database

			employee = dao.getEmployee(user, password);
			if (dao.getEmployee(user, password) != null) {

				return true;

			}
		} catch (SQLException ex) {
            // Handle SQL exception
			System.out.println("ERROR con la BBDD: " + ex.getMessage());
		} finally {
            // Disconnect from the database
			System.out.println("Desconectando....");
			try {
				dao.disconnect();
			} catch (SQLException ex) {
				System.out.println("Error al desconectar " + ex.getMessage());
			}
		}
		return false;

		// if(user == 123 && password.equalsIgnoreCase("test")) {

		// return true;
		// }else {

		// return false;
		// }

	}
    // Getter and setter for employeeId
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
    // Getter and setter for password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    // Getter and setter for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Final Constants:
	final static int USER = 123;
	final static String PASSWORD = "test";

	// public boolean login(int user, String password) {

	// return false;

	// }

}
