package fr.antspot.www.bo;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ExcelResults {
	private Integer rowNumber;

	private Map<Integer, String> result;

	public ExcelResults() {
		super();
		this.result = new LinkedHashMap<Integer, String>();
	}

	public void addCellValue(Integer index, String value) {
		result.put(index, value);
	}
	
	public void addCellValue(String value) {
		result.put(result.size(), value);
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	@Override
	public String toString() {
		String resultString = rowNumber.toString() + "/ ";

		for (Entry<Integer, String> entry : result.entrySet()) {
			resultString += entry.getValue() + " // ";
		}
		return resultString;
	}
	
	public String getResultAtIndex(int pIndex){
		return result.get(pIndex);
	}
}
