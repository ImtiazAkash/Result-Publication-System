package application;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class tableview_controller implements Initializable {

	@FXML
    private AnchorPane show_result;

    @FXML
    private TextField get_sid;

    
    @FXML
    private TableView<Modeltable> table;

    @FXML
    private TableColumn<Modeltable, String> Course_code;

    @FXML
    private TableColumn<Modeltable, String> course_title;

    @FXML
    private TableColumn<Modeltable, String> credit;

    @FXML
    private TableColumn<Modeltable, String> intk_sec;

    @FXML
    private TableColumn<Modeltable, String> type;

    @FXML
    private TableColumn<Modeltable, String> marks_final;

    @FXML
    private TableColumn<Modeltable, String> marks_mid;

    @FXML
    private TableColumn<Modeltable, String> marks_thirty;

    @FXML
    private TableColumn<Modeltable, String> marks_total;
    @FXML 
    private Button back_from_show_result_btn;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
	
    ObservableList<Modeltable> oblist = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//String std_id = get_sid.getText();
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/result_publication", "root", "");
			
	
			pst = con.prepareStatement("SELECT marks.course_code, course_title, credit, intake_sec, type, final, mid, thirty, (final+mid+thirty) FROM marks INNER JOIN course on marks.course_code=course.course_code where student_id = '"+studentController.user_id+"'");
			
			rs=pst.executeQuery();
			while(rs.next()) {
				oblist.add(new Modeltable(rs.getString("marks.course_code"), rs.getString("course_title"), rs.getString("credit"), rs.getString("intake_sec"), 
						rs.getString("type"), rs.getString("final"), rs.getString("mid"), rs.getString("thirty"), rs.getString("(final+mid+thirty)")));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Course_code.setCellValueFactory(new PropertyValueFactory<>("course_code"));
		course_title.setCellValueFactory(new PropertyValueFactory<>("course_title"));
		credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
		intk_sec.setCellValueFactory(new PropertyValueFactory<>("intk_sec"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		marks_final.setCellValueFactory(new PropertyValueFactory<>("marks_final"));
		marks_mid.setCellValueFactory(new PropertyValueFactory<>("marks_mid"));
		marks_thirty.setCellValueFactory(new PropertyValueFactory<>("marks_thirty"));
		marks_total.setCellValueFactory(new PropertyValueFactory<>("marks_total"));
		
		
		table.setItems(oblist);
	}

}
