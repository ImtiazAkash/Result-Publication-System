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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

public class Teacher_Edit_Result {
	@FXML
	private AnchorPane editing_result;
	@FXML
	private TextField upmarks_std_id;
	@FXML
	private TextField upmarks_course_no;
	@FXML
	private TextField upmid_marks;
	@FXML
	private TextField upfinal_marks;
	@FXML
	private TextField upout_of_thirty;
	@FXML
	private Button update_result_btn;
	@FXML
	private Button back_from_eresult_btn;

	Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    public void update_marks(ActionEvent event) {
    	
    	String up_sID = upmarks_std_id.getText();
    	String up_course_code = upmarks_course_no.getText();
    	String up_marks_mid = upmid_marks.getText();
    	String up_marks_final = upfinal_marks.getText();
    	String up_marks_thirty = upout_of_thirty.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("update marks set mid=?, final=?, thirty=? where student_id='"+up_sID+"' and course_code='"+up_course_code+"'");
			
			pst.setString(1, up_marks_mid);
			pst.setString(2, up_marks_final);
			pst.setString(3, up_marks_thirty);
			
			int status = pst.executeUpdate();
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Updated");
				
		        upmarks_std_id.setText("");
		        upmarks_course_no.setText("");
		        upmid_marks.setText("");
		        upfinal_marks.setText("");
		        upout_of_thirty.setText("");
		        upmarks_std_id.requestFocus();
		        
			}
			else {
				JOptionPane.showMessageDialog(null, "Record Does not Exists");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    public void back_from_edit_result(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/TeacherPanel.fxml"));
		editing_result.getChildren().setAll(pane);
    }

}
