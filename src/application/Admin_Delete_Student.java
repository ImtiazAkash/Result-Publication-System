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

public class Admin_Delete_Student {
	@FXML
	private AnchorPane delete_student;
	@FXML
	private Label delete_student_lvl;
	@FXML
	private TextField std_delete_txt;
	@FXML
	private TextField std_delete_name;
	@FXML
	private Button std_delete_btn;
	@FXML
	private Button back_from_sdelete_btn;


	Connection con;
    PreparedStatement pst;
	
	public void delete_btn(ActionEvent event) {
		String std_ID = std_delete_txt.getText();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			pst = con.prepareStatement("delete from student where student_id='"+std_ID+"'");
			
			
			int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Deleted");
				
				std_delete_txt.setText("");
				std_delete_name.setText("");
				
				std_delete_txt.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "Record does not exist");
			    
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void back_from_delete_student(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Student_modification.fxml"));
		delete_student.getChildren().setAll(pane);
    }
	
}
