package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class Admin_Edit_Student {
	@FXML
	private AnchorPane edit_student;
	@FXML
	private Label editing_std_lvl;
	@FXML
	private TextField edit_student_id;
	@FXML
	private TextField edit_student_name;
	@FXML
	private TextField edit_intake;
	@FXML
	private TextField edit_section;
	@FXML
	private TextField edit_std_dept;
	@FXML
	private TextField edit_father_name;
	@FXML
	private TextField edit_mother_name;
	@FXML
	private TextField edit_std_dob;
	@FXML
	private TextField edit_std_contact;
	@FXML
	private TextField edit_std_address;
	@FXML
	private TextField edit_std_blood;
	@FXML
	private TextField edit_std_email;
	@FXML
	private TextField edit_std_pass;
	@FXML
	private Button std_confrim_change_btn;
	@FXML
	private Button back_from_sedit_btn;
	Connection con;
	PreparedStatement pst;

	public void confirm_change_btn(ActionEvent event) {

    	String std_id = edit_student_id.getText();
		String std_sec = edit_section.getText();
    	String std_contact = edit_std_contact.getText();
    	String std_address = edit_std_address.getText();
    	String std_blood = edit_std_blood.getText();
    	String std_email = edit_std_email.getText();
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("update student set section=?, contact_no=?, address=?, blood_group=?, email=? where student_id='"+std_id+"'");
			
			pst.setString(1, std_sec);
			pst.setString(2, std_contact);
			pst.setString(3, std_address);
			pst.setString(4, std_blood);
			pst.setString(5, std_email);
			
			int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record updated");
				
				edit_student_id.setText("");
				edit_section.setText("");
				edit_std_contact.setText("");
				edit_std_address.setText("");
				edit_std_blood.setText("");
				edit_std_email.setText("");
			    edit_student_id.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Student ID");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void back_from_edit_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_modification.fxml"));
		edit_student.getChildren().setAll(pane);
    }

}
