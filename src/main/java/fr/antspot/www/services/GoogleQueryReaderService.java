package fr.antspot.www.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

public class GoogleQueryReaderService {

	private static final ImmutableMap<String, String> apiKeysFinal = new ImmutableMap.Builder<String, String>()
			.put("AIzaSyApcKGtKhWjrkuSeEPhw1iKp2uxEILUkvQ", "005293556585275424489:2_vvk6spj4k")
			.put("AIzaSyBiB8x_DLKR99mexPbPRo1xEi7BvY2nOYI", "007360947949592224321:jipbmrhbvqy")
			.put("AIzaSyAvo5UDsne9JGD7QxokeWaSDtb7gLWpYaA", "014793693421890995973:3fhwdwn2xxo")
			.put("AIzaSyCm8Rqcvhx-elfw53rI8HJdvCQ6z774TFU", "015453605495681887935:fsr3jchjnok")
			.put("AIzaSyBiEQTtUQca48Tx6Gc-3z39oYP8uczUXLE", "017143659608654648450:dhz6v_6e__e")
			.put("AIzaSyDSdJl66xZNbFElHymYZhQEvJz-TbDNNFo", "018089503566261776610:k89xb-emb3a")
			.put("AIzaSyAKH4zyi5cP--LOhD0JKkiHA5A_BI7f0YM", "008365662555676130483:ulqvw9nhjvm")
			.put("AIzaSyDqdS4nNyLpuTLaKkM853lD6Bbzn7G09yY", "010720450811299280161:ykuoukuny20")
			.put("AIzaSyCXXYDAycZlNAxpowNWjs28yN2s5oIRyLA", "008413310253421262418:gz_i2pegs5c").build();
	
	
	private static final Map<String, String> usedUpKeys = new HashMap<String, String>();
	
	/**
	 * Failsafe method. Runs again with another key if google api key quota is
	 * met.
	 * 
	 * @param pUrl
	 * @param searchKeywords
	 * @param startingResult
	 * @param nbOfResults
	 * @return
	 */
	public static String read(String pUrl, String searchKeywords, int startingResult, int nbOfResults) {
		// First, make the search string.
		for (Entry<String, String> apiKey : apiKeysFinal.entrySet()) {
			if (usedUpKeys.containsKey(apiKey.getKey())) {
				continue;
			}
			String toSearch = pUrl + "key=" + apiKey.getKey() + "&cx=" + apiKey.getValue() + "&q=";
			String searchString = toSearch + searchKeywords;
			System.out.println(searchString);
			searchString += "&alt=json";
			// specify starting result number
			if (startingResult != 0) {
				searchString += "&start=" + startingResult;
			}
			// specify the number of results you need from the starting position
			if (nbOfResults != 0) {
				searchString += "&num=" + nbOfResults;
			}
			// pUrl is the URL we created in previous step
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				URL url = new URL(searchString);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer buffer = new StringBuffer();
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
				return buffer.toString();
			} catch (Exception e) {
				System.out.println("La reqûete GoogleSearchAPI n'a pas aboutie. Essai avec une autre clé: "
						+ e.getMessage());
				usedUpKeys.put(apiKey.getKey(), apiKey.getValue());
			}
		}
		System.out.println("Toutes les clés ont atteint leur quota journalier. Echec.");
		return null;
	}
}
