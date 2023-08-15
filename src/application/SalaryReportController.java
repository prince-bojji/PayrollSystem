package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SalaryReportController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Connection connection; 
	private final String URL = "jdbc:mysql://localhost:3306/payroll_database"; 
	private final String USERNAME = "root"; 
	private final String PASSWORD = "Johanten222001";
	private String sql; 
	public String username;
	public String password;
	
	@FXML
	TextField txtEmpID,txtPayID, txtStatus;
	
	public void search() {
		createConnection();
		
		int empID = Integer.parseInt(txtEmpID.getText());
		int payID = Integer.parseInt(txtPayID.getText());
		float payAmt = 0;
		float salAmt = 0;
		String status = " ";
		
		try {
			sql = "SELECT * FROM payment WHERE pay_ID = "+payID;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				payAmt = result.getFloat("pay_amt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			sql = "SELECT * FROM salary WHERE sal_ID = "+empID;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				salAmt = result.getFloat("sal_amt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(payAmt == salAmt) {
			txtStatus.setText("Completed");
			status = "Completed";
		}
		else {
			txtStatus.setText("Pending");
			status = "Pending";
		}
	}
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			Statement statement = connection.createStatement();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void switchToAddEmployee(ActionEvent event) throws IOException{
		switchScene(event, "AddEmployee.fxml");
	}
	
	public void switchToComputeSalary(ActionEvent event) throws IOException{
		switchScene(event, "ComputeSalary.fxml");
	}
	
	public void switchToPayment(ActionEvent event) throws IOException{
		switchScene(event, "Payment.fxml");
	}	
	
	public void exit(ActionEvent event) throws IOException{
		System.exit(0);
	}
	
	private void switchScene(ActionEvent event, String temp) throws IOException {
		root = FXMLLoader.load(getClass().getResource(temp));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
