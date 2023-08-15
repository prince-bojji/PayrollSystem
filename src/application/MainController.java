package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToAddEmployee(ActionEvent event) throws IOException{
		switchScene(event, "AddEmployee.fxml");
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
