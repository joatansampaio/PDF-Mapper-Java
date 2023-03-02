package com.joatan.pdfmapper.handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFHandler {

	PDDocument pdf;

	public void loadPDF(String path) {
		try {
			pdf = PDDocument.load(new File(path));
		}catch(IOException ioException) {
			System.out.println("I/O Exception.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getFormFieldsAndValues() throws Exception {
		PDDocumentCatalog catalog = pdf.getDocumentCatalog();
		PDAcroForm form = catalog.getAcroForm();
		if (form == null) {
			System.out.println("No form");
			return null;
		}
		List<PDField> fields = form.getFields();

		HashMap<String, String> formFields = new HashMap<String, String>();

		for (PDField field : fields) {
			String fieldValue = field.getValueAsString();
			String fieldName = field.getFullyQualifiedName();

			formFields.put(fieldName, fieldValue);
		}

		return formFields;
	}

}
