package dao;

public interface Dao {
	
	public void connect();

	public int getEmployee(int employeeId, String password);

	public void disconnect();
}
