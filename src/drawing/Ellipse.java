/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.geom.Ellipse2D;

/**
 * Represents Ellipse tool that draws an ellipse.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class Ellipse extends AbstractRectangularShape {    
    
    /**
     * Constructs a new object with location (0, 0) and size (0, 0).
     */
    public Ellipse() {
        super(new Ellipse2D.Double());
    }   
}
