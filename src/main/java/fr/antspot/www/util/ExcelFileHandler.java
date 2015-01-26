package fr.antspot.www.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHandler {
	private XSSFWorkbook workbook;
	private XSSFSheet worksheet;
	private FileOutputStream fos;

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public void setWorksheet(XSSFSheet worksheet) {
		this.worksheet = worksheet;
	}

	public XSSFSheet getWorksheet() {
		return worksheet;
	}

	public ExcelFileHandler(FileInputStream fis, Integer sheetNumber) {
		super();
		try {
			this.workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.worksheet = workbook.getSheetAt(sheetNumber);
	}

	public ExcelFileHandler(FileOutputStream fos) {
		super();
		this.fos = fos;
		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet("Identified companies");
	}

	public void WriteAndFlushWorkbook() throws IOException{
		if(fos!=null && workbook!=null){
			workbook.write(fos);
			fos.close();
		}
	}
}
