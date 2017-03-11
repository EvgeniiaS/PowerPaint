/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Represents Line tool that draws lines.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class Line implements ToolInterface {
    
    /** A shape that represents this object. */
    private Line2D myLine;  

    /**
     * Constructs a new object with coordinates of the start and end points (0, 0).
     */
    public Line() {
        super();
        myLine = new Line2D.Double();        
    }

    @Override
    public Shape createShape(final Point theP1) {
        myLine = new Line2D.Double((Point2D) theP1.clone(), (Point2D) theP1.clone());
        return myLine;
    } 
    
    @Override
    public Shape drawShape(final Point theP1, final Point theP2) {
        myLine.setLine((Point2D) theP1.clone(), (Point2D) theP2.clone());
        return myLine;
    }    
}
