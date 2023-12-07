package ip.rijksmuseumquiz.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEditorTest {
    @Test
    public void imageEditorExtractsImageOfCorrectDimensions() throws IOException{
        BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/testImage.jpg"));
        assertEquals(5656, image.getWidth());
        assertEquals(4704, image.getHeight());
    }
    
    @Test
    public void imageEditorCanProduceASubImageOfSpecifiedDimensions() throws IOException{
        ImageEditor imageEditor = new ImageEditor();
        BufferedImage greaterImage = ImageIO.read(this.getClass().getResourceAsStream("/testImage.jpg"));
        BufferedImage subImage = imageEditor.getSubImage(greaterImage, 500, 500, 300, 300);
        assertEquals(500, subImage.getWidth());
        assertEquals(500, subImage.getHeight());
    }

    @Test
    public void imageEditorCanCreateAnImageObjectOfFiveSpecifiedColours() {
        ImageEditor imageEditor = new ImageEditor();
        Color[] testColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN};
        BufferedImage colourSchemeImage = imageEditor.createColourScheme(testColors);
        Color firstBarColour = new Color(colourSchemeImage.getRGB(0, 0));
        Color secondBarColour = new Color(colourSchemeImage.getRGB(100, 0));
        Color thirdBarColour = new Color(colourSchemeImage.getRGB(200, 0));
        Color fourthBarColour = new Color(colourSchemeImage.getRGB(300, 0));
        Color fifthBarColour = new Color(colourSchemeImage.getRGB(400, 0));
        assertEquals(Color.RED, firstBarColour);
        assertEquals(Color.GREEN, secondBarColour);
        assertEquals(Color.BLUE, thirdBarColour);
        assertEquals(Color.YELLOW, fourthBarColour);
        assertEquals(Color.CYAN, fifthBarColour);
        assertEquals(500, colourSchemeImage.getWidth());
    }

    @Test
    public void colourSchemeImageCreatedIsDependentInWidthOnLengthOfArrayOfColors() {
        ImageEditor imageEditor = new ImageEditor();
        Color[] testColors = {Color.RED, Color.GREEN};
        BufferedImage colourSchemeImage = imageEditor.createColourScheme(testColors);
        assertEquals(200, colourSchemeImage.getWidth());
    }
}
