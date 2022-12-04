package com.joatan.pdfmapper.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joatan
 *
 */
public class Student {

	public static final int NAME = 0;
	public static final int STAR_ID = 1;
	public static final int STUDENT_ID = 2;
	public static final int PHONE = 3;
	public static final int EMAIL = 4;
	public static final int COUNTRY = 5;
	public static final int ARRIVAL = 6;
	public static final int PRIOR_EDUCATION = 7;
	public static final int PRIOR_EDUCATION_LEVEL = 8;
	public static final int EDUCATION_US = 9;
	public static final int EDUCATION_US_YEARS = 10;
	public static final int HIGHSCHOOL_GRADUATION = 11;
	public static final int GED_ = 12;
	public static final int HIGHSCHOOL_NAME = 13;
	public static final int GPA_ = 14;

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
	private String primarySecondarySchoolYears;
	private String highschoolGraduation;
	private String highschoolGraduationYear;
	private String GED;
	private String GEDYear;
	private String highschoolName;
	private String GPA;
	public int maxLen;

	public void initFromFields(HashMap<String, String> map) {
		setName(map.get("name"));
		setStudentID(map.get("studentid"));
		setStarID(map.get("starid"));
		setPhone(map.get("phone1") + map.get("phone2") + map.get("phone3"));
		setEmail(map.get("email"));
		setCountry(map.get("countryborn"));
		setArrival(map.get("arrival"));
		setEducation(map.get("formalschooling"));
		setEducationLevel(getEducationLevel(map));
		setSchoolUS(map.get("formalschoolingus"));
		setprimarySecondarySchoolYears(map.get("prisec_years"));
		setHighschoolGraduation(map.get("gradschoolus"));
		setHighschoolGraduationYear(map.get("gradschoolusyears"));
		setGED(map.get("ged"));
		setGEDYear(map.get("gedyear"));
		setHighschoolName(getHighSchoolName(map));
		setGPA(getGPA(map));
	}

	private String getEducationLevel(HashMap<String, String> map) {

		String educationLevel = "";

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if ((entry.getKey().contains("None") || entry.getKey().contains("Elementary or Primary School")
					|| entry.getKey().contains("$High School") || entry.getKey().contains("University"))
					&& entry.getValue().contains("Yes")) {
				educationLevel = entry.getKey().replace("$", "");
			}
		}
		return educationLevel;
	}
	
	private String getHighSchoolName(HashMap<String, String> map) {
		String highschoolName = "";
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().contains("check") && entry.getValue().equals("Yes")) {
				highschoolName = entry.getKey().split("\\$")[1];
			}
		}
		return highschoolName;
	}
	
	private String getGPA(HashMap<String, String> map) {
		String gpa = "";
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			if(entry.getKey().contains("gpa") && entry.getValue().equals("Yes")) {
				gpa = entry.getKey().split("\\$")[1];
			}
		}
		
		return gpa;
	}

	@Override
	public String toString() {
		return "Student \nname=" + name + "\nstarID=" + starID + "\nstudentID=" + studentID + "\nphone=" + phone
				+ "\nemail=" + email + "\ncountry=" + country + "\narrival=" + arrival + "\neducation=" + education
				+ "\neducationLevel=" + educationLevel + "\nschoolUS=" + schoolUS + "\nschoolUSYears=" + primarySecondarySchoolYears
				+ "\nhighschoolGraduation=" + highschoolGraduation + "\nhighschoolGraduationYear="
				+ highschoolGraduationYear + "\nGED=" + GED + "\nGEDYear=" + GEDYear + "\nhighschoolName="
				+ highschoolName + "\nGPA=" + GPA;
	}
	
	public Student() {
		super();
		maxLen = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		maxLen = name == null ? maxLen : Math.max(name.length(), maxLen);
	}

	public String getStarID() {
		return starID;
	}

	public void setStarID(String starID) {
		this.starID = starID;
		maxLen = starID == null ? maxLen : Math.max(starID.length(), maxLen);
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
		maxLen = studentID == null ? maxLen : Math.max(studentID.length(), maxLen);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		maxLen = phone == null ? maxLen : Math.max(phone.length(), maxLen);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		maxLen = email == null ? maxLen : Math.max(email.length(), maxLen);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		maxLen = country == null ? maxLen : Math.max(country.length(), maxLen);
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
		maxLen = arrival == null ? maxLen : Math.max(arrival.length(), maxLen);
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
		maxLen = education == null ? maxLen : Math.max(education.length(), maxLen);
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
		maxLen = educationLevel == null ? maxLen : Math.max(educationLevel.length(), maxLen);
	}

	public String getSchoolUS() {
		return schoolUS;
	}

	public void setSchoolUS(String schoolUS) {
		this.schoolUS = schoolUS;
		maxLen = schoolUS == null ? maxLen : Math.max(schoolUS.length(), maxLen);
	}

	public String getprimarySecondarySchoolYears() {
		return primarySecondarySchoolYears;
	}

	public void setprimarySecondarySchoolYears(String schoolUSYears) {
		this.primarySecondarySchoolYears = schoolUSYears;
		maxLen = schoolUSYears == null ? maxLen : Math.max(schoolUSYears.length(), maxLen);
	}

	public String getHighschoolGraduation() {
		return highschoolGraduation;
	}

	public void setHighschoolGraduation(String highschoolGraduation) {
		this.highschoolGraduation = highschoolGraduation;
		maxLen = highschoolGraduation == null ? maxLen : Math.max(highschoolGraduation.length(), maxLen);
	}

	public String getHighschoolGraduationYear() {
		return highschoolGraduationYear;
	}

	public void setHighschoolGraduationYear(String highschoolGraduationYear) {
		this.highschoolGraduationYear = highschoolGraduationYear;
		maxLen = highschoolGraduationYear == null ? maxLen : Math.max(highschoolGraduationYear.length(), maxLen);
	}

	public String getGED() {
		return GED;
	}

	public void setGED(String gED) {
		GED = gED;
		maxLen = gED == null ? maxLen : Math.max(gED.length(), maxLen);
	}

	public String getGEDYear() {
		return GEDYear;
	}

	public void setGEDYear(String gEDYear) {
		GEDYear = gEDYear;
		maxLen = gEDYear == null ? maxLen : Math.max(gEDYear.length(), maxLen);
	}

	public String getHighschoolName() {
		return highschoolName;
	}

	public void setHighschoolName(String highschoolName) {
		this.highschoolName = highschoolName;
		maxLen = highschoolName == null ? maxLen : Math.max(highschoolName.length(), maxLen);
	}

	public String getGPA() {
		return GPA;
	}

	public void setGPA(String gPA) {
		GPA = gPA;
		maxLen = gPA == null ? maxLen : Math.max(gPA.length(), maxLen);
	}

}
