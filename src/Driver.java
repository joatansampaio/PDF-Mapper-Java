import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Driver {
	private static final int NAME = 0;
	private static final int STAR_ID = 1;
	private static final int STUDENT_ID = 2;
	private static final int PHONE = 3;
	private static final int EMAIL = 4;
	private static final int COUNTRY = 5;
	private static final int ARRIVAL = 6;
	private static final int PRIOR_EDUCATION = 7;
	private static final int PRIOR_EDUCATION_LEVEL = 8;
	private static final int EDUCATION_US = 9;
	private static final int EDUCATION_US_YEARS = 10;
	private static final int HIGHSCHOOL_GRADUATION = 11;
	private static final int GED = 12;
	private static final int HIGHSCHOOL_NAME = 13;
	private static final int GPA = 14;

	private static String HEADER[] = { "Name", "Star ID", "Student ID", "Phone", "Email", "Country", "Arrival",
			"Education", "Ed. Level", "Ed. USA", "Years Ed. US", "Highschool Grad", "GED", "Highchool", "GPA" };

	public static void main(String[] args) {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Students");
		
		sheet.setColumnWidth(NAME, 8000);
		sheet.setColumnWidth(STAR_ID, 3500);
		sheet.setColumnWidth(STUDENT_ID, 4000);
		sheet.setColumnWidth(PHONE, 6000);
		sheet.setColumnWidth(EMAIL, 8000);
		sheet.setColumnWidth(COUNTRY, 4000);
		sheet.setColumnWidth(ARRIVAL, 3000);
		sheet.setColumnWidth(PRIOR_EDUCATION, 5500);
		sheet.setColumnWidth(PRIOR_EDUCATION_LEVEL, 5500);
		sheet.setColumnWidth(EDUCATION_US, 5000);
		sheet.setColumnWidth(EDUCATION_US_YEARS, 8000);
		sheet.setColumnWidth(HIGHSCHOOL_GRADUATION, 8000);
		sheet.setColumnWidth(GED, 3000);
		sheet.setColumnWidth(HIGHSCHOOL_NAME, 5000);
		sheet.setColumnWidth(GPA, 3000);
		sheet.setDefaultRowHeight((short) 450);

		sheet.setZoom(85);
		sheet.setVerticallyCenter(true);

		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeight(12);
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(font);
		
		XSSFFont font2 = ((XSSFWorkbook) workbook).createFont();
		font2.setFontName("Arial");
		font2.setFontHeight(12);

		Cell headerCell;

		for (int i = 0; i < 15; i++) {
			headerCell = header.createCell(i);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue(HEADER[i]);
		}
		
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setFont(font2);

		Row row = sheet.createRow(1);
		Cell cell;
		
		cell = row.createCell(NAME);
		cell.setCellValue("Joatan Sampaio");
		cell.setCellStyle(style);

		cell = row.createCell(STAR_ID);
		cell.setCellValue("fe4194fm");
		cell.setCellStyle(style);
		
		cell = row.createCell(STUDENT_ID);
		cell.setCellFormula("9090808");
		cell.setCellStyle(style);
		
		cell = row.createCell(PHONE);
		cell.setCellValue("612 298 6700");
		cell.setCellStyle(style);
		
		cell = row.createCell(EMAIL);
		cell.setCellValue("joatansampaio@live.com");
		cell.setCellStyle(style);

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "ESOL.xlsx";

		FileOutputStream outputStream;

		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			PDDocument pdf = PDDocument.load(new File("student1.pdf"));
			
			listFields(pdf);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public static void listFields(PDDocument doc) throws Exception {
        PDDocumentCatalog catalog = doc.getDocumentCatalog();
        PDAcroForm form = catalog.getAcroForm();
        List<PDField> fields = form.getFields();

        HashMap<String, String> studentInfo = new HashMap<String, String>();

        for(PDField field: fields) {
            String fieldValue = field.getValueAsString();
            String fieldName = field.getFullyQualifiedName();
           
            studentInfo.put(fieldName, fieldValue);
            
        }
        
        Student test = new Student();
        
        test.initFromPDF(studentInfo);
    }

}
