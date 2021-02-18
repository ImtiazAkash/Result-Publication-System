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

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class Admin_Search_Student {
	@FXML
	private AnchorPane search_student;
	@FXML
	private Label student_searching_lvl;
	@FXML
	private TextField std_search_txt;
	@FXML
	private Button std_search_btn;
	@FXML
	private TextField std_search_id;
	@FXML
	private Label std_search_id_lvl;
	@FXML
	private Label std_search_name_lvl;
	@FXML
	private TextField std_search_name;
	@FXML
	private Label std_search_intake_lvl;
	@FXML
	private TextField std_search_intk;
	@FXML
	private Label std_search_section_lvl;
	@FXML
	private TextField std_search_sec;
	@FXML
	private Label std_search_dept_lvl;
	@FXML
	private TextField std_search_dept;
	@FXML
	private Label std_search_fname_lvl;
	@FXML
	private TextField std_search_fname;
	@FXML
	private Label std_search_mname_lvl;
	@FXML
	private TextField std_search_mname;
	@FXML
	private Label std_search_dob_lvl;
	@FXML
	private TextField std_search_dob;
	@FXML
	private Label std_search_cont_lvl;
	@FXML
	private TextField std_search_cont;
	@FXML
	private Label std_search_address_lvl;
	@FXML
	private TextField std_search_address;
	@FXML
	private Label std_search_blood_lvl;
	@FXML
	private TextField std_search_blood;
	@FXML
	private Label std_search_email_lvl;
	@FXML
	private TextField std_search_email;
	@FXML
	private Button back_from_sstd_btn;

	
	Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void std_search_btn(ActionEvent event) {
    	String get_tID = std_search_txt.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			pst = con.prepareStatement("select student_name, intake, section, department, father_name, mother_name, date_of_birth, contact_no, address, blood_group, email from student where student_id=?");
			pst.setString(1, get_tID);
			
			rs=pst.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Record found");
				
		        String std_name = rs.getString("student_name");
		        std_search_name.setText(std_name);
		        String std_intk = rs.getString("intake");
		        std_search_intk.setText(std_intk);
		        String std_sec = rs.getString("section");
		        std_search_sec.setText(std_sec);
		        String std_dept = rs.getString("department");
		        std_search_dept.setText(std_dept);
		        String std_fname = rs.getString("father_name");
		        std_search_fname.setText(std_fname);
		        String std_mname = rs.getString("mother_name");
		        std_search_mname.setText(std_mname);
		        String std_dob = rs.getString("date_of_birth");
		        std_search_dob.setText(std_dob);
		        String std_cont = rs.getString("contact_no");
		        std_search_cont.setText(std_cont);
		        String std_add = rs.getString("address");
		        std_search_address.setText(std_add);
		        String std_blood = rs.getString("blood_group");
		        std_search_blood.setText(std_blood);
		        String std_email = rs.getString("email");
		        std_search_email.setText(std_email);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Record not found");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void back_from_search_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_modification.fxml"));
		search_student.getChildren().setAll(pane);
    }
    
}
