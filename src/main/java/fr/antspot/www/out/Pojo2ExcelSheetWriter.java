package fr.antspot.www.out;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import fr.antspot.www.bo.IdentifiedCompany;
import fr.antspot.www.bo.IdentifiedContact;
import fr.antspot.www.util.ExcelFileHandler;

public class Pojo2ExcelSheetWriter {

	ExcelFileHandler mExcelFileHandler = null;

	public Pojo2ExcelSheetWriter(ExcelFileHandler pExcelFileHandler) {
		mExcelFileHandler = pExcelFileHandler;
	}

	public void writeCompany2Excel(List<IdentifiedCompany> pListCompany) {
		int rowCount = 0;
		int maxCellCountReached = 0;
		for (IdentifiedCompany ic : pListCompany) {
			List<IdentifiedContact> lListContact = ic.getIdentifiedContacts();
			List<String> phoneNumbers = ic.getParsedPhoneNumber();
			int cellCount = 0;

			// Création de la premiere ligne correspondant aux infos générales
			// de la compagnie
			Row row = mExcelFileHandler.getWorksheet().createRow(rowCount++);
			row.createCell(cellCount++).setCellValue(ic.getCompanyName());
			row.createCell(cellCount++).setCellValue(ic.getCompanySize());
			row.createCell(cellCount++).setCellValue(ic.getCompanyDomain());
			row.createCell(cellCount++).setCellValue(
					phoneNumbers != null && !phoneNumbers.isEmpty() ? phoneNumbers.get(0) : "");
			if (lListContact != null && !lListContact.isEmpty()) {

				cellCount = 1;
				for (IdentifiedContact lIdentifiedContact : lListContact) {
					row = mExcelFileHandler.getWorksheet().createRow(rowCount++);
					row.createCell(cellCount++).setCellValue(lIdentifiedContact.getLinkedInWebLink());
					row.createCell(cellCount++).setCellValue(lIdentifiedContact.getLinkedInRole());
					row.createCell(cellCount++).setCellValue(lIdentifiedContact.getLinkedInLocation());
				}
			}
			maxCellCountReached = maxCellCountReached < cellCount ? cellCount : maxCellCountReached;
		}
		for (int i = 0; i < maxCellCountReached; i++) {
			mExcelFileHandler.getWorksheet().autoSizeColumn(i);
		}
		try {
			mExcelFileHandler.WriteAndFlushWorkbook();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
