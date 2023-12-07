package ip.rijksmuseumquiz.api.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RijksmuseumquizRestControllerTest {
    RijksmuseumquizRestController rijksmuseumquizRestController;

    @BeforeEach
    public void testSetup(){
        rijksmuseumquizRestController = new RijksmuseumquizRestController();
    }
    

    @Test
    public void restControllerWithStandardParametersReturnsNumberOfObjects(){
        int numberOfObjects = new JSONObject(rijksmuseumquizRestController.makeInitialQuery(100)).getInt("count");
        assertEquals(4402, numberOfObjects);
    }

    @Test
    public void changingSearchParametersChangesTheNumberOfItemsReturnedInStringByRestController(){
        rijksmuseumquizRestController.setSearchParameters("&involvedMaker=Rembrandt+van+Rijn");
        int numberOfObjects = new JSONObject(rijksmuseumquizRestController.makeInitialQuery(100)).getInt("count");
        assertEquals(34, numberOfObjects);
    }

    @Test
    public void whenGivenSearchParametersThatHaveNoCorrespondingWorksTheStringReturnedByControllerContainsNoObjects(){
        rijksmuseumquizRestController.setSearchParameters("&involvedMaker=Rembrandt+van+Rijn&type=beeldhouwwerk");
        int numberOfObjects = new JSONObject(rijksmuseumquizRestController.makeInitialQuery(100)).getInt("count");
        assertEquals(0, numberOfObjects);
    }
}
