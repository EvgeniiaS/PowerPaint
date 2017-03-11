/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

/**
 * Represents an object with particular shape, color, line thickness.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class MyDrawnShape {
    
    /** Shape of this object. */
    private final Shape myShape;
    
    /** Color of this object outline. */
    private final Color myColor;
    
    /** Line thickness of this object. */
    private final BasicStroke myStroke;
    
    /** Color of this filled object. */
    private final Color myFillColor;
    
    /** Keeps information whether an object is filled or not. */
    private final boolean myFill;

    /**
     * Creates an object with specified parameters.
     * @param theShape shape of this object
     * @param theColor color of this object outline
     * @param theStroke line thickness of this object
     * @param theFillColor color of this filled object
     * @param theFill whether this object filled or not
     */
    public MyDrawnShape(final Shape theShape, final Color theColor, 
                        final BasicStroke theStroke, final Color theFillColor,
                        final boolean theFill) {
        super();
        myShape = theShape;
        myColor = theColor;
        myStroke = theStroke;
        myFillColor = theFillColor;
        myFill = theFill;
    }
    
    /**
     * Returns a shape of this object.
     * @return shape of this object
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Returns color of this object outline.
     * @return color of this object outline
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Returns line thickness of this object.
     * @return line thickness of this object
     */
    public BasicStroke getStroke() {
        return myStroke;
    } 
    
    /**
     * Returns color of this filled object.
     * @return color of this filled object
     */
    public Color getFillColor() {        
        return myFillColor;
    }
    
    /**
     * Returns whether this objects is filled or not.
     * @return whether this object is filled or not
     */
    public boolean isFilled() {
        return myFill;
    }
   
}
