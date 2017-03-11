/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Represents a tool bar for PowerPaint program.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class GUIToolBar extends JToolBar {
    
    /** Generated serial version ID. */
    private static final long serialVersionUID = -3082575901545612626L;
    
    /** A group of buttons. */
    private final ButtonGroup myGroup;

    /**
     * Constructs a tool bar.
     */
    public GUIToolBar() {
        super();
        myGroup = new ButtonGroup();
    }
    
    /**
     * Creates a tool bar button.
     * @param theAction the action associated with the button
     */
    public void createToolBarButton(final ToolAction theAction) {
        final JToggleButton button = new JToggleButton(theAction);
        button.setSelectedIcon(new ImageIcon("images/" + button.getText().toLowerCase()
                                             + ".gif"));
        if (button.getText().equals("Line")) {
            button.setSelected(true);            
        }
        myGroup.add(button);
        add(button);        
    }
}
