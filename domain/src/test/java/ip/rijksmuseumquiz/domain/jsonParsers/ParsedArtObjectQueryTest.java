package ip.rijksmuseumquiz.domain.jsonParsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.json.JSONException;

public class ParsedArtObjectQueryTest {

    @Test
    public void insertingFaultyStringIntoParserResultsInException() {
        assertThrows(JSONException.class, () -> {
            new ParsedArtObjectQuery("Faulty string");
        });
    }

    @Test
    public void parserCanExtractTitleOfPaintingFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        String nameOfPainting = parsedQuery.getNameOfArtwork();
        assertTrue(nameOfPainting.equals("De Nachtwacht"));
    }

    @Test
    public void parserCanExtractNameOfPainterFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        String nameOfPainter = parsedQuery.getNameOfArtist();
        assertTrue(nameOfPainter.equals("Rembrandt van Rijn"));
    }

    @Test
    public void parserCanExtractPresentingDateFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        int sortingDate = parsedQuery.getDate();
        assertEquals(1642, sortingDate);
    }

    @Test
    public void parserCanExtractLongTitleFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        String longTitle = parsedQuery.getLongTitle();
        assertTrue(longTitle.equals("De Nachtwacht, Rembrandt van Rijn, 1642"));
    }

    @Test
    public void parserCanExtractImageUrlFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        String imageUrl = parsedQuery.getImageUrl();
        String expectedUrl = "https://lh3.googleusercontent.com/SsEIJWka3_cYRXXSE8VD3XNOgtOxoZhqW1uB6UFj78eg8gq3G4jAqL4Z_5KwA12aD7Leqp27F653aBkYkRBkEQyeKxfaZPyDx0O8CzWg=s0";
        assertTrue(expectedUrl.equals(imageUrl));
    }

    @Test
    public void parserCanExtractPlaqueDescriptionFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        String plaqueDescription = parsedQuery.getPlaqueDescription();
        assertTrue(plaqueDescription.equals("Rembrandts beroemdste en grootste doek werd gemaakt voor de Kloveniersdoelen. Dit was een van de verenigingsgebouwen van de Amsterdamse schutterij, de burgerwacht van de stad. \r\nRembrandt was de eerste die op een groepsportret de figuren in actie weergaf. De kapitein, in het zwart, geeft zijn luitenant opdracht dat de compagnie moet gaan marcheren. De schutters stellen zich op. Met behulp van licht vestigde Rembrandt de aandacht op belangrijke details, zoals het handgebaar van de kapitein en het kleine meisje op de achtergrond. Zij is de mascotte van de schutters."));
    }

    @Test
    public void parserCanDetermineNumberOfColoursGivenInAJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        int numberOfColours = parsedQuery.getNumberOfColours();
        assertEquals(7, numberOfColours);
    }

    @Test
    public void parserCanExtractAnArrayOfColoursOfSpecifiedLengthFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        Color[] colours = parsedQuery.getColours(5);
        assertEquals(5, colours.length);
    }

    @Test
    public void parserCanExtractAnArrayOfCorrectColoursFromJSONString() {
        ParsedArtObjectQuery parsedQuery = new ParsedArtObjectQuery(testQueryString);
        Color[] colours = parsedQuery.getColours(5);
        assertEquals(new Color(38, 24, 8), colours[0]);
        assertEquals(new Color(94, 60, 20), colours[1]);
        assertEquals(new Color(156, 130, 56), colours[2]);
        assertEquals(new Color(136, 86, 23), colours[3]);
        assertEquals(new Color(175, 159, 107), colours[4]);
    }

    private String testQueryString = "{\"elapsedMilliseconds\":2348,\"artObject\":{\"links\":{\"search\":\"http://www.rijksmuseum.nl/api/nl/collection\"},\"id\":\"nl-SK-C-5\",\"priref\":\"5216\",\"objectNumber\":\"SK-C-5\",\"language\":\"nl\",\"title\":\"De Nachtwacht\",\"copyrightHolder\":null,\"webImage\":{\"guid\":\"bbd1fae8-4023-4859-8ed1-d38616aec96c\",\"offsetPercentageX\":50,\"offsetPercentageY\":100,\"width\":5656,\"height\":4704,\"url\":\"https://lh3.googleusercontent.com/SsEIJWka3_cYRXXSE8VD3XNOgtOxoZhqW1uB6UFj78eg8gq3G4jAqL4Z_5KwA12aD7Leqp27F653aBkYkRBkEQyeKxfaZPyDx0O8CzWg=s0\"},\"colors\":[{\"percentage\":81,\"hex\":\"#261808\"},{\"percentage\":9,\"hex\":\" #5E3C14\"},{\"percentage\":3,\"hex\":\" #9C8238\"},{\"percentage\":2,\"hex\":\" #885617\"},{\"percentage\":1,\"hex\":\" #AF9F6B\"},{\"percentage\":0,\"hex\":\" #6C6238\"},{\"percentage\":0,\"hex\":\" #D7CB9E\"}],\"colorsWithNormalization\":[{\"originalHex\":\"#261808\",\"normalizedHex\":\"#000000\"},{\"originalHex\":\" #5E3C14\",\"normalizedHex\":\"#B35A1F\"},{\"originalHex\":\" #9C8238\",\"normalizedHex\":\"#E09714\"},{\"originalHex\":\" #885617\",\"normalizedHex\":\"#B35A1F\"},{\"originalHex\":\" #AF9F6B\",\"normalizedHex\":\"#E0CC91\"},{\"originalHex\":\" #6C6238\",\"normalizedHex\":\"#367614\"},{\"originalHex\":\" #D7CB9E\",\"normalizedHex\":\"#E0CC91\"}],\"normalizedColors\":[{\"percentage\":81,\"hex\":\"#000000\"},{\"percentage\":12,\"hex\":\" #8B4513\"},{\"percentage\":3,\"hex\":\" #B8860B\"},{\"percentage\":1,\"hex\":\" #BDB76B\"},{\"percentage\":0,\"hex\":\" #556B2F\"},{\"percentage\":0,\"hex\":\" #F5DEB3\"}],\"normalized32Colors\":[{\"percentage\":81,\"hex\":\"#000000\"},{\"percentage\":12,\"hex\":\" #B35A1F\"},{\"percentage\":3,\"hex\":\" #E09714\"},{\"percentage\":2,\"hex\":\" #E0CC91\"},{\"percentage\":0,\"hex\":\" #367614\"}],\"materialsThesaurus\":[],\"techniquesThesaurus\":[],\"productionPlacesThesaurus\":[],\"titles\":[\"Officieren en andere schutters van wijk II in Amsterdam, onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als ‘De Nachtwacht’\",\"Het korporaalschap van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als de 'Nachtwacht'\"],\"description\":\"Officieren en andere schutters van wijk II in Amsterdam onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, sinds het einde van de 18de eeuw bekend als ‘De Nachtwacht’. Schutters van de Kloveniersdoelen uit een poort naar buiten tredend. Op een schild aangebracht naast de poort staan de namen van de afgebeelde personen: Frans Banning Cocq, heer van purmerlant en Ilpendam, Capiteijn Willem van Ruijtenburch van Vlaerdingen, heer van Vlaerdingen, Lu[ij]tenant, Jan Visscher Cornelisen Vaendrich, Rombout Kemp Sergeant, Reijnier Engelen Sergeant, Barent Harmansen, Jan Adriaensen Keyser, Elbert Willemsen, Jan Clasen Leydeckers, Jan Ockersen, Jan Pietersen bronchorst, Harman Iacobsen wormskerck, Jacob Dircksen de Roy, Jan vander heede, Walich Schellingwou, Jan brugman, Claes van Cruysbergen, Paulus Schoonhoven. De schutters zijn gewapend met onder anderen pieken, musketten en hellebaarden. Rechts de tamboer met een grote trommel. Tussen de soldaten links staat een meisje met een dode kip om haar middel, rechts een blaffende hond. Linksboven de vaandrig met de uitgestoken vaandel.\",\"labelText\":null,\"objectTypes\":[\"schilderij\"],\"objectCollection\":[\"schilderijen\"],\"makers\":[],\"principalMakers\":[{\"name\":\"Rembrandt van Rijn\",\"unFixedName\":\"Rijn, Rembrandt van\",\"placeOfBirth\":\"Leiden\",\"dateOfBirth\":\"1606-07-15\",\"dateOfBirthPrecision\":null,\"dateOfDeath\":\"1669-10-08\",\"dateOfDeathPrecision\":null,\"placeOfDeath\":\"Amsterdam\",\"occupation\":[\"prentmaker\",\"tekenaar\",\"schilder\"],\"roles\":[\"schilder\"],\"nationality\":\"Noord-Nederlands\",\"biography\":null,\"productionPlaces\":[\"Amsterdam\"],\"qualification\":null,\"labelDesc\":\"Rembrandt van Rijn (15-jul-1606 - 08-okt-1669), Amsterdam\"}],\"plaqueDescriptionDutch\":\"Rembrandts beroemdste en grootste doek werd gemaakt voor de Kloveniersdoelen. Dit was een van de verenigingsgebouwen van de Amsterdamse schutterij, de burgerwacht van de stad. \\r\\n"
            + //
            "Rembrandt was de eerste die op een groepsportret de figuren in actie weergaf. De kapitein, in het zwart, geeft zijn luitenant opdracht dat de compagnie moet gaan marcheren. De schutters stellen zich op. Met behulp van licht vestigde Rembrandt de aandacht op belangrijke details, zoals het handgebaar van de kapitein en het kleine meisje op de achtergrond. Zij is de mascotte van de schutters.\",\"plaqueDescriptionEnglish\":\"Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \\r\\n"
            + //
            "Rembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\\r\\n"
            + //
            "\",\"principalMaker\":\"Rembrandt van Rijn\",\"artistRole\":null,\"associations\":[],\"acquisition\":{\"method\":\"bruikleen\",\"date\":\"1808-01-01T00:00:00\",\"creditLine\":\"Bruikleen van de gemeente Amsterdam\"},\"exhibitions\":[],\"materials\":[\"doek\",\"olieverf\"],\"techniques\":[],\"productionPlaces\":[\"Amsterdam\"],\"dating\":{\"presentingDate\":\"1642\",\"sortingDate\":1642,\"period\":17,\"yearEarly\":1642,\"yearLate\":1642},\"classification\":{\"iconClassIdentifier\":[\"45(+26)\",\"45C1\",\"48C7341\",\"31D11222\",\"45D12\",\"34B11\"],\"iconClassDescription\":[\"warfare; military affairs (+ citizen soldiery, civil guard, citizen militia)\",\"weapons\",\"drum (musical instrument)\",\"girl (child between toddler and youth)\",\"(military) standard-bearer\",\"dog\"],\"motifs\":[],\"events\":[],\"periods\":[],\"places\":[\"Amsterdam\"],\"people\":[\"Banninck Cocq, Frans\",\"Ruytenburch, Willem van\",\"Visscher Cornelisen, Jan\",\"Kemp, Rombout\",\"Engelen, Reijnier Janszn\",\"Bolhamer, Barent Harmansen\",\"Keijser, Jan Adriaensen\",\"Willemsen, Elbert\",\"Leijdeckers, Jan Claesen\",\"Ockersen, Jan\",\"Bronchorst, Jan Pietersen\",\"Wormskerck, Harman Jacobsen\",\"Roy, Jacob Dircksen de\",\"Heede, Jan van der\"],\"objectNumbers\":[\"SK-C-5\"]},\"hasImage\":true,\"historicalPersons\":[\"Banninck Cocq, Frans\",\"Ruytenburch, Willem van\",\"Visscher Cornelisen, Jan\",\"Kemp, Rombout\",\"Engelen, Reijnier Janszn\",\"Bolhamer, Barent Harmansen\",\"Keijser, Jan Adriaensen\",\"Willemsen, Elbert\",\"Leijdeckers, Jan Claesen\",\"Ockersen, Jan\",\"Bronchorst, Jan Pietersen\",\"Wormskerck, Harman Jacobsen\",\"Roy, Jacob Dircksen de\",\"Heede, Jan van der\"],\"inscriptions\":[],\"documentation\":[\"The Rembrandt Database,  Object information, Rembrandt,  Civic guardsmen of Amsterdam under command of Banninck Cocq,  dated 1642, Rijksmuseum, Amsterdam, inv. no. SK-C-5, http://www.rembrandtdatabase.org/Rembrandt/painting/3063/civic-guardsmen-of-amsterdam-under-command-of-banninck-cocq, accessed 2016 February 01\",\"Inzoomer object op zaal, 2013 (Nederlands/English).\",\"A. Jensen Adams, Public Faces and Private Identities in Seventeenth-Century Holland, Portraiture and the Production of Community, New York 2009, p. 211-217, fig. 60.\",\"M. Rayssac, 'l'Exode des Musées, Histoire des oeuvres d'art sous l'Occupation', Parijs 2007.\",\"K.M. Groen, 'Earth Matters, The origin of the material used for the preparation of the Nightwatch and many other canvases in Rembrandt's workshop after 1640', Art Matters, volume 3, p. 138.\",\"E. Runia. A. van Suchtelen, Rembrandt, Den Haag 2006, p. 14.\",\"Y. van Veelen, 'Work in progress. De strijd om de Nachtwacht', Kunstbeeld nr. 12/1 (dec. 2004/jan. 2005), p. 44-47.\",\"'Omtrents Rembrandts Nachtwacht', Kunstkrant Rijksmuseum Amsterdam nr. 1 (1998), p. 14-20.\",\"H. van Os, in: Bulletin van het Rijksmuseum nr. 4 (1996), p. 309-320 + afb.\",\"J. Boomgaard, 'De Verloren Zoon. Rembrandt en de Nederlandse Kunstgeschiedenisbeschrijving', Kunstreeks (1995).\",\"P. Taylor, 'Darkness at Noon. Rembrandts Nachtwacht', Kunstschrift 6 (1994), p. 22-27 + afb.\",\"Fieke Tissink, 'Hoofdstuk uit het Rijksmuseum', Rijksmuseum Kunstkrant 19 (1993) nr. 3, p. 14-18.\",\"O. Pacht, 'Rembrandt', Munchen 1991, p. 19-30 + ill.\",\"A.K. Wheelock, 'The Age of Rembrandt', Studies in seventeenth Century Dutch Painting (The Pennsylvania State University, 1988), p. 215, 223 (afb.).\",\"J.B. Bedaux, 'Een achttiende eeuwse kunsttheoretische discussie', Kunstlicht 15 (1985), p. 25-28.\",\"C. Grimm, 'Handschrift, schildertechniek en beeldstructuur. Bijdrage tot het onderzoek naar toeschrijving, I: de helmen van Rembrandt', Tableau (1982/83), p. 246-248, afb. 5.\",\"E. van de Wetering, 'Ontrouw aan Rembrandt', Kunstschrift (Openbaar Kunstbezit) (1982), p. 166-167, 171, afb. 24-26.\",\"K. Clark, 'What is a masterpiece?', Portfolio. The Magazine of the Visual Arts, 2 (1980), p. 51 + afb.\",\"H. Günther, 'Damals oder heute unverstanden. Zum Problem von Rembrandts Nachtwache', Welkunst 50 (1980), p. 1848-1850 + afb.\",\"U. Schumacher, 'Gruppenporträt und Genrebild. Zur Deutung der Photographie fur die franzosische Malerei des 19. Jahrhunderts, Giessener Beiträge zur Kunstgeschichte 4 (1979), p. 29, afb. 11.\",\"M.M. Toth-Ubbens, 'De barbier van Amsterdam. Aantekeningen over de relaties tussen het Waaggebouw en de Schouwburg in de zeventiende eeuw', Antiek 10 (1975), p. 388, afb. 12.\",\"H. Gerson, 'De Nachtwacht', Openbaar Kunstbezit, 10e jaargang, januari (1966).\",\"Chr. White, 'Rembrandt', Den Haag 1964, p. 65, 66 + afb.\",\"A.J. Moes-Veth, 'Rembrandt's Claudius Civilis en de Nachtwacht van terzijde beschouwd, Oud Holland LXXV (1960), p. 143.\",\"A.J. Moes-Veth, 'De Nachtwacht en haar oude copieen', Oud Holland LXII (1947), p. 188.\",\"J.A. Overhoff, 'De Nachtwacht op zwerftocht' (het schilderij ten tijde van WO II), http://www.cacciucco.nl/artikelen/kunst/de_nachtwacht_op_zwerftocht.html [2016]\",\"J.A. Overhoff, 'THE NIGHT WATCH ODYSSEY' (the painting during WWII) , http://www.cacciucco.nl/english/the_night_watch_odyssey.html [2017]\"],\"catRefRPK\":[],\"principalOrFirstMaker\":\"Rembrandt van Rijn\",\"dimensions\":[{\"unit\":\"cm\",\"type\":\"hoogte\",\"precision\":null,\"part\":null,\"value\":\"379,5\"},{\"unit\":\"cm\",\"type\":\"breedte\",\"precision\":null,\"part\":null,\"value\":\"453,5\"},{\"unit\":\"kg\",\"type\":\"gewicht\",\"precision\":null,\"part\":null,\"value\":\"337\"},{\"unit\":\"kg\",\"type\":\"gewicht\",\"precision\":null,\"part\":null,\"value\":\"170\"}],\"physicalProperties\":[],\"physicalMedium\":\"olieverf op doek\",\"longTitle\":\"De Nachtwacht, Rembrandt van Rijn, 1642\",\"subTitle\":\"h 379,5cm × b 453,5cm × g 337kg\",\"scLabelLine\":\"Rembrandt van Rijn (1606-1669) olieverf op doek, 1642\",\"label\":{\"title\":\"De Nachtwacht\",\"makerLine\":\"Rembrandt van Rijn (1606-1669) olieverf op doek, 1642\",\"description\":\"Rembrandts beroemdste en grootste schilderij werd gemaakt voor de Kloveniersdoelen. Dit was een van de drie hoofdkwartieren van de Amsterdamse schutterij, de burgerwacht van de stad. Rembrandt was de eerste die op een schuttersstuk alle figuren in actie weergaf. De kapitein, in het zwart, geeft zijn luitenant opdracht dat de compagnie moet gaan marcheren. De schutters stellen zich op. Met behulp van licht vestigde Rembrandt de aandacht op belangrijke details, zoals het handgebaar van de kapitein en het kleine meisje op de voorgrond. Zij is de mascotte van de schutters. De naam Nachtwacht is pas veel later ontstaan, toen men dacht dat het om een nachtelijk tafereel ging.\",\"notes\":null,\"date\":\"2019-07-02\"},\"showImage\":true,\"location\":\"HG-2.31\"},\"artObjectPage\":{\"id\":\"nl-SK-C-5\",\"similarPages\":[],\"lang\":\"nl\",\"objectNumber\":\"SK-C-5\",\"tags\":[],\"plaqueDescription\":\"Rembrandts beroemdste en grootste doek werd gemaakt voor de Kloveniersdoelen. Dit was een van de verenigingsgebouwen van de Amsterdamse schutterij, de burgerwacht van de stad. \\r\\n"
            + //
            "Rembrandt was de eerste die op een groepsportret de figuren in actie weergaf. De kapitein, in het zwart, geeft zijn luitenant opdracht dat de compagnie moet gaan marcheren. De schutters stellen zich op. Met behulp van licht vestigde Rembrandt de aandacht op belangrijke details, zoals het handgebaar van de kapitein en het kleine meisje op de achtergrond. Zij is de mascotte van de schutters.\",\"audioFile1\":null,\"audioFileLabel1\":null,\"audioFileLabel2\":null,\"createdOn\":\"2012-08-09T14:47:53.679885+00:00\",\"updatedOn\":\"2012-10-31T10:52:16.0492047+00:00\",\"adlibOverrides\":{\"titel\":null,\"maker\":null,\"etiketText\":null}}}";
}
