/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.Point;
import java.awt.Shape;

/**
 * An interface for objects that represent shapes that can be drawn.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public interface ToolInterface {
    
    /**
     * Creates and starts to draw a Shape.
     * @param theP1 the start point
     * @return the created Shape
     */
    Shape createShape(Point theP1);

    /**
     * Draws a Shape.
     * @param theP1 the start point
     * @param theP2 the end point
     * @return the drawn Shape
     */
    Shape drawShape(Point theP1, Point theP2);
}
