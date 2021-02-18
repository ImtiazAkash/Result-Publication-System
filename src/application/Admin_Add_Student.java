package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class Admin_Add_Student {
	@FXML
	private AnchorPane Add_Student;
	@FXML
	private Label adding_std_lvl;
	@FXML
	private TextField txt_student_id;
	@FXML
	private TextField txt_student_name;
	@FXML
	private TextField txt_intake;
	@FXML
	private TextField txt_section;
	@FXML
	private TextField txt_std_dept;
	@FXML
	private TextField txt_father_name;
	@FXML
	private TextField txt_mother_name;
	@FXML
	private TextField txt_std_dob;
	@FXML
	private TextField txt_std_contact;
	@FXML
	private TextField txt_std_address;
	@FXML
	private TextField txt_std_blood;
	@FXML
	private TextField txt_std_email;
	@FXML
	private TextField txt_std_pass;
	@FXML
	private Button std_add_btn;
	@FXML
	private Button back_from_astd_btn;
	
	
	Connection con;
    PreparedStatement pst;
    
    @FXML
    public void add_std_btn(ActionEvent event) {
    	
    	String std_id = txt_student_id.getText();
    	String std_name = txt_student_name.getText();
    	String std_intake = txt_intake.getText();
    	String std_sec = txt_section.getText();
    	String std_dept = txt_std_dept.getText();
    	String std_fname = txt_father_name.getText();
    	String std_mname = txt_mother_name.getText();
    	String std_dob = txt_std_dob.getText();
    	String std_contact = txt_std_contact.getText();
    	String std_address = txt_std_address.getText();
    	String std_blood = txt_std_blood.getText();
    	String std_email = txt_std_email.getText();
    	String std_pass = txt_std_pass.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("insert into student(student_id, student_name, password, intake, section, department, father_name, mother_name, date_of_birth, contact_no, address, blood_group, email)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1, std_id);
			pst.setString(2, std_name);
			pst.setString(3, std_pass);
			pst.setString(4, std_intake);
			pst.setString(5, std_sec);
			pst.setString(6, std_dept);
			pst.setString(7, std_fname);
			pst.setString(8, std_mname);
			pst.setString(9, std_dob);
			pst.setString(10, std_contact);
			pst.setString(11, std_address);
			pst.setString(12, std_blood);
			pst.setString(13, std_email);
			
			int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Added");
				
				txt_student_id.setText("");
				txt_student_name.setText("");
				txt_intake.setText("");
				txt_section.setText("");
				txt_std_dept.setText("");
				txt_father_name.setText("");
				txt_mother_name.setText("");
				txt_std_dob.setText("");
				txt_std_contact.setText("");
				txt_std_address.setText("");
				txt_std_blood.setText("");
				txt_std_email.setText("");
				txt_std_pass.setText("");
				txt_student_id.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "Record Failed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }

    public void back_from_add_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_modification.fxml"));
		Add_Student.getChildren().setAll(pane);
    }
    
}

