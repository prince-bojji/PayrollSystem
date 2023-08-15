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

public class ComputeSalaryController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField txtEmpID, txtHourlyRate, txtAbsent, txtOvertime, txtLate, txtPayrollType, txtPayrollDate, txtAmount;
	
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
	
	public void ok() {
		createConnection();

		int emp_ID = Integer.parseInt(txtEmpID.getText());
		int hourlyRate = Integer.parseInt(txtHourlyRate.getText());
		int absent = Integer.parseInt(txtAbsent.getText());
		int overtime = Integer.parseInt(txtOvertime.getText());
		int late = Integer.parseInt(txtLate.getText());
		String payrollType = txtPayrollType.getText();
		String payrollDate = txtPayrollDate.getText();
		int days = 0;
		
		if(payrollType.equalsIgnoreCase("weekly"))
			days = 7;
		if(payrollType.equalsIgnoreCase("monthly"))
			days = 31;
		
		//Salary Formula
		float salary = (float) ((hourlyRate * 8 * days) + (overtime * 1.25) - (late * hourlyRate) - (absent * 8));
		txtAmount.setText(String.format("%.2f", salary));
		
		try {
			Statement statement = connection.createStatement();
			sql = "insert into salary(hrly_rate,t_days_absent,t_hrs_over,t_hrs_late,sal_amt,emp_ID)"
			+"values(" +hourlyRate +"," +absent +"," +overtime +"," +late +"," +salary +"," +emp_ID +")";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Statement statement = connection.createStatement();
			sql = "insert into payroll(pyrl_date,pyrl_type,emp_ID)"
			+"values(" +"'" +payrollDate +"'" +"," +"'" +payrollType +"'" +"," +emp_ID +")";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void switchToAddEmployee(ActionEvent event) throws IOException{
		switchScene(event, "AddEmployee.fxml");
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
