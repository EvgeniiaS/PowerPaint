/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import drawing.DrawingPanel;
import drawing.ToolInterface;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;


/**
 * Represents an action which occurs when a tool is selected. 
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class ToolAction extends AbstractAction {
        
    /** Generated serial version ID. */
    private static final long serialVersionUID = 5988024582925900755L;
    
    /** A drawing tool. */
    private final ToolInterface myShape;
    
    /** A drawing panel. */
    private final DrawingPanel myPanel;
    
    /**
     * Constructs the action.
     * @param theName the name of the action
     * @param theIcon the icon of the action
     * @param theShape the tool 
     * @param thePanel the drawing panel
     */
    ToolAction(final String theName, final ImageIcon theIcon, final ToolInterface theShape,
               final DrawingPanel thePanel) {
        super(theName, theIcon);
        myShape = theShape;
        myPanel = thePanel;
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        putValue(Action.SELECTED_KEY, true);
        myPanel.setTool(myShape);            
    }        
}