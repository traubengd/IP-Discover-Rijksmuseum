package ip.rijksmuseumquiz.domain;

import java.awt.Color;
import java.awt.Graphics2D;
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

    public BufferedImage createColourScheme(Color[] colors) {
        int imageHeight = 300;
        int barWidth = 100;
        int imageWidth = (colors.length)*barWidth;
        BufferedImage colourSchemeImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = colourSchemeImage.createGraphics();
        for (int i = 0; i < colors.length; i++){
            graphics.setColor(colors[i]);
            graphics.fillRect(i*barWidth, 0, barWidth, imageHeight);
        }
        return colourSchemeImage;
    }
}
