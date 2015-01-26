package fr.antspot.www;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.antspot.www.bo.ExcelResults;
import fr.antspot.www.bo.IdentifiedCompany;
import fr.antspot.www.bo.IdentifiedContact;
import fr.antspot.www.json.GoogleSearchResult;
import fr.antspot.www.json.Item;
import fr.antspot.www.json.Pagemap;
import fr.antspot.www.json.Person;
import fr.antspot.www.out.Pojo2ExcelSheetWriter;
import fr.antspot.www.util.ExcelCriteria;
import fr.antspot.www.util.ExcelFileHandler;
import fr.antspot.www.util.ExcelReaderService;

public class Execution {

	private static final List<String> apiKeys = new ArrayList<String>(Arrays.asList(
			"AIzaSyApcKGtKhWjrkuSeEPhw1iKp2uxEILUkvQ", "AIzaSyBiB8x_DLKR99mexPbPRo1xEi7BvY2nOYI","AIzaSyAvo5UDsne9JGD7QxokeWaSDtb7gLWpYaA","AIzaSyCm8Rqcvhx-elfw53rI8HJdvCQ6z774TFU"));
	private static final List<String> customSearchEngineKeys = new ArrayList<String>(Arrays.asList(
			"005293556585275424489:2_vvk6spj4k", "007360947949592224321:jipbmrhbvqy","014793693421890995973:3fhwdwn2xxo","015453605495681887935:fsr3jchjnok"));
	
	private static final String apiKey = "AIzaSyApcKGtKhWjrkuSeEPhw1iKp2uxEILUkvQ";
	private static final String customSearchEngineKey = "005293556585275424489:2_vvk6spj4k";

	private static final int SEARCH_COUNT_LIMIT = 3;
	private static final int NUMBER_OF_GOOGLE_PAGES_TO_SEARCH = 1;
	private static final String REGION = "PACA";
	private static final List<String> REGIONS_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("PACA", "MARSEILLE",
			"TOULON", "VAR", "NICE", "SOPHIA"));
	private static final List<String> ROLES_TO_LOOK_FOR = new ArrayList<String>(Arrays.asList("HEAD", "CHEF",
			"MANAGER", "PROJECT", "PROJET", "DIRECTEUR", "DIRECTOR", "IT", "INFORMATIQUE", "SI"));

	public static void main(String[] args) throws Exception {
		String searchURL = "https://www.googleapis.com/customsearch/v1?";

		ExcelFileHandler handler = new ExcelFileHandler(new FileInputStream(
				"C:\\Users\\elphono\\Downloads\\Fiches société\\liste\\listeSocietesCibles.xlsx"), 0);
		List<ExcelCriteria> criterias = new ArrayList<ExcelCriteria>();
		criterias.add(new ExcelCriteria("Secteur", "Distribution"));
		List<ExcelResults> listResults = ExcelReaderService.searchSourceExcelWithCriteria(handler, criterias);
		System.out.println("Excel results:");

		int searchCurrentCount = 1;
		List<IdentifiedCompany> lListCompanies = new ArrayList<IdentifiedCompany>();
		for (ExcelResults res : listResults) {
			if (searchCurrentCount++ > SEARCH_COUNT_LIMIT) {
				break;
			}
			IdentifiedCompany lIdentifiedCompany = new IdentifiedCompany(res.getResultAtIndex(0),
					res.getResultAtIndex(1), res.getResultAtIndex(2));
			System.out
					.println("Premiere étape: On récupère les infos de bases sur le site de base (URL et numéro de telephone du standard)");
			String searchKeywords = "inurl:" + res.getResultAtIndex(0).replaceAll("\\s+", "*") + "*contact";
			System.out.println(searchKeywords);
			String toSearch = makeSearchString(searchURL, searchKeywords, 1, 0);
			lookForUsefulInfoInJSON(read(toSearch), lIdentifiedCompany, ROLES_TO_LOOK_FOR, REGIONS_TO_LOOK_FOR);

			System.out
					.println("Deuxième étape: On essaye de recuperer des noms de personnes qui y travaille grace à LinkedIn");
			searchKeywords = "site:fr.linkedin.com+(inurl:in+|+inurl:pub)+-inurl:dir+-inurl:title+-inurl:jobs2+-inurl:jobs+"
					+ res.getResultAtIndex(0).replaceAll("\\s+", "+") + "+" + REGION;
			for (int i = 0; i < NUMBER_OF_GOOGLE_PAGES_TO_SEARCH; i++) {
				toSearch = makeSearchString(searchURL, searchKeywords, 10, i * 10);
				lookForUsefulInfoInJSON(read(toSearch), lIdentifiedCompany, ROLES_TO_LOOK_FOR, REGIONS_TO_LOOK_FOR);
			}
			System.out.println(lIdentifiedCompany);
			lListCompanies.add(lIdentifiedCompany);
		}

		// Maintenant on écrit le résultat de la liste de companies dans un
		// excel (ou autre, choisir le writer approprié)
		Pojo2ExcelSheetWriter lPojoWriter = new Pojo2ExcelSheetWriter(new ExcelFileHandler(new FileOutputStream(
				"C:\\Users\\elphono\\Downloads\\Fiches société\\liste\\listeSocietesCiblesOutput.xlsx")));
		lPojoWriter.writeCompany2Excel(lListCompanies);
	}

	private static String makeSearchString(String searchURL, String searchKeywords, int nbOfResults, int startingResult) {
		String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&q=";
		String searchString = toSearch + searchKeywords;

		searchString += "&alt=json";
		// specify starting result number
		if (startingResult != 0) {
			searchString += "&start=" + startingResult;
		}
		// specify the number of results you need from the starting position
		if (nbOfResults != 0) {
			searchString += "&num=" + nbOfResults;
		}
		// System.out.println("SearchString= " + searchString);
		return searchString;
	}

	private static String read(String pUrl) {
		// pUrl is the URL we created in previous step
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			URL url = new URL(pUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void lookForUsefulInfoInJSON(String pJson, IdentifiedCompany pIdentifiedCompany,
			List<String> pKeywords, List<String> pRegions) {
		System.out.println(pJson);
		// On passe autant de fois que de ExcelResults (donc autant de fois que
		// de société parsée) x le nombre de page à check dans Google.
		Gson gson = new GsonBuilder().create();
		GoogleSearchResult gsr = gson.fromJson(pJson, GoogleSearchResult.class);
		// Get the root object for the response
		List<Item> lListItem = gsr.getItems();
		outer: for (Item it : lListItem) {
			Pagemap pm = it.getPagemap();
			List<Person> person = pm.getPerson();
			// On est dans le cas ou il y a une personne ou pas. On cherche les
			// infos de la personne OU celle du standard téléphonique de la
			// boite.
			boolean personFound = false;
			for (Person p : person) {
				personFound = true;
				boolean keepPerson = true;
				if (pKeywords != null && !pKeywords.isEmpty() && pRegions != null && !pRegions.isEmpty()) {
					keepPerson = checkIfPersonIsInteresting(p, pKeywords, pRegions);
				}
				if (!keepPerson) {
					continue outer;
				}
				IdentifiedContact lIdentifiedContact = new IdentifiedContact();
				lIdentifiedContact.setLinkedInWebLink(it.getLink());
				lIdentifiedContact.setLinkedInLocation(p.getLocation());
				lIdentifiedContact.setLinkedInRole(p.getRole());
				pIdentifiedCompany.getIdentifiedContacts().add(lIdentifiedContact);
			}
			if (!personFound) {
				try {
					String phoneNumberParsed = parsePhoneNumberFromWebsite(it.getLink());
					pIdentifiedCompany.getParsedPhoneNumber().add(phoneNumberParsed);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String parsePhoneNumberFromWebsite(String url) throws IOException {
		String frenchPhoneNumberRegex = "((\\+|00)33\\s?|0|(\\+|00)33\\s?\\(0\\))[1-7](\\s?\\d{2}){4}";
		final Pattern phonePattern = Pattern.compile(frenchPhoneNumberRegex);
		String toReturn = null;
		URL lUrl = new URL(url);
		URLConnection lUrlConnection = lUrl.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(lUrlConnection.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			Matcher matcher = phonePattern.matcher(inputLine);
			if (matcher.find()) {

				toReturn = matcher.group();
			}
		}
		in.close();
		return toReturn;
	}

	/**
	 * Filter method by keywords for role and for region If one or more matches
	 * in each list returns true.
	 */
	private static boolean checkIfPersonIsInteresting(Person person, List<String> keywords, List<String> regions) {
		List<String> copyKeywords = new ArrayList<String>(keywords.size());
		List<String> copyRegions = new ArrayList<String>(regions.size());
		copyKeywords.addAll(keywords);
		copyRegions.addAll(regions);
		String[] locations = person.getLocation().split(" ");
		String[] roles = person.getRole().split(" ");

		copyRegions.retainAll(Arrays.asList(locations).stream().map(str -> str.toUpperCase())
				.collect(Collectors.toList()));
		copyKeywords
				.retainAll(Arrays.asList(roles).stream().map(str -> str.toUpperCase()).collect(Collectors.toList()));
		if (copyRegions.size() > 0 && copyKeywords.size() > 0) {
			return true;
		}
		return false;
	}

}
