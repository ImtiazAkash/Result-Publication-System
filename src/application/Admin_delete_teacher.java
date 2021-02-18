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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Admin_delete_teacher {
	@FXML
	private AnchorPane delete_Teacher;
	@FXML
	private TextField delete_txt;
	@FXML
	private Button delete_btn;
	@FXML
	private Label delete_teacher_lvl;
	@FXML
	private Button back_from_tdelte_btn;
	
	Connection con;
    PreparedStatement pst;
	
	public void delete_btn(ActionEvent event) {
		String tID = delete_txt.getText();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			pst = con.prepareStatement("delete from teacher where teacher_id = ?");
			
			pst.setString(1, tID);
			int status = pst.executeUpdate();
			
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Deleted");
				
				delete_txt.setText("");
				delete_txt.requestFocus();
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

	public void back_from_delete_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_modification.fxml"));
		delete_Teacher.getChildren().setAll(pane);
    }

}
