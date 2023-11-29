package ip.rijksmuseumquiz.domain.jsonParsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;

public class ParsedMultiPageQueryTest {

    @Test
    public void insertingFaultyStringIntoParserResultsInException() {
        assertThrows(JSONException.class, () -> {
            new ParsedMultiPageQuery("Faulty string");
        });
    }

    @Test
    public void givenAJSONStringContainingCountParserCanDetermineNumberOfResults() {
        ParsedMultiPageQuery parsedQuery = new ParsedMultiPageQuery(testQueryString);
        assertEquals(4402, parsedQuery.getNumberOfResults());
    }

    @Test
    public void givenANumberOfResultsAndNumberOfResultsPerPageParserCanDetermineNumberOfPages() {
        ParsedMultiPageQuery parsedQuery = new ParsedMultiPageQuery(testQueryString);
        int numberOfResults = parsedQuery.getNumberOfResults();
        assertEquals(44, parsedQuery.getNumberOfPages(numberOfResults, 100));
    }

    private String testQueryString = "{\"elapsedMilliseconds\":0,\"count\":4402,\"countFacets\":{\"hasimage\":4402,\"ondisplay\":722}}";
}
