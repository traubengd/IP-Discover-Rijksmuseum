package ip.rijksmuseumquiz.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
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
}
