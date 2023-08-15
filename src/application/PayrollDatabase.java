package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class PayrollDatabase {
	private Connection connection; 
	private final String URL = "jdbc:mysql://localhost:3306/payroll_database"; 
	private final String USERNAME = "root"; 
	private final String PASSWORD = "Johanten222001";
	public String sql; 
	public String username;
	public String password;
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			Statement statement = connection.createStatement();
			JOptionPane.showMessageDialog(null, "Database connection created successfully");
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
