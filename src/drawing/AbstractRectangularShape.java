/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RectangularShape;

/**
 * An abstract class that represents Rectangular Shapes.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public abstract class AbstractRectangularShape implements ToolInterface {
    
    /** Represents a shape of an object. */
    private RectangularShape myRectangularShape;

    /**
     * Constructs an object.
     * @param theShape A shape of an object to create
     */
    protected AbstractRectangularShape(final RectangularShape theShape) {
        myRectangularShape = (RectangularShape) theShape.clone();
    }

    @Override
    public Shape createShape(final Point theP1) {
        myRectangularShape = (RectangularShape) myRectangularShape.clone();
        myRectangularShape.setFrameFromDiagonal(theP1.getX(), theP1.getY(), 
                                         theP1.getX(), theP1.getY());
        return myRectangularShape;
    }

    @Override
    public Shape drawShape(final Point theP1, final Point theP2) {        
        myRectangularShape.setFrameFromDiagonal(theP1.getX(), theP1.getY(), 
                                         theP2.getX(), theP2.getY());
        return myRectangularShape;
    } 
}
