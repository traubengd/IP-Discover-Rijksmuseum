package ip.rijksmuseumquiz.domain.jsonParsers;

import java.awt.Color;

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
        if (makers.length() == 1) {
            String nameOfArtist = makers.getJSONObject(0).getString("name");
            return nameOfArtist;
        } else {
            String nameOfArtist = "";
            for (int i = 0; i < makers.length()-1; i++) {
                String nextArtistName = makers.getJSONObject(i).getString("name");
                nameOfArtist = nameOfArtist + nextArtistName + ", ";
            }
            String lastArtistName = makers.getJSONObject(makers.length()-1).getString("name");
            nameOfArtist = nameOfArtist + "en " + lastArtistName;
            return nameOfArtist;
        }
    }

    public int getDate() {
        int sortingDate = queryObject.getJSONObject("dating").getInt("sortingDate");
        return sortingDate;
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

    public int getNumberOfColours() {
        int numberOfColours = queryObject.getJSONArray("colors").length();
        return numberOfColours;
    }

    public Color[] getColours(int numberOfColours) {
        Color[] colourArray = new Color[numberOfColours];
        for (int i = 0; i < numberOfColours; i++) {
            String colourCode = queryObject.getJSONArray("colors").getJSONObject(i).getString("hex").trim()
                    .substring(1);
            Color colorAsObject = Color.decode("0x" + colourCode);
            colourArray[i] = colorAsObject;
        }
        return colourArray;
    }
}
