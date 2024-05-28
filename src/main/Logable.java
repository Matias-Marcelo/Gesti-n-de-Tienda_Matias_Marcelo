package main;

import java.sql.SQLException;

//Create interface logable 
public interface Logable {

	//Method login with parameters int users and String password;
	public boolean login(int user, String password) throws SQLException;

	
	
}
