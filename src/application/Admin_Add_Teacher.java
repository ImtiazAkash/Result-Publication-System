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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Admin_Add_Teacher {

    @FXML
    private AnchorPane Add_Teacher;

    @FXML
    private Label adding_teacher_panel;

    @FXML
    private TextField txt_teacher_name;

    @FXML
    private TextField txt_teacher_id;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_department;

    @FXML
    private TextField txt_designation;

    @FXML
    private TextField txt_contact;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_gender;

    @FXML
    private Button adding_teacher_btn;
    @FXML
    private Button confirm_change_btn;
    
    @FXML
	private Button back_from_tadd;
    
    
	Connection con;
    PreparedStatement pst;
    ResultSet rs;


	
	
	@FXML
	public void add_btn(ActionEvent event) {
		
		String tname = txt_teacher_name.getText();
		String tID = txt_teacher_id.getText();
		String temail = txt_email.getText();
		String tpass = txt_password.getText();
		String tdepart = txt_department.getText();
		String tdesig = txt_designation.getText();
		String tcont = txt_contact.getText();
		String taddress = txt_address.getText();
		String tgender = txt_gender.getText();
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("insert into teacher(teacher_id,teacher_name,password,department,designation,Contact_num,gender,address,email)values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, tID);
			pst.setString(2, tname);
			pst.setString(3, tpass);
			pst.setString(4, tdepart);
			pst.setString(5, tdesig);
			pst.setString(6, tcont);
			pst.setString(7, tgender);
			pst.setString(8, taddress);
			pst.setString(9, temail);
			
			int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Added");
				
				txt_teacher_name.setText("");
				txt_teacher_id.setText("");
				txt_email.setText("");
				txt_password.setText("");
				txt_department.setText("");
				txt_designation.setText("");
				txt_contact.setText("");
				txt_address.setText("");
				txt_gender.setText("");
				txt_teacher_name.requestFocus();
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

	
	public void back_from_add_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_modification.fxml"));
		Add_Teacher.getChildren().setAll(pane);
    }

	
}
