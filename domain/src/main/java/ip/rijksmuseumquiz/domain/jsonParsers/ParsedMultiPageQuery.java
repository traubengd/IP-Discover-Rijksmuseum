package ip.rijksmuseumquiz.domain.jsonParsers;

import org.json.JSONObject;

public class ParsedMultiPageQuery {
    private JSONObject queryObject;

    public ParsedMultiPageQuery(String jsonString){
        this.queryObject = new JSONObject(jsonString);
    }
    
    public int getNumberOfResults() {
        int numberOfResults = queryObject.getInt("count");
        return numberOfResults;
    }

    public int getNumberOfPages(int numberOfResults, int numberOfResultsPerPage) {
        int numberOfPages = (int)(numberOfResults/numberOfResultsPerPage);
        return numberOfPages;
    }

}
