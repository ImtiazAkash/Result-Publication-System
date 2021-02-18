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

public class Teacher_Add_Result {
	@FXML
	private AnchorPane adding_result;
	@FXML
	private TextField marks_std_id;
	@FXML
	private TextField marks_intk_sec;
	@FXML
	private TextField marks_course_no;
	@FXML
	private TextField mid_marks;
	@FXML
	private TextField final_marks;
	@FXML
	private TextField out_of_thirty;
	@FXML
	private Button add_result_btn;
	@FXML
	private Button back_from_aresult_btn;
	
	Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    public void add_marks(ActionEvent event) {
    	
    	String sID = marks_std_id.getText();
    	String sIntk_section = marks_intk_sec.getText();
    	String course_code = marks_course_no.getText();
    	String marks_mid = mid_marks.getText();
    	String marks_final = final_marks.getText();
    	String marks_thirty = out_of_thirty.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
			pst = con.prepareStatement("insert into marks(student_id, intake_sec, course_code, mid, final, thirty)values(?,?,?,?,?,?)");
			pst.setString(1, sID);
			pst.setString(2, sIntk_section);
			pst.setString(3, course_code);
			pst.setString(4, marks_mid);
			pst.setString(5, marks_final);
			pst.setString(6, marks_thirty);
			
			int status = pst.executeUpdate();
			if(status == 1) {
				JOptionPane.showMessageDialog(null, "Record Added");
				
		        marks_std_id.setText("");
		        marks_intk_sec.setText("");
		        marks_course_no.setText("");
		        mid_marks.setText("");
		        final_marks.setText("");
		        out_of_thirty.setText("");
		        marks_std_id.requestFocus();
		        
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


    public void back_from_add_result(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/TeacherPanel.fxml"));
		adding_result.getChildren().setAll(pane);
    }

}
