package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;



public class studentController {
	
	public static String user_id = null;
	@FXML
	private AnchorPane student_panel;
	@FXML
	private AnchorPane student_login;
	@FXML
	private TextField student_user;
	@FXML
	private PasswordField student_pass;
	@FXML
	private Label Studentlvl;
	@FXML
	private Button student_loginbtn;
	@FXML
	private Button student_logout;
	@FXML
	private Button back_from_slogin_btn;
	@FXML
    private AnchorPane std_changing_pass;

    @FXML
    private TextField std_id_pass;

    @FXML
    private TextField std_old_pass;

    @FXML
    private TextField std_new_pass;

    @FXML
    private Button std_change_pass_btn;

    @FXML
    private Button back_tchange_pass_btn;
    @FXML
    private Button change_pass_btn;
    @FXML
    private Button search_result;
	
	Connection con;
    PreparedStatement pst;
    ResultSet rs;

	
    
    @FXML
	public void login(ActionEvent event) throws IOException {
		String username = student_user.getText();
		String password = student_pass.getText();
		
		if(username.equals("") && password.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a username and password");
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
				
				pst = con.prepareStatement("select * from student where student_id=? and password=?");
				
				pst.setString(1, username);
				pst.setString(2, password);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Login Sucessfull");
					user_id = student_user.getText();
					AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/StudentPanel.fxml"));
					student_login.getChildren().setAll(pane);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong");
					student_user.setText("");
					student_pass.setText("");
					student_user.requestFocus();;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       }
	@FXML
	public void logout(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		student_panel.getChildren().setAll(pane);
       }
	
	public void back_from_student_login(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		student_login.getChildren().setAll(pane);
    } 
	
	public void show_result(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Student_View_Result.fxml"));
	    Scene scene = new Scene(root,850,650);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public void stdchangepass(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_Change_Pass.fxml"));
		student_panel.getChildren().setAll(pane);
	}
	
	@FXML
	public void std_changepass_btn(ActionEvent event) throws IOException {
		String s_id = std_id_pass.getText();
		String s_oldpass = std_old_pass.getText();
		String s_newpass = std_new_pass.getText();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("update student set password=? where student_id=? and password='"+s_oldpass+"'");
			pst.setString(1, s_newpass);
			pst.setString(2, s_id);
			
			
            int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Password Upadated");
				
				std_id_pass.setText("");
				std_old_pass.setText("");
				std_new_pass.setText("");
				std_id_pass.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "Record doesn't exists");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       }
	
	public void back_from_student_changepass(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/StudentPanel.fxml"));
		std_changing_pass.getChildren().setAll(pane);
    }
}