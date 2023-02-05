package guiprogramming.bookshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDatabase {
	
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/dbbookshop?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "1234";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	public BookDatabase() {
		

	}
	
	protected Connection getDatabaseConnection() {
		Connection con = null;
		
		try {
			Class.forName(jdbcDriver);
			try {
				con = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
