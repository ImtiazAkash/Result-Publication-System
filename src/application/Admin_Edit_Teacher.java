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

public class Admin_Edit_Teacher {
	@FXML
	private AnchorPane edit_Teacher;
	@FXML
	private Label edit_teacher_lvl;
	@FXML
	private TextField get_teacher_id;
	@FXML
	private Label who_to_edit_lvl;
	@FXML
	private TextField new_designation;
	@FXML
	private Label designation_lvl;
	@FXML
	private Label contact_lvl;
	@FXML
	private TextField new_contact;
	@FXML
	private TextField new_address;
	@FXML
	private Label address_lvl;
	@FXML
	private Button confirm_change_btn;
	@FXML
	private Button back_from_tedit_btn;
	

	Connection con;
	PreparedStatement pst;

	public void confirm_change_btn(ActionEvent event) {

		String teacher_id = get_teacher_id.getText();
		String teacher_designation = new_designation.getText();
		String teacher_contact = new_contact.getText();
		String teacher_address = new_address.getText();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			pst = con.prepareStatement("update teacher set designation=?, Contact_num=?, address=? where teacher_id='"+teacher_id+"'");
			
			pst.setString(1, teacher_designation);
			pst.setString(2, teacher_contact);
			pst.setString(3, teacher_address);
			
			

			int status = pst.executeUpdate();

			if (status == 1) {
				JOptionPane.showMessageDialog(null, "Record Updated");

				get_teacher_id.setText("");
				new_designation.setText("");
				new_contact.setText("");
				new_address.setText("");
				get_teacher_id.requestFocus();
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Teacher ID");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void back_from_edit_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_modification.fxml"));
		edit_Teacher.getChildren().setAll(pane);
    }

}
