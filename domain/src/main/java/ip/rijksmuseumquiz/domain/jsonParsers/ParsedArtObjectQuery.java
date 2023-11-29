package ip.rijksmuseumquiz.domain.jsonParsers;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsedArtObjectQuery {
    private JSONObject queryObject;

    public ParsedArtObjectQuery(String jsonString) {
        JSONObject fullObject = new JSONObject(jsonString);
        this.queryObject = fullObject.getJSONObject("artObject");
    }

    public String getNameOfArtwork() {
        String nameOfArtwork = queryObject.getString("title");
        return nameOfArtwork;
    }

    public String getNameOfArtist() {
        JSONArray makers = queryObject.getJSONArray("principalMakers");
        String nameOfArtist = makers.getJSONObject(0).getString("name");
        return nameOfArtist;
        // TODO Implement means for dealing with multiple makers being involved
    }

    public String getImageUrl() {
        JSONObject webImageObject = queryObject.getJSONObject("webImage");
        String imageUrl = webImageObject.getString("url");
        return imageUrl;
    }

    public String getLongTitle() {
        String longTitle = queryObject.getString("longTitle");
        return longTitle;
    }

    public String getPlaqueDescription() {
        String plaqueDescription = queryObject.getString("plaqueDescriptionDutch");
        return plaqueDescription;
    }
    
}
