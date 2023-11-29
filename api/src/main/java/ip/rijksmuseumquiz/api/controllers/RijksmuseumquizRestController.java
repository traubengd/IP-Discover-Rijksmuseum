package ip.rijksmuseumquiz.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RijksmuseumquizRestController {
	private String apiKey = "lFppaOWt";

	public String getMultipleArtworks(int resultsPerPage, int numberOfPages){
		int pagenumber = (int)(Math.random() * (numberOfPages-1));
		//The following strings for now assume specifically paintings, might want to adjust to broaden to whole collection later,
		//or figure something out for other types of artwork
		String url = "https://www.rijksmuseum.nl/api/nl/collection/?key="+ apiKey + "&ps=" + resultsPerPage + "&p=" + pagenumber + "&type=schilderij&toppieces=true&imgonly=true";
		RestTemplate restTemplate = new RestTemplate();
		String queryResults = restTemplate.getForObject(url, String.class);

		return queryResults;
	}

	public String makeInitialQuery(int resultsPerPage){
		String url = "https://www.rijksmuseum.nl/api/nl/collection/?key=" + apiKey + "&ps=" + resultsPerPage + "&type=schilderij&toppieces=true&imgonly=true";
		RestTemplate restTemplate = new RestTemplate();
		String queryResults = restTemplate.getForObject(url, String.class);

		return queryResults;
	}

	public String getSpecificArtwork(String objectCode) {
		String url = "https://www.rijksmuseum.nl/api/nl/collection/"+ objectCode + "?key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();

		String queryResults = restTemplate.getForObject(url, String.class);

		return queryResults;
	}
}
