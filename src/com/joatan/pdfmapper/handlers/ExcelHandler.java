package com.joatan.pdfmapper.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.joatan.pdfmapper.entity.Student;

public class ExcelHandler {

	private static String HEADER[] = { "Name", "Star ID", "Student ID", "Phone", "Email", "Country", "Arrival",
			"Education", "Ed. Level", "Ed. USA", "Pri/Secondary Sch Years", "Highschool Grad", "GED", "Highchool",
			"GPA" };

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow header;
	XSSFRow row;
	XSSFCellStyle headerStyle;
	XSSFCellStyle style;
	XSSFCell cell;

	public void createWorkbook() {

		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Students");
		header = sheet.createRow(0);
		headerStyle = workbook.createCellStyle();
		style = workbook.createCellStyle();

		sheet.addIgnoredErrors(new CellRangeAddress(0, 9999, 0, 9999), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

		sheet.setColumnWidth(Student.NAME, 8000);
		sheet.setColumnWidth(Student.STAR_ID, 3500);
		sheet.setColumnWidth(Student.STUDENT_ID, 4000);
		sheet.setColumnWidth(Student.PHONE, 6000);
		sheet.setColumnWidth(Student.EMAIL, 8000);
		sheet.setColumnWidth(Student.COUNTRY, 4000);
		sheet.setColumnWidth(Student.ARRIVAL, 3000);
		sheet.setColumnWidth(Student.PRIOR_EDUCATION, 5500);
		sheet.setColumnWidth(Student.PRIOR_EDUCATION_LEVEL, 5500);
		sheet.setColumnWidth(Student.EDUCATION_US, 5000);
		sheet.setColumnWidth(Student.EDUCATION_US_YEARS, 8000);
		sheet.setColumnWidth(Student.HIGHSCHOOL_GRADUATION, 8000);
		sheet.setColumnWidth(Student.GED_, 3000);
		sheet.setColumnWidth(Student.HIGHSCHOOL_NAME, 7000);
		sheet.setColumnWidth(Student.GPA_, 6000);
		sheet.setDefaultRowHeight((short) 450);

		sheet.setZoom(85);
		sheet.setVerticallyCenter(true);

		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		header.setHeight((short) (30 * 20));

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeight(12);
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(font);

		Cell headerCell;

		for (int i = 0; i < 15; i++) {
			headerCell = header.createCell(i);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue(HEADER[i]);
		}

	}

	public void addStudent(String src, int rowNumber) throws Exception {

		PDFHandler pdf = new PDFHandler();
		Student student = new Student();
		pdf.loadPDF(src);
		student.initFromFields(pdf.getFormFieldsAndValues());

		row = sheet.createRow(rowNumber);

		XSSFFont cellFontStyle = ((XSSFWorkbook) workbook).createFont();
		cellFontStyle.setFontName("Arial");
		cellFontStyle.setFontHeight(12);
		style.setWrapText(true);
		style.setFont(cellFontStyle);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		cell = row.createCell(Student.NAME);
		cell.setCellValue(student.getName());
		cell.setCellStyle(style);

		cell = row.createCell(Student.STAR_ID);
		cell.setCellValue(student.getStarID());
		cell.setCellStyle(style);

		cell = row.createCell(Student.STUDENT_ID);
		cell.setCellValue(student.getStudentID());
		cell.setCellStyle(style);

		cell = row.createCell(Student.PHONE);
		cell.setCellFormula(student.getPhone());
		cell.setCellStyle(style);

		cell = row.createCell(Student.EMAIL);
		cell.setCellValue(student.getEmail());
		cell.setCellStyle(style);

		cell = row.createCell(Student.COUNTRY);
		cell.setCellValue(student.getCountry());
		cell.setCellStyle(style);

		cell = row.createCell(Student.ARRIVAL);
		cell.setCellValue(student.getArrival());
		cell.setCellStyle(style);

		cell = row.createCell(Student.PRIOR_EDUCATION);
		cell.setCellValue(student.getSchoolUS());
		cell.setCellStyle(style);

		cell = row.createCell(Student.PRIOR_EDUCATION_LEVEL);
		cell.setCellValue(student.getEducationLevel());
		cell.setCellStyle(style);

		cell = row.createCell(Student.EDUCATION_US);
		cell.setCellValue(student.getEducation());
		cell.setCellStyle(style);

		cell = row.createCell(Student.EDUCATION_US_YEARS);
		cell.setCellValue(student.getprimarySecondarySchoolYears());
		cell.setCellStyle(style);

		cell = row.createCell(Student.HIGHSCHOOL_GRADUATION);
		cell.setCellValue(student.getHighschoolGraduation());
		cell.setCellStyle(style);

		cell = row.createCell(Student.GED_);
		cell.setCellValue(student.getGED() + " " + student.getGEDYear());
		cell.setCellStyle(style);

		cell = row.createCell(Student.HIGHSCHOOL_NAME);
		cell.setCellValue(student.getHighschoolName());
		cell.setCellStyle(style);

		cell = row.createCell(Student.GPA_);
		cell.setCellValue(student.getGPA());
		cell.setCellStyle(style);

		row.setHeight((short) (student.maxLen * 20));
		System.out.println(student.maxLen);

		write();

	}

	public void write() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "ESOL.xlsx";

		FileOutputStream outputStream;

		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			System.out.println("File saved!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
