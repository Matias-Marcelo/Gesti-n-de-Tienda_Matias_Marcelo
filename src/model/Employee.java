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
		// Asignar un objeto DaoImplJDBC al atributo dao
		this.dao = new DaoImplJDBC();
	}

	public Employee(int employeeId, String password) {
		// TODO Auto-generated constructor stub
		this.employeeId = employeeId;
		this.password = password;

	}

	@Override
	public boolean login(int user, String password) throws SQLException {
		// TODO Auto-generated method stub
		dao = new DaoImplJDBC();
		Employee employee = null;
		try {
			System.out.println("Estableciendo conexión con la base de datos...");
			dao.connect();
			System.out.println("Conectado.");

			employee = dao.getEmployee(user, password);
			if (dao.getEmployee(user, password) != null) {

				return true;

			}
		} catch (SQLException ex) {
			System.out.println("ERROR con la BBDD: " + ex.getMessage());
		} finally {
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
