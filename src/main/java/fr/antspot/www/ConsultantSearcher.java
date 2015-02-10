package fr.antspot.www;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.antspot.www.bo.ExcelResults;
import fr.antspot.www.bo.IdentifiedCompany;
import fr.antspot.www.bo.IdentifiedContact;
import fr.antspot.www.out.Pojo2ExcelSheetWriter;
import fr.antspot.www.services.GoogleQueryReaderService;
import fr.antspot.www.services.JsonParserService;
import fr.antspot.www.util.ExcelCriteria;
import fr.antspot.www.util.ExcelFileHandler;
import fr.antspot.www.util.ExcelReaderService;

public class ConsultantSearcher {

	/**
	 * Compteur technique. En "prod" n'est pas censé être là
	 */
	private static final int SEARCH_COUNT_LIMIT = 500;
	/**
	 * Profondeur de la recherche google
	 */
	private static final int NUMBER_OF_GOOGLE_PAGES_TO_SEARCH = 1;
	private static final List<String> REGIONS_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("TOULON", "VAR",
			"PACA", "MARSEILLE", "NICE", "SOPHIA"));
	private static final List<String> ROLES_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("HEAD", "CHEF",
			"MANAGER", "PROJECT", "PROJET", "DIRECTEUR", "DIRECTOR", "IT", "INFORMATIQUE", "SI","INGENIEUR","ENGINEER","CONSULTANT"));

	/**
	 * On commence à traiter le fichier excel d'entrée à partir de l'entrée N
	 */
	private static final Integer STARTING_ENTRIES_IN_EXCEL_FILE = 2;

	public static void main(String[] args) throws Exception {
		String searchURL = "https://www.googleapis.com/customsearch/v1?";

		ExcelFileHandler handler = new ExcelFileHandler(new FileInputStream(
				"C:\\Users\\elphono\\Downloads\\Amaris\\listePrestas.xlsx"), 0);
		List<ExcelCriteria> criterias = new ArrayList<ExcelCriteria>();
		List<ExcelResults> listResults = ExcelReaderService.searchSourceExcelWithCriteria(handler, criterias);
		System.out.println("Excel results:");

		int searchCurrentCount = 1;
		int startCounter = 0;
		List<IdentifiedContact> lListContactTotal = new ArrayList<IdentifiedContact>();
		for (ExcelResults res : listResults) {
			if (startCounter++ < STARTING_ENTRIES_IN_EXCEL_FILE) {
				continue;
			}
			if (SEARCH_COUNT_LIMIT != 0 && searchCurrentCount++ > SEARCH_COUNT_LIMIT) {
				break;
			}
			try {
				IdentifiedContact lIdentifiedContact = new IdentifiedContact();
				lIdentifiedContact.setPrenom(res.getResultAtIndex(2));
				lIdentifiedContact.setNom(res.getResultAtIndex(4));
				lIdentifiedContact.setEmail(res.getResultAtIndex(3));
				List<IdentifiedContact> listContact = new ArrayList<IdentifiedContact>();
				System.out.println("On essaye de recuperer des noms de personnes grace à LinkedIn");
				String searchKeywords = "site:fr.linkedin.com+(inurl:in+|+inurl:pub)+-inurl:dir+-inurl:title+-inurl:jobs2+-inurl:jobs+"
						+ lIdentifiedContact.getPrenom().replaceAll("\\s+", "+")
						+ "+"
						+ lIdentifiedContact.getNom().replaceAll("\\s+", "+");
//						+ "+it-ec";
				System.out.println(searchKeywords);
				for (int i = 0; i < NUMBER_OF_GOOGLE_PAGES_TO_SEARCH; i++) {
					JsonParserService.lookForLinkedInContactsInJSON(
							GoogleQueryReaderService.read(searchURL, searchKeywords, 0, 10), listContact,
							null, null,lIdentifiedContact);
				}
				for (IdentifiedContact id : listContact) {
					id.setNom(lIdentifiedContact.getNom());
					id.setPrenom(lIdentifiedContact.getPrenom());
					id.setEmail(lIdentifiedContact.getEmail());
				}
				lListContactTotal.addAll(listContact);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		// Maintenant on écrit le résultat de la liste de companies dans un
		// excel (ou autre, choisir le writer approprié)
		Pojo2ExcelSheetWriter lPojoWriter = new Pojo2ExcelSheetWriter(new ExcelFileHandler(new FileOutputStream(
				"C:\\Users\\elphono\\Downloads\\Amaris\\listContactEnrichie.xlsx")));
		lPojoWriter.writeContact2Excel(lListContactTotal);
	}
}
