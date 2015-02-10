package fr.antspot.www;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.antspot.www.bo.ExcelResults;
import fr.antspot.www.bo.IdentifiedCompany;
import fr.antspot.www.out.Pojo2ExcelSheetWriter;
import fr.antspot.www.services.GoogleQueryReaderService;
import fr.antspot.www.services.JsonParserService;
import fr.antspot.www.util.ExcelCriteria;
import fr.antspot.www.util.ExcelFileHandler;
import fr.antspot.www.util.ExcelReaderService;

public class EntrepriseSearcher {

	/**
	 * Compteur technique. En "prod" n'est pas censé être là
	 */
	private static final int SEARCH_COUNT_LIMIT = 1;
	/**
	 * Profondeur de la recherche google
	 */
	private static final int NUMBER_OF_GOOGLE_PAGES_TO_SEARCH = 50;
	private static final List<String> REGIONS_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("TOULON", "VAR",
			"PACA", "MARSEILLE", "NICE", "SOPHIA"));
	private static final List<String> ROLES_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("HEAD", "CHEF",
			"MANAGER", "PROJECT", "PROJET", "DIRECTEUR", "DIRECTOR", "IT", "INFORMATIQUE", "SI","INGENIEUR","ENGINEER","CONSULTANT"));

	/**
	 * On commence à traiter le fichier excel d'entrée à partir de l'entrée N
	 */
	private static final Integer STARTING_ENTRIES_IN_EXCEL_FILE = 0;

	public static void main(String[] args) throws Exception {
		String searchURL = "https://www.googleapis.com/customsearch/v1?";

		ExcelFileHandler handler = new ExcelFileHandler(new FileInputStream(
				"C:\\Users\\elphono\\Downloads\\Fiches société\\liste\\listeSocietesCibles.xlsx"), 0);
		List<ExcelCriteria> criterias = new ArrayList<ExcelCriteria>();
		criterias.add(new ExcelCriteria("Secteur", "Distribution"));
		List<ExcelResults> listResults = ExcelReaderService.searchSourceExcelWithCriteria(handler, criterias);
		System.out.println("Excel results:");

		int searchCurrentCount = 1;
		int startCounter = 0;
		List<IdentifiedCompany> lListCompanies = new ArrayList<IdentifiedCompany>();
		for (ExcelResults res : listResults) {
			if (startCounter++ < STARTING_ENTRIES_IN_EXCEL_FILE) {
				continue;
			}
			if (SEARCH_COUNT_LIMIT != 0 && searchCurrentCount++ > SEARCH_COUNT_LIMIT) {
				break;
			}
			try {
				IdentifiedCompany lIdentifiedCompany = new IdentifiedCompany(res.getResultAtIndex(0),
						res.getResultAtIndex(1), res.getResultAtIndex(2));
				System.out
						.println("Premiere étape: On récupère les infos de bases sur le site de base (URL et numéro de telephone du standard)");
				String searchKeywords = "inurl:"
						+ res.getResultAtIndex(0).replaceAll("\\W", "*").replaceAll("\\s+", "*") + "*contact";
				System.out.println(searchKeywords);

				JsonParserService.lookForPhoneNumberInJSON(
						GoogleQueryReaderService.read(searchURL, searchKeywords, 1, 5), lIdentifiedCompany,
						ROLES_TO_LOOK_FOR, REGIONS_TO_LOOK_FOR);
				System.out
						.println("Deuxième étape: On essaye de recuperer des noms de personnes qui y travaillent grace à LinkedIn");
				searchKeywords = "site:fr.linkedin.com+(inurl:in+|+inurl:pub)+-inurl:dir+-inurl:title+-inurl:jobs2+-inurl:jobs+"
						+ res.getResultAtIndex(0).replaceAll("\\s+", "+");
				System.out.println(searchKeywords);
				for (int i = 0; i < NUMBER_OF_GOOGLE_PAGES_TO_SEARCH; i++) {
					JsonParserService.lookForLinkedInContactsInJSON(
							GoogleQueryReaderService.read(searchURL, searchKeywords, 0, 10 + i * 10),
							lIdentifiedCompany.getIdentifiedContacts(), ROLES_TO_LOOK_FOR, REGIONS_TO_LOOK_FOR,null);
				}
				System.out.println(lIdentifiedCompany);
				lListCompanies.add(lIdentifiedCompany);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		// Maintenant on écrit le résultat de la liste de companies dans un
		// excel (ou autre, choisir le writer approprié)
		Pojo2ExcelSheetWriter lPojoWriter = new Pojo2ExcelSheetWriter(new ExcelFileHandler(new FileOutputStream(
				"C:\\Users\\elphono\\Downloads\\Fiches société\\liste\\listeSocietesCiblesOutput.xlsx")));
		lPojoWriter.writeCompany2Excel(lListCompanies);
	}

}
