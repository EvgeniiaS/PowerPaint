/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * Represents Pencil tool that draws a freeform curve.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class Pencil implements ToolInterface {
    
    /** A shape that represents this object. */
    private Path2D.Double myPencil;

    /**
     * Constructs a new empty object.
     */
    public Pencil() {
        myPencil = new Path2D.Double();
    }

    @Override
    public Shape createShape(final Point theP1) {
        myPencil = new Path2D.Double();
        myPencil.moveTo(theP1.getX(), theP1.getY());
        return myPencil;
    }

    @Override
    public Shape drawShape(final Point theP1, final Point theP2) {
        myPencil.lineTo(theP2.getX(), theP2.getY());
        return myPencil;
    }
}
