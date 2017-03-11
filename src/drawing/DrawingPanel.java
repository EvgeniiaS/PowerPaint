/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


/**
 * Represents a panel for drawing.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class DrawingPanel extends JPanel {
    
    /** Generated serial version ID. */
    private static final long serialVersionUID = 2860084640152457453L;

    /** No current shape for drawing. */    
    private static final Shape NO_CURRENT_SHAPE = null;
    
    /** Class name of the shape drawn by Pencil tool. */
    private static final String PENCIL_SHAPE_TOOL = "java.awt.geom.Path2D$Double";
    
    /** UW purple color. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /** UW gold color. */
    private static final Color UW_GOLD = new Color(232, 211, 162);
    
    /** Default point. */
    private static final Point DEFAULT_POINT = new Point(-10, -10);
    
    
    /** A list of shapes to draw. */
    private final List<MyDrawnShape> myDrawnShapes;
    
    /** The start point for drawing. */
    private Point myP1;
    
    /** A current shape to draw. */
    private Shape myCurrentShape;
    
    /** Color to draw an outline. */
    private Color myColor;
    
    /** Color to draw a filled shape. */
    private Color myFillColor;
    
    /** Line thickness to draw an outline.*/
    private BasicStroke myStroke;
    
    /** A drawing tool. */
    private ToolInterface myToolInterface;
    
    /** Whether to draw filled shapes or not. */
    private boolean myFill;
    

    /**
     * Represents a panel for drawing.
     */
    public DrawingPanel() {        
        super();
        myDrawnShapes = new ArrayList<>();
        myP1 = DEFAULT_POINT;
        myColor = UW_PURPLE;
        myFillColor = UW_GOLD;
        myStroke = new BasicStroke(1);
        myToolInterface = new Line();
        myFill = false;
        myCurrentShape = NO_CURRENT_SHAPE;
        
        addListeners();        
    }
    
    /**
     * Adds listeners to a drawing panel.
     */
    private void addListeners() {
        final MouseAdapter mouseAdapter = new MouseHandler();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);         
               
        for (final MyDrawnShape s: myDrawnShapes) {
            if (s.getStroke().getLineWidth() != 0) {
                if (s.isFilled()) {
                    g2d.setPaint(s.getFillColor());
                    g2d.fill(s.getShape());
                }
                g2d.setStroke(s.getStroke());
                g2d.setPaint(s.getColor());
                g2d.draw(s.getShape());
            }
        }
        
        g2d.setStroke(myStroke);
        if (myCurrentShape != null && myStroke.getLineWidth() != 0) {  
            if (myFill && !myCurrentShape.getClass().getName().equals(PENCIL_SHAPE_TOOL)) { 
                g2d.setPaint(myFillColor);
                g2d.fill(myCurrentShape);                
            }
            g2d.setPaint(myColor);            
            g2d.draw(myCurrentShape);           
        }        
    }
    
    /**
     * Adds a shape with specified parameters to a list of shapes and fires 
     * property change event for listeners.
     */
    public void addShape() {
        if (myCurrentShape.getClass().getName().equals(PENCIL_SHAPE_TOOL)) {
            myDrawnShapes.add(new MyDrawnShape(myCurrentShape, myColor, myStroke,
                                           myFillColor, false));
        } else {
            myDrawnShapes.add(new MyDrawnShape(myCurrentShape, myColor, myStroke,
                                              myFillColor, myFill));
        }
        
        firePropertyChange("Drawing panel is not empty", 0, myDrawnShapes.size());
    }
    
    /**
     * Sets a tool to a drawing panel.
     * @param theTool toll to be set
     */
    public void setTool(final ToolInterface theTool) {
        myToolInterface = theTool;
    }

    /**
     * Sets a line thickness to a drawing panel.
     * @param theStroke line thickness to be set
     */
    public void setStroke(final BasicStroke theStroke) {
        myStroke = theStroke; 
    }
    
    /**
     * Clears a drawing panel.
     */
    public void clearPanel() {
        myDrawnShapes.clear();
        repaint();
    }  
    
    /**
     * Sets color to a drawing panel.
     * @param theColor color to be set
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Sets color for drawing filled shapes.
     * @param theColor color to be set
     */
    public void setFillColor(final Color theColor) {
        myFillColor = theColor;
    }
    
    /**
     * Sets whether to fill shapes or not.
     */
    public void setFill() {
        myFill = !myFill;
    }
    
    
    
    /**
     * Represents listeners for a drawing panel.
     * @author Evgeniia Shcherbinina
     * @version 11/20/2016
     */
    private class MouseHandler extends MouseAdapter {
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myP1 = theEvent.getPoint();
            myCurrentShape = myToolInterface.createShape(theEvent.getPoint());
            repaint();
        }   
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            addShape();
            myCurrentShape = NO_CURRENT_SHAPE;
        }        
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentShape = myToolInterface.drawShape(myP1, theEvent.getPoint());
            repaint();
        }
    }    
}