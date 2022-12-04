package com.joatan.pdfmapper.main;
import com.joatan.pdfmapper.handlers.ExcelHandler;

public class Driver {

	
	public static void main(String[] args) throws Exception{

		
		ExcelHandler excel = new ExcelHandler();
		
		excel.createWorkbook();
		excel.addStudent("student1.pdf", 1);
		excel.addStudent("student2.pdf", 2);
		excel.addStudent("student3.pdf", 3);

	}
	
    

}