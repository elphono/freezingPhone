package fr.antspot.www.util;
public class ExcelCriteria {
	private String criteriaName;
	private String criteriaValue;
	private int columnIndex;

	public ExcelCriteria(String criteriaName, String criteriaValue) {
		super();
		this.criteriaName = criteriaName;
		this.criteriaValue = criteriaValue;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public String getCriteriaValue() {
		return criteriaValue;
	}

	public void setCriteriaValue(String criteriaValue) {
		this.criteriaValue = criteriaValue;
	}
}
