package dao;

public class DaoImplJDBC implements Dao{

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/school";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);
	}

	@Override
	public int getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

}
