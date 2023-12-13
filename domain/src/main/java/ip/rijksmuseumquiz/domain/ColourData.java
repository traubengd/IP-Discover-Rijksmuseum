package ip.rijksmuseumquiz.domain;
import java.awt.Color;

public class ColourData {
    private Color colourValues;
    private int presencePercentage;

    public ColourData(Color color, int percentage){
        this.colourValues = color;
        this.presencePercentage = percentage;
    }

    public Color getColor(){
        return colourValues;
    }

    public int getPercentage(){
        return presencePercentage;
    }
}
