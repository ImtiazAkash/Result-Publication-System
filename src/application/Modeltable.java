package application;

public class Modeltable {

	String course_code, course_title, credit, intk_sec, type, marks_final, marks_mid, marks_thirty, marks_total;
	
	public Modeltable(String course_code, String course_title, String credit, String intk_sec, String type, String marks_final, String marks_mid, String marks_thirty, String marks_total) {
		this.course_code = course_code;
		this.course_title = course_title;
		this.credit = credit;
		this.intk_sec = intk_sec;
		this.type = type;
		this.marks_final = marks_final;
		this.marks_mid = marks_mid;
		this.marks_thirty = marks_thirty;
		this.marks_total = marks_total;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getCourse_title() {
		return course_title;
	}

	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getIntk_sec() {
		return intk_sec;
	}

	public void setIntk_sec(String intk_sec) {
		this.intk_sec = intk_sec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarks_final() {
		return marks_final;
	}

	public void setMarks_final(String marks_final) {
		this.marks_final = marks_final;
	}

	public String getMarks_mid() {
		return marks_mid;
	}

	public void setMarks_mid(String marks_mid) {
		this.marks_mid = marks_mid;
	}

	public String getMarks_thirty() {
		return marks_thirty;
	}

	public void setMarks_thirty(String marks_thirty) {
		this.marks_thirty = marks_thirty;
	}

	public String getMarks_total() {
		return marks_total;
	}

	public void setMarks_total(String marks_total) {
		this.marks_total = marks_total;
	}
	
	
	
	
}
