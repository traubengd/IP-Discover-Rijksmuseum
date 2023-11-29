package ip.rijksmuseumquiz.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageEditor {
    public BufferedImage getImageFromUrl(String imageUrl) throws URISyntaxException, IOException {
        BufferedImage image = null;
        URL url = new URI(imageUrl).toURL();
        image = ImageIO.read(url);
        return image;
    }

    public BufferedImage getSubImage(BufferedImage inputImage, int width, int height, int xInput, int yInput) {
        BufferedImage subImage = inputImage.getSubimage(xInput, yInput, width, height);
        return subImage;
    }
}
