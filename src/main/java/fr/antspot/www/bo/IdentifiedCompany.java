package fr.antspot.www.bo;

import java.util.ArrayList;
import java.util.List;

public class IdentifiedCompany {

	private String companyName;
	private String companySize;
	private String companyDomain;

	private List<String> parsedPhoneNumber;
	private List<IdentifiedContact> identifiedContacts;

	public IdentifiedCompany(String companyName, String companySize, String companyDomain,
			List<String> parsedPhoneNumber, List<IdentifiedContact> identifiedContacts) {
		super();
		this.companyName = companyName;
		this.companySize = companySize;
		this.companyDomain = companyDomain;
		this.parsedPhoneNumber = parsedPhoneNumber;
		this.identifiedContacts = identifiedContacts;
	}

	public IdentifiedCompany(String companyName, String companySize, String companyDomain) {
		super();
		this.companyName = companyName;
		this.companySize = companySize;
		this.companyDomain = companyDomain;
		this.parsedPhoneNumber = new ArrayList<String>();
		this.identifiedContacts = new ArrayList<IdentifiedContact>();
	}

	public IdentifiedCompany() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanyDomain() {
		return companyDomain;
	}

	public void setCompanyDomain(String companyDomain) {
		this.companyDomain = companyDomain;
	}

	public List<String> getParsedPhoneNumber() {
		return parsedPhoneNumber;
	}

	public void setParsedPhoneNumber(List<String> parsedPhoneNumber) {
		this.parsedPhoneNumber = parsedPhoneNumber;
	}

	public List<IdentifiedContact> getIdentifiedContacts() {
		return identifiedContacts;
	}

	public void setIdentifiedContacts(List<IdentifiedContact> identifiedContacts) {
		this.identifiedContacts = identifiedContacts;
	}

	@Override
	public String toString() {
		return "IdentifiedCompany [companyName=" + companyName + ", companySize=" + companySize + ", companyDomain="
				+ companyDomain + ", parsedPhoneNumber=" + parsedPhoneNumber + ", identifiedContacts="
				+ identifiedContacts + "]";
	}

}
