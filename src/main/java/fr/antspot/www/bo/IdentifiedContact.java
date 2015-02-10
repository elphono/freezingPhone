package fr.antspot.www.bo;

public class IdentifiedContact {
	private String linkedInWebLink;
	private String linkedInLocation;
	private String linkedInRole;
	
	private String nom;
	private String prenom;
	private String email;

	public IdentifiedContact(String linkedInWebLink, String linkedInLocation, String linkedInRole) {
		super();
		this.linkedInWebLink = linkedInWebLink;
		this.linkedInLocation = linkedInLocation;
		this.linkedInRole = linkedInRole;
	}

	public IdentifiedContact() {
		super();
	}

	public String getLinkedInWebLink() {
		return linkedInWebLink;
	}

	public void setLinkedInWebLink(String linkedInWebLink) {
		this.linkedInWebLink = linkedInWebLink;
	}

	public String getLinkedInLocation() {
		return linkedInLocation;
	}

	public void setLinkedInLocation(String linkedInLocation) {
		this.linkedInLocation = linkedInLocation;
	}

	public String getLinkedInRole() {
		return linkedInRole;
	}

	public void setLinkedInRole(String linkedInRole) {
		this.linkedInRole = linkedInRole;
	}

	@Override
	public String toString() {
		return "IdentifiedContact [linkedInWebLink=" + linkedInWebLink + ", linkedInLocation=" + linkedInLocation
				+ ", linkedInRole=" + linkedInRole + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
