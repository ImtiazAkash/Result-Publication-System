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

public class Admin_Search_Teacher {
	@FXML
	private AnchorPane search_Teacher;
	@FXML
    private Label teacher_searching_lvl;

    @FXML
    private TextField teacher_search_txt;

    @FXML
    private Button search_btn;

    @FXML
    private TextField result_tID;

    @FXML
    private Label search_id_lvl;

    @FXML
    private Label search_name_lvl;

    @FXML
    private TextField result_tname;

    @FXML
    private Label search_dept_lvl;

    @FXML
    private TextField result_tdept;

    @FXML
    private Label search_desig_lvl;

    @FXML
    private TextField result_tdesig;

    @FXML
    private Label search_contact_lvl;

    @FXML
    private TextField result_tcont;

    @FXML
    private Label search_gen_lvl;

    @FXML
    private TextField result_tgen;

    @FXML
    private Label search_add_lvl;

    @FXML
    private TextField result_tadd;

    @FXML
    private Label search_email_lvl;

    @FXML
    private TextField result_temail;
    @FXML
    private Button back_from_tsearch_btn;
	
	
	Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void search_btn(ActionEvent event) {
    	String get_tID = teacher_search_txt.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			pst = con.prepareStatement("select teacher_id, teacher_name, department, designation, Contact_num, gender, address, email from teacher where teacher_id=?");
			pst.setString(1, get_tID);
			
			rs=pst.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Record found");
				
				String tID = rs.getString("teacher_id");
				result_tID.setText(tID);
		        String tname = rs.getString("teacher_name");
		        result_tname.setText(tname);
		        String tdept = rs.getString("department");
		        result_tdept.setText(tdept);
		        String tdes = rs.getString("designation");
		        result_tdesig.setText(tdes);
		        String tcont = rs.getString("Contact_num");
		        result_tcont.setText(tcont);
		        String tgen = rs.getString("gender");
		        result_tgen.setText(tgen);
		        String tadd = rs.getString("address");
		        result_tadd.setText(tadd);
		        String temail = rs.getString("email");
		        result_temail.setText(temail);
				
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

    public void back_from_search_teacher(ActionEvent event) throws IOException { //this get us back to Admin panel from teacher modification
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Teacher_modification.fxml"));
		search_Teacher.getChildren().setAll(pane);
    }

}
