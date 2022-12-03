import java.util.HashMap;
import java.util.Map;

public class Student {

	private String name;
	private String starID;
	private String studentID;
	private String phone;
	private String email;
	private String country;
	private String arrival;
	private String education;
	private String educationLevel;
	private String schoolUS;
	private String schoolUSYears;
	private String highschoolGraduation;
	private String highschoolGraduationYear;
	private String GED;
	private String GEDYear;
	private String highschoolName;
	private String GPA;

	public Student() {
		super();
	}

	public Student(String name, String starID, String studentID, String phone, String email, String country,
			String arrival, String education, String educationLevel, String schoolUS, String schoolUSYears,
			String highschoolGraduation, String highschoolGraduationYear, String gED, String gEDYear,
			String highschoolName, String gPA) {
		super();
		this.name = name;
		this.starID = starID;
		this.studentID = studentID;
		this.phone = phone;
		this.email = email;
		this.country = country;
		this.arrival = arrival;
		this.education = education;
		this.educationLevel = educationLevel;
		this.schoolUS = schoolUS;
		this.schoolUSYears = schoolUSYears;
		this.highschoolGraduation = highschoolGraduation;
		this.highschoolGraduationYear = highschoolGraduationYear;
		GED = gED;
		GEDYear = gEDYear;
		this.highschoolName = highschoolName;
		GPA = gPA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStarID() {
		return starID;
	}

	public void setStarID(String starID) {
		this.starID = starID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getSchoolUS() {
		return schoolUS;
	}

	public void setSchoolUS(String schoolUS) {
		this.schoolUS = schoolUS;
	}

	public String getSchoolUSYears() {
		return schoolUSYears;
	}

	public void setSchoolUSYears(String schoolUSYears) {
		this.schoolUSYears = schoolUSYears;
	}

	public String getHighschoolGraduation() {
		return highschoolGraduation;
	}

	public void setHighschoolGraduation(String highschoolGraduation) {
		this.highschoolGraduation = highschoolGraduation;
	}

	public String getHighschoolGraduationYear() {
		return highschoolGraduationYear;
	}

	public void setHighschoolGraduationYear(String highschoolGraduationYear) {
		this.highschoolGraduationYear = highschoolGraduationYear;
	}

	public String getGED() {
		return GED;
	}

	public void setGED(String gED) {
		GED = gED;
	}

	public String getGEDYear() {
		return GEDYear;
	}

	public void setGEDYear(String gEDYear) {
		GEDYear = gEDYear;
	}

	public String getHighschoolName() {
		return highschoolName;
	}

	public void setHighschoolName(String highschoolName) {
		this.highschoolName = highschoolName;
	}

	public String getGPA() {
		return GPA;
	}

	public void setGPA(String gPA) {
		GPA = gPA;
	}

	public void initFromPDF(HashMap<String, String> map) {
		
		this.name = map.get("name");
		this.studentID = map.get("studentid");
		this.starID = map.get("starid");
		this.phone = map.get("phone1") + map.get("phone2") + map.get("phone3");
		this.email = map.get("email");
		this.country = map.get("countryborn");
		this.arrival = map.get("arrival");
 		
		System.out.println(this.toString());
		for (Map.Entry<String, String> foo : map.entrySet()) {
			if (foo.getKey().contains("check"))
				if (!foo.getValue().equals("Off"))
					System.out.println(foo.getKey() + " | " + foo.getValue());
		}
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", starID=" + starID + ", studentID=" + studentID + ", phone=" + phone
				+ ", email=" + email + ", country=" + country + ", arrival=" + arrival + ", education=" + education
				+ ", educationLevel=" + educationLevel + ", schoolUS=" + schoolUS + ", schoolUSYears=" + schoolUSYears
				+ ", highschoolGraduation=" + highschoolGraduation + ", highschoolGraduationYear="
				+ highschoolGraduationYear + ", GED=" + GED + ", GEDYear=" + GEDYear + ", highschoolName="
				+ highschoolName + ", GPA=" + GPA + "]";
	}

}
