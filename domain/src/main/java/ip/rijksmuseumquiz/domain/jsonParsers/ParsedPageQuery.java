package ip.rijksmuseumquiz.domain.jsonParsers;

import org.json.JSONObject;

public class ParsedPageQuery {
    private JSONObject queryObject;

    public ParsedPageQuery(String jsonString){
        this.queryObject = new JSONObject(jsonString);
    }
    
    public int getNumberOfObjects() {
        int numberOfObjects = queryObject.getJSONArray("artObjects").length();
        return numberOfObjects;
    }

    public String getObjectCode(int objectIndex) {
        String objectCode = queryObject.getJSONArray("artObjects").getJSONObject(objectIndex).getString("objectNumber");
        return objectCode;
    }

    public String[] getMultipleLongTitles(int firstIndex, int secondIndex, int thirdIndex) {
        String[] multipleLongTitles = new String[3];
        multipleLongTitles[0] = queryObject.getJSONArray("artObjects").getJSONObject(firstIndex).getString("longTitle");
        multipleLongTitles[1] = queryObject.getJSONArray("artObjects").getJSONObject(secondIndex).getString("longTitle");
        multipleLongTitles[2] = queryObject.getJSONArray("artObjects").getJSONObject(thirdIndex).getString("longTitle");
        return multipleLongTitles;
    }
}
