package fr.antspot.www.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import fr.antspot.www.bo.ExcelResults;

public class ExcelReaderService {

	/*
	 * Returns lines from an excel source depending on the list of criteria.
	 * Each criteria associates the name of a column with a value.
	 */
	public static List<ExcelResults> searchSourceExcelWithCriteria(ExcelFileHandler pExcelFileHandler,
			List<ExcelCriteria> pListCriteria) {
		List<ExcelResults> lListExcelResults = new ArrayList<ExcelResults>();
		Iterator<Row> rowIt = pExcelFileHandler.getWorksheet().rowIterator();
		int rowCount = 0;
		while (rowIt.hasNext()) {
			Row lRow = rowIt.next();
			Iterator<Cell> cellIt = lRow.cellIterator();
			Map<Integer, ExcelCriteria> lMapSortedCriteria = new TreeMap<Integer, ExcelCriteria>();
			if (++rowCount == 1) {
				// This is the first line of the sheet, where columns headers
				// should be.
				// We will map each matched ExcelCriteria with the matching
				// column number.
				while (cellIt.hasNext()) {
					Cell lCell = cellIt.next();
					for (ExcelCriteria excelCriteria : pListCriteria) {
						if (excelCriteria.getCriteriaName().equals(lCell.getStringCellValue())) {
							excelCriteria.setColumnIndex(lCell.getColumnIndex());
							lMapSortedCriteria.put(lCell.getColumnIndex(), excelCriteria);
						}
					}
				}
			} else {
				ExcelResults excelResult = new ExcelResults();
				while (cellIt.hasNext()) {

					Cell lCell = cellIt.next();
					try {
						excelResult.setRowNumber(rowCount);

						try {
							excelResult.addCellValue(lCell.getColumnIndex(), lCell.getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								excelResult.addCellValue(lCell.getColumnIndex(),
										String.valueOf(lCell.getNumericCellValue()));
							} catch (IllegalStateException e1) {
//								System.out.println("ignore error");
							}
						}
						ExcelCriteria criteria = lMapSortedCriteria.get(lCell.getColumnIndex());
						if (criteria != null) {
							if (!lCell.getStringCellValue().equals(criteria.getCriteriaValue())) {
								continue;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						// mute
					}
					if (!cellIt.hasNext()) {
						lListExcelResults.add(excelResult);
						continue;
					}
				}
			}
		}
		return lListExcelResults;
	}
}
