/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 * Represents a small icon for a menu bar.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class ColorIcon implements Icon {
    
    /** Height and width of an icon. */
    private static final int SIZE = 10;
    
    
    /**Height and width of this icon. */ 
    private final int mySize;
    
    /** Color of this  icon. */
    private java.awt.Color myColor;

    /**
     * Creates an icon with specified color. 
     * @param theColor color of this icon
     */
    public ColorIcon(final java.awt.Color theColor) {
        super();
        mySize = SIZE;
        myColor = theColor;
    }
    
    /**
     * Sets color of an icon.
     * @param theColor color to be set
     */
    public void setColor(final java.awt.Color theColor) {
        myColor = theColor;
    }
    

    @Override
    public int getIconHeight() {
        return mySize;
    }

    @Override
    public int getIconWidth() {
        return mySize;
    }

    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphics,
                          final int theX, final int theY) {
        theGraphics.setColor(myColor);
        theGraphics.fillRect(theX, theY, mySize, mySize);        
    }

}
