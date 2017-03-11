/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.geom.Rectangle2D;

/**
 * Represents Rectangle tool that draws a rectangle.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class Rectangle extends AbstractRectangularShape {   

    /**
     * Constructs a new object with location (0, 0) and size (0, 0).
     */
    public Rectangle() {
        super(new Rectangle2D.Double());     
    }
}
