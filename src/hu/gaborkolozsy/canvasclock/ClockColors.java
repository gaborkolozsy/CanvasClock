package hu.gaborkolozsy.canvasclock;

import javafx.scene.paint.Color;

/**
 * Own colors enum for draw.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 1.0.0
 * @see Color
 */
public enum ClockColors {    
    BLUE1 (Color.rgb(40, 209, 250, 1)),
    BLUE2 (Color.rgb(100, 209, 250, 1)),
    BLUE3 (Color.rgb(140, 209, 250, 1)),
    BLUE4 (Color.rgb(40, 196, 255, .4)),
    BLUE5 (Color.rgb(40, 196, 255, .6)),
    RED1 (Color.rgb(255, 0, 0, .4)),
    RED2 (Color.rgb(255, 0, 0, .8)),
    RED3 (Color.rgb(255, 0, 0, .6));
    
    private final Color color;
    
    ClockColors(Color color) {
        this.color = color;
    }
    
    /**
     * Get the color.
     * @return color
     */
    public Color get() {
        return color;
    }
    
}
