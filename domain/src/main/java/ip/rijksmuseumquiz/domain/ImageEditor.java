package ip.rijksmuseumquiz.domain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

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

    public BufferedImage createColourScheme(ArrayList<ColourData> coloursInPainting) {
        int imageHeight = 300;
        int imageWidth = 500;
        int totalOfPercentages = 0;
        for (int i = 0; i < coloursInPainting.size(); i++){
            totalOfPercentages += coloursInPainting.get(i).getPercentage();
        }
        BufferedImage colourSchemeImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = colourSchemeImage.createGraphics();
        int originForRectangle = 0;
        for (int i = 0; i < coloursInPainting.size(); i++){
            int barWidth = coloursInPainting.get(i).getPercentage() * imageWidth / totalOfPercentages;
            graphics.setColor(coloursInPainting.get(i).getColor());
            graphics.fillRect(originForRectangle, 0, barWidth, imageHeight);
            originForRectangle += barWidth;
        }
        return colourSchemeImage;
    }
}
