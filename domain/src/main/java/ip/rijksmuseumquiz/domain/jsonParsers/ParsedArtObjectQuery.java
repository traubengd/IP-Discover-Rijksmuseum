package ip.rijksmuseumquiz.domain.jsonParsers;

import java.awt.Color;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import ip.rijksmuseumquiz.domain.ColourData;

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

    public ArrayList<ColourData> getColours() {
        ArrayList<ColourData> colourList = new ArrayList<>();
        int numberOfColours = queryObject.getJSONArray("colors").length();
        for (int i = 0; i < numberOfColours; i++) {
            String colourCode = queryObject.getJSONArray("colors").getJSONObject(i).getString("hex").trim()
                    .substring(1);
            Color colourAsObject = Color.decode("0x" + colourCode);
            int colourPresence = queryObject.getJSONArray("colors").getJSONObject(i).getInt("percentage");
            ColourData newColour = new ColourData(colourAsObject, colourPresence);
            if (newColour.getPercentage() > 0){
                colourList.add(newColour);
            }
        }
        Collections.sort(colourList, Comparator.comparing(ColourData::getPercentage).reversed());
        return colourList;
    }

    public String getObjectCode() {
        String objectCode = queryObject.getString("objectNumber");
        return objectCode;
    }

    public String getDescription() {
        String description = queryObject.getString("description");
        return description;
    }
}
