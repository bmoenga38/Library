package authenticate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	String password;
	String username;
	Connection con;
	String Driver="com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/library";
public ConnectionPool(String user,String pass){
	this.password = pass;
	this.username = user;
}
public Connection connector(){
	try {
		Class.forName(Driver);
		con = DriverManager.getConnection(url,username,password);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}
}
