package ip.rijksmuseumquiz.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RijksmuseumquizRestController {
	private String apiKey = "lFppaOWt";
	private String searchParameters = "&toppieces=true";

	public String makeInitialQuery(int resultsPerPage){
		String url = "https://www.rijksmuseum.nl/api/nl/collection/?key=" + apiKey + "&ps=" + resultsPerPage + "&type=schilderij&imgonly=true" + searchParameters;
		RestTemplate restTemplate = new RestTemplate();
		String queryResults = restTemplate.getForObject(url, String.class);

		return queryResults;
	}
	
	public String getMultipleArtworks(int resultsPerPage, int numberOfPages){
		int pagenumber = (int)(Math.random() * (numberOfPages-1));
		String url = "https://www.rijksmuseum.nl/api/nl/collection/?key="+ apiKey + "&ps=" + resultsPerPage + "&p=" + pagenumber + "&type=schilderij&imgonly=true" + searchParameters;
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

    public void setSearchParameters(String parameters) {
		this.searchParameters = parameters;
    }
}
