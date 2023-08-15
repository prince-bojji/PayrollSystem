package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AddEmployeeController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField txtName, txtNo, txtAdd;
	
	private Connection connection; 
	private final String URL = "jdbc:mysql://localhost:3306/payroll_database"; 
	private final String USERNAME = "root"; 
	private final String PASSWORD = "Johanten222001";
	private String sql; 
	public String username;
	public String password;
	
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
		
	public void insert() {
		createConnection();
		
		String name = txtName.getText();
		String num = txtNo.getText();
		String address = txtAdd.getText();
		
		try {
			Statement statement = connection.createStatement();
			sql = "insert into employee(emp_name,emp_no,emp_add)"+"values(" +"'"+name+"'" +","+num +","+"'"+address+"'"+")";
			statement.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "New employee successfully inserted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void switchToComputeSalary(ActionEvent event) throws IOException{
		switchScene(event, "ComputeSalary.fxml");
	}
	
	public void switchToPayment(ActionEvent event) throws IOException{
		switchScene(event, "Payment.fxml");
	}
	
	public void switchToSalaryReport(ActionEvent event) throws IOException{
		switchScene(event, "SalaryReport.fxml");
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
