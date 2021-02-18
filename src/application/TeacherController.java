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
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeacherController {
	@FXML
	private AnchorPane teacher_login;
	@FXML
	private AnchorPane teacher_panel;
	@FXML
	private AnchorPane changing_pass;
	@FXML
	private Label teacherpanel_level;
	@FXML
	private AnchorPane Teacherpanel_insidepane;
	@FXML
    private TextField teacher_user;

    @FXML
    private PasswordField teacher_pass;
    @FXML
    private Button teacher_loginbtn;

	@FXML
	private Button add_result;
	@FXML
	private Button modify_result;
	@FXML
	private Button teacher_logout;
	@FXML
	private Button back_from_tlogin_btn;
	@FXML
    private TextField tchr_id_pass;

    @FXML
    private TextField tchr_old_pass;

    @FXML
    private TextField tchr_new_pass;

    @FXML
    private Button change_pass_btn;

    @FXML
    private Button back_tchange_pass_btn;
	
	Connection con;
    PreparedStatement pst;
    ResultSet rs;
	
	@FXML
	public void login(ActionEvent event) throws IOException {
		String username = teacher_user.getText();
		String password = teacher_pass.getText();
		
		if(username.equals("") && password.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a username and password");
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
				pst = con.prepareStatement("select * from teacher where teacher_id=? and password=?");
				
				pst.setString(1, username);
				pst.setString(2, password);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Login Sucessfull");
					AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/TeacherPanel.fxml"));
					teacher_login.getChildren().setAll(pane);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong");
					teacher_user.setText("");
					teacher_pass.setText("");
					teacher_user.requestFocus();;
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
	public void add_result(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_Add_Result.fxml"));
		teacher_panel.getChildren().setAll(pane);
	}
	
	public void modify_result(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_Edit_Result.fxml"));
		teacher_panel.getChildren().setAll(pane);
	}
	
	public void changepass(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_Change_Pass.fxml"));
		teacher_panel.getChildren().setAll(pane);
	}
	
	@FXML
	public void changepass_btn(ActionEvent event) throws IOException {
		String t_id = tchr_id_pass.getText();
		String t_oldpass = tchr_old_pass.getText();
		String t_newpass = tchr_new_pass.getText();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("update teacher set password=? where teacher_id=? and password='"+t_oldpass+"'");
			pst.setString(1, t_newpass);
			pst.setString(2, t_id);
			
			
            int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Password Upadated");
				
				tchr_id_pass.setText("");
				tchr_old_pass.setText("");
				tchr_new_pass.setText("");
				tchr_id_pass.requestFocus();
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
	
	public void logout(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		teacher_panel.getChildren().setAll(pane);
       }
	
	public void back_from_teacher_login(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		teacher_login.getChildren().setAll(pane);
    }
	
	public void back_from_teacher_changepass(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/TeacherPanel.fxml"));
		changing_pass.getChildren().setAll(pane);
    }
}
