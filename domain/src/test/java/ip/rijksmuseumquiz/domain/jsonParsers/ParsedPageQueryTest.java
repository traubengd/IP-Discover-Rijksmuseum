package ip.rijksmuseumquiz.domain.jsonParsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;

public class ParsedPageQueryTest {

    @Test
    public void insertingFaultyStringIntoParserResultsInException() {
        assertThrows(JSONException.class, () -> {
            new ParsedMultiPageQuery("Faulty string");
        });
    }

    @Test
    public void parserCanDetermineNumberOfObjectsInAPageQuery() {
        ParsedPageQuery parsedQuery = new ParsedPageQuery(testQueryString);
        int numberOfObjects = parsedQuery.getNumberOfObjects();
        assertEquals(3, numberOfObjects);
    }

    @Test
    public void parserCanExtractMultipleLongTitlesBasedOnIndices() {
        ParsedPageQuery parsedQuery = new ParsedPageQuery(testQueryString);
        String[] multipleLongTitles = parsedQuery.getMultipleLongTitles(0, 1, 2);
        assertTrue(multipleLongTitles[0].equals("Maria met het Christuskind, Adriaen van Wesel, ca. 1470 - ca. 1480"));
        assertTrue(multipleLongTitles[1].equals("Maria, Jozef en drie engelen, Adriaen van Wesel, ca. 1475 - ca. 1477"));
        assertTrue(multipleLongTitles[2].equals("Plint van de Kruisiging:, Adriaen van Wesel, ca. 1475 - ca. 1477"));
    }

    @Test
    public void parserCanPickOutAnArtObjectCode() {
        ParsedPageQuery parsedQuery = new ParsedPageQuery(testQueryString);
        String objectCode = parsedQuery.getObjectCode(2);
        assertTrue(objectCode.equals("BK-1979-94-1"));
    }

    @Test
    public void tryingToParseAnIncorrectIndexForCodeResultsInException() {
        ParsedPageQuery parsedQuery = new ParsedPageQuery(testQueryString);
        assertThrows(JSONException.class, () -> {
            parsedQuery.getObjectCode(5);
        });
    }

    private String testQueryString = "{\n" + //
            "  \"elapsedMilliseconds\": 0,\n" + //
            "  \"count\": 27,\n" + //
            "  \"countFacets\": {\n" + //
            "    \"hasimage\": 27,\n" + //
            "    \"ondisplay\": 19\n" + //
            "  },\n" + //
            "  \"artObjects\": [\n" + //
            "    {\n" + //
            "      \"links\": {\n" + //
            "        \"self\": \"http://www.rijksmuseum.nl/api/nl/collection/BK-NM-3888\",\n" + //
            "        \"web\": \"http://www.rijksmuseum.nl/nl/collectie/BK-NM-3888\"\n" + //
            "      },\n" + //
            "      \"id\": \"nl-BK-NM-3888\",\n" + //
            "      \"objectNumber\": \"BK-NM-3888\",\n" + //
            "      \"title\": \"Maria met het Christuskind\",\n" + //
            "      \"hasImage\": true,\n" + //
            "      \"principalOrFirstMaker\": \"Adriaen van Wesel\",\n" + //
            "      \"longTitle\": \"Maria met het Christuskind, Adriaen van Wesel, ca. 1470 - ca. 1480\",\n" + //
            "      \"showImage\": true,\n" + //
            "      \"permitDownload\": true,\n" + //
            "      \"webImage\": {\n" + //
            "        \"guid\": \"489a26be-8691-42a2-9102-3cb1954846ce\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 1297,\n" + //
            "        \"height\": 2500,\n" + //
            "        \"url\": \"https://lh6.ggpht.com/w51qt-qW6i-7WJL4SJzFLw1JQAa0lx0Kem6vI0TgQv9fFVDzLaq70UV3SmAQfdN4AhcnnspmU7eG1zaQxOu_z2z5caY=s0\"\n"
            + //
            "      },\n" + //
            "      \"headerImage\": {\n" + //
            "        \"guid\": \"62456a1e-566c-4ad9-b8fb-310b470def6d\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 1297,\n" + //
            "        \"height\": 311,\n" + //
            "        \"url\": \"https://lh4.ggpht.com/ZIgmR-zGjDdnoVaw8OgEA0mGIdwTH41FvcpD4QXWisYjD9C18FCsO5JCFWYtvcbziYlJT-CYNUp0NqBx68VTT_cgLcM=s0\"\n"
            + //
            "      },\n" + //
            "      \"productionPlaces\": [\n" + //
            "        \"Utrecht\"\n" + //
            "      ]\n" + //
            "    },\n" + //
            "    {\n" + //
            "      \"links\": {\n" + //
            "        \"self\": \"http://www.rijksmuseum.nl/api/nl/collection/BK-NM-11647\",\n" + //
            "        \"web\": \"http://www.rijksmuseum.nl/nl/collectie/BK-NM-11647\"\n" + //
            "      },\n" + //
            "      \"id\": \"nl-BK-NM-11647\",\n" + //
            "      \"objectNumber\": \"BK-NM-11647\",\n" + //
            "      \"title\": \"Maria, Jozef en drie engelen\",\n" + //
            "      \"hasImage\": true,\n" + //
            "      \"principalOrFirstMaker\": \"Adriaen van Wesel\",\n" + //
            "      \"longTitle\": \"Maria, Jozef en drie engelen, Adriaen van Wesel, ca. 1475 - ca. 1477\",\n" + //
            "      \"showImage\": true,\n" + //
            "      \"permitDownload\": true,\n" + //
            "      \"webImage\": {\n" + //
            "        \"guid\": \"df19e8bc-a221-4ed9-8419-679c19eabcc3\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 2281,\n" + //
            "        \"height\": 2500,\n" + //
            "        \"url\": \"https://lh3.ggpht.com/7s-7WY4dNxKE6vHJGu7HcbEsnApDGJVbiF4TwLM1edwyUvqj4ElntbdoZibjnUbWJRnPKLNBrYB6-gFQUbqQZ_qDRs_a=s0\"\n"
            + //
            "      },\n" + //
            "      \"headerImage\": {\n" + //
            "        \"guid\": \"d087fe67-125a-494b-b320-6c4b6627b87c\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 1920,\n" + //
            "        \"height\": 460,\n" + //
            "        \"url\": \"https://lh3.ggpht.com/as2Jw1gpX-ae_QXb-fw9fAUZpg4_9SDZR13TEDxHUollT_OXMPC7UsrSd1DtAK0F9ZfABbzIjsdwE06W0FA3GHzTDBov=s0\"\n"
            + //
            "      },\n" + //
            "      \"productionPlaces\": [\n" + //
            "        \"Utrecht\"\n" + //
            "      ]\n" + //
            "    },\n" + //
            "    {\n" + //
            "      \"links\": {\n" + //
            "        \"self\": \"http://www.rijksmuseum.nl/api/nl/collection/BK-1979-94-1\",\n" + //
            "        \"web\": \"http://www.rijksmuseum.nl/nl/collectie/BK-1979-94-1\"\n" + //
            "      },\n" + //
            "      \"id\": \"nl-BK-1979-94-1\",\n" + //
            "      \"objectNumber\": \"BK-1979-94-1\",\n" + //
            "      \"title\": \"Plint van de Kruisiging:\",\n" + //
            "      \"hasImage\": true,\n" + //
            "      \"principalOrFirstMaker\": \"Adriaen van Wesel\",\n" + //
            "      \"longTitle\": \"Plint van de Kruisiging:, Adriaen van Wesel, ca. 1475 - ca. 1477\",\n" + //
            "      \"showImage\": true,\n" + //
            "      \"permitDownload\": true,\n" + //
            "      \"webImage\": {\n" + //
            "        \"guid\": \"21c95404-7f22-4bf3-bbc4-26464cd9ceeb\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 1383,\n" + //
            "        \"height\": 2500,\n" + //
            "        \"url\": \"https://lh3.googleusercontent.com/h6w6oRm1uNy_I92J0i0TWlLofhmSnMPBkp0xXPIzMmpjLQei_qOK_wpvyjKWPalu81qc34-0NhceYhkgddIcZhX2PfzbkJyY3xxURc3R=s0\"\n"
            + //
            "      },\n" + //
            "      \"headerImage\": {\n" + //
            "        \"guid\": \"339fe0c1-e6a2-4ff1-8f79-130f7e58a2d7\",\n" + //
            "        \"offsetPercentageX\": 0,\n" + //
            "        \"offsetPercentageY\": 0,\n" + //
            "        \"width\": 1383,\n" + //
            "        \"height\": 332,\n" + //
            "        \"url\": \"https://lh3.googleusercontent.com/vzcH27i_kgX5qrfONkv_mXUvvhCJ5ZjtpiREz3-zXfD_PRxdDpUz0FOwh8gqUQrxn7MAQY_yqocAgs7xN_1iMRTDUEGeEJ0UzOPlWz7G-g=s0\"\n"
            + //
            "      },\n" + //
            "      \"productionPlaces\": [\n" + //
            "        \"Utrecht\"\n" + //
            "      ]\n" + //
            "    }\n" + //
            "  ]}";
}
