package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class frontpage {
	@FXML
	private AnchorPane rootpane;
	@FXML
	private Label TopTittle;
	@FXML
	private Label SIAbutton;
	@FXML
	private Button student;
	@FXML
	private Button teacher;
	@FXML
	private Button Admin;

	
	
	@FXML
	public void adminLogin(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminLogin.fxml"));
		rootpane.getChildren().setAll(pane);
	}
	@FXML
	public void teacherLogin(ActionEvent event) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/teacherLogin.fxml"));
		rootpane.getChildren().setAll(pane);
	}
	@FXML
	public void studentLogin(ActionEvent event) throws IOException {
		//Stage primaryStage = new Stage();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/studentLogin.fxml"));
		rootpane.getChildren().setAll(pane);
	}
	
	
	
	
}
