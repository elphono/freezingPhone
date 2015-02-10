package fr.antspot.www.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import fr.antspot.www.bo.IdentifiedCompany;
import fr.antspot.www.bo.IdentifiedContact;
import fr.antspot.www.json.GoogleSearchResult;
import fr.antspot.www.json.Item;
import fr.antspot.www.json.Pagemap;
import fr.antspot.www.json.Person;

public class JsonParserService {

	public static void lookForLinkedInContactsInJSON(String pJson, List<IdentifiedContact> pIdentifiedContacts,
			List<String> pKeywords, List<String> pRegions, IdentifiedContact pIdentifiedContact) {
		// On passe autant de fois que de ExcelResults (donc autant de fois que
		// de société parsée) x le nombre de page à check dans Google.
		Gson gson = new GsonBuilder().create();
		GoogleSearchResult gsr = gson.fromJson(pJson, GoogleSearchResult.class);
		// Get the root object for the response
		List<Item> lListItem = gsr.getItems();
		outer: for (Item it : lListItem) {
			// Verifions que le profil trouvé correspond au nom cherché
			if (pIdentifiedContact != null) {
				String title = it.getTitle();
				String prenom = pIdentifiedContact.getPrenom();
				String nom = pIdentifiedContact.getNom();
				if (title.contains(prenom.toLowerCase()) && title.toLowerCase().contains(nom.toLowerCase())) {
					// do nothing
				} else {
					continue;
				}
			}
			Pagemap pm = it.getPagemap();
			if (pm == null) {
				continue;
			}
			List<Person> person = pm.getPerson();
			// On est dans le cas ou il y a une personne ou pas. On cherche les
			// infos de la personne OU celle du standard téléphonique de la
			// boite.
			for (Person p : person) {
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
				pIdentifiedContacts.add(lIdentifiedContact);
			}
		}
	}

	public static void lookForPhoneNumberInJSON(String pJson, IdentifiedCompany pIdentifiedCompany,
			List<String> pKeywords, List<String> pRegions) {
		// On passe autant de fois que de ExcelResults (donc autant de fois que
		// de société parsée) x le nombre de page à check dans Google.
		Gson gson = new GsonBuilder().create();
		GoogleSearchResult gsr = gson.fromJson(pJson, GoogleSearchResult.class);
		// Get the root object for the response
		List<Item> lListItem = gsr.getItems();
		for (Item it : lListItem) {
			try {
				String phoneNumberParsed = parsePhoneNumberFromWebsite(it.getLink());
				if (phoneNumberParsed != null && !phoneNumberParsed.isEmpty()) {
					pIdentifiedCompany.getParsedPhoneNumber().add(phoneNumberParsed);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	private static String parsePhoneNumberFromWebsite(String url) throws IOException {
		String frenchPhoneNumberRegex = "((\\+|00)33\\s?|0|(\\+|00)33\\s?\\(0\\))[1-7]((\\s|\\.)?\\d{2}){4}";
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
}
