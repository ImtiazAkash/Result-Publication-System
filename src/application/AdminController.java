package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.PasswordField;

public class AdminController {
	@FXML
	private AnchorPane adminroot;
	@FXML
    private AnchorPane teacher_modification;
	@FXML
	private AnchorPane student_modification;
	@FXML
	private TextField Adminuser;
	@FXML
	private PasswordField Adminpass;
	@FXML
	private Button Admin_loginbtn;
	@FXML
	private Label Adminlvl;
	@FXML
	private AnchorPane admin_panel;
	@FXML
	private Label AdminPanel_level;
	@FXML
	private AnchorPane Admin_panel_pane;
	@FXML
	private Button teachers_Control_panel_btn;
	@FXML
    private Button add_teacher_btn;

    @FXML
    private Button modify_teacher_btn;

    @FXML
    private Button search_teacher_btn;

    @FXML
    private Button delete_teacher_btn;

    @FXML
    private Button teachermod_back;
    @FXML
    private Button logout_from_teacher;
    
	@FXML
	private Button student_Control_panel_btn;
	@FXML
	private Button logout_from_std;
    @FXML
    private Button admin_logout;
      
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

	@FXML
	public void login(ActionEvent event) throws IOException { // takes us to subsection of admin panel
		
		String username = Adminuser.getText();
		String password = Adminpass.getText();
		
		if(username.equals("") && password.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a username and password");
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
				pst = con.prepareStatement("select * from admin where admin_id=? and password=?");
				
				pst.setString(1, username);
				pst.setString(2, password);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Login Sucessfull");
					AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminPanel.fxml"));
					adminroot.getChildren().setAll(pane);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong");
					Adminuser.setText("");
					Adminpass.setText("");
					Adminuser.requestFocus();;
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

	public void back_from_admin_login(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		adminroot.getChildren().setAll(pane);
	}
	
	@FXML
	public void teacher_control(ActionEvent event) throws IOException { // this takes us to the admins teacher subsection
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_modification.fxml"));
		admin_panel.getChildren().setAll(pane);
	}
	@FXML
	public void add_teacher(ActionEvent event) throws IOException { // this allows us to add teacher
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Add_Teacher.fxml"));
		teacher_modification.getChildren().setAll(pane);
       }
	
	@FXML
	public void modify_teacher(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/edit_teacher.fxml"));
		teacher_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void search_teacher(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_search_Teacher.fxml"));
		teacher_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void delete_teacher(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_delete_teacher.fxml"));
		teacher_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void student_control(ActionEvent event) throws IOException { // this takes us to the admins student subsection
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_modification.fxml"));
		admin_panel.getChildren().setAll(pane);
	}
	
	@FXML
	public void add_student(ActionEvent event) throws IOException { // this allows us to add teacher
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_Add_Student.fxml"));
		student_modification.getChildren().setAll(pane);
       }
	
	@FXML
	public void modify_student(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_Edit_Student.fxml"));
		student_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void search_student(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_Search_Student.fxml"));
		student_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void delete_student(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Admin_Delete_Student.fxml"));
		student_modification.getChildren().setAll(pane);
	}
	
	@FXML
	public void logout(ActionEvent event) throws IOException { // logs us out from admin panel and takes us to front page
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		admin_panel.getChildren().setAll(pane);
       }
	public void back_from_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminPanel.fxml"));
		teacher_modification.getChildren().setAll(pane);
       }
	public void logout_from_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		teacher_modification.getChildren().setAll(pane);
       }
	
	public void back_from_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/AdminPanel.fxml"));
		student_modification.getChildren().setAll(pane);
       }
	
	public void logout_from_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/FrontPage.fxml"));
		student_modification.getChildren().setAll(pane);
       }
}
