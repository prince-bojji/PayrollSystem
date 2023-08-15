package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentController {
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
	TextField txtSalID, txtPyrlID, txtAmount;
	
	public void confirm() {
		createConnection();
		
		int salID = Integer.parseInt(txtSalID.getText());
		int pyrlID = Integer.parseInt(txtPyrlID.getText());
		float amount = Float.parseFloat(txtAmount.getText());
		
		try {
			Statement statement = connection.createStatement();
			sql = "insert into payment(pay_amt,sal_ID,pyrl_ID)"
			+"values(" +amount +"," +salID +"," +pyrlID +")";
			statement.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Payment Success.");
		} catch (SQLException e) {
			e.printStackTrace();
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
