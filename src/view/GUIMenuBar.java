/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import drawing.DrawingPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;

/**
 * Represents a menu bar for PowerPaint program.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class GUIMenuBar extends JMenuBar implements PropertyChangeListener {
    
    /** Generated serial version ID.*/
    private static final long serialVersionUID = 7915862936647081946L;
    
    /** UW purple color. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /** UW gold color. */
    private static final Color UW_GOLD = new Color(232, 211, 162);
    
    /** Slider major tick spacing. */
    private static final int SLIDER_MAJOR_TICK_SPACING = 5;
    
    /** Slider minor tick spacing. */
    private static final int SLIDER_MINOR_TICK_SPACING = 1;
    
    /** Slider minimum value. */
    private static final int SLIDER_MIN = 0;
    
    /** Slider maximum value. */
    private static final int SLIDER_MAX = 20;
    
    /** Slider initial value. */
    private static final int SLIDER_INITIAL = 1;
    

    /** A button group. */
    private final ButtonGroup myButtonGroup;
    
    /** A drawing panel. */
    private final DrawingPanel myPanel;
    
    /** A Clear menu item of File menu. */
    private final JMenuItem myClearButton;
    
    /** Tools menu. */
    private final JMenu myToolsMenu;

    /** 
     * Constructs a menu bar.
     * @param theFrame a frame to add a menu bar
     * @param thePanel a drawing panel
     */
    public GUIMenuBar(final JFrame theFrame, final DrawingPanel thePanel) {
        super();
        myButtonGroup = new ButtonGroup();
        myPanel = thePanel;
        myClearButton = new JMenuItem("Clear", 'C');
        myToolsMenu = new JMenu("Tools");
        
        setupFileMenu(theFrame);
        setupOptionsMenu();
        setupToolsMenu();
        setupAboutMenu();
    }
    
    /**
     * Sets up File menu.
     * @param theFrame a frame to add this menu bar
     */
    private void setupFileMenu(final JFrame theFrame) {        
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        add(fileMenu);
        
        fileMenu.add(myClearButton);
        myClearButton.setEnabled(false);
        myClearButton.addActionListener(theEvent -> {
            myPanel.clearPanel();
            myClearButton.setEnabled(false);
        });    
        
        myPanel.addPropertyChangeListener(this);
        
        fileMenu.addSeparator();
        
        final JMenuItem quitFileMenuItem = new JMenuItem("Quit", 'Q');
        fileMenu.add(quitFileMenuItem);
        quitFileMenuItem.addActionListener(theEvent -> {
            theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
        });      
    }   
         
    /**
     * Sets up Options menu.
     */
    private void setupOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic('O');
        add(optionsMenu);
        
        final JMenu thicknessOptionsMenu = new JMenu("Thickness");
        thicknessOptionsMenu.setMnemonic('T');
        
        final JSlider slider = new JSlider(SLIDER_MIN, SLIDER_MAX, SLIDER_INITIAL);
        slider.setMajorTickSpacing(SLIDER_MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(SLIDER_MINOR_TICK_SPACING);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        thicknessOptionsMenu.add(slider);
        slider.addChangeListener(theEvent -> {
            myPanel.setStroke(new BasicStroke(slider.getValue()));       
        });
        
        final JMenuItem drawColorOptionsMenuItem = new JMenuItem("Draw color...", 'D');        
        final JMenuItem fillColorOptionsMenuItem = new JMenuItem("Fill color...", 'F');
        final JCheckBoxMenuItem fillOptionsMenuItem = new JCheckBoxMenuItem("Fill");
        optionsMenu.add(thicknessOptionsMenu);
        optionsMenu.addSeparator();
        optionsMenu.add(drawColorOptionsMenuItem);
        optionsMenu.add(fillColorOptionsMenuItem);
        optionsMenu.addSeparator();
        optionsMenu.add(fillOptionsMenuItem);
        
        final ColorIcon drawIcon = new ColorIcon(UW_PURPLE);
        final ColorIcon fillIcon = new ColorIcon(UW_GOLD);
        drawColorOptionsMenuItem.setIcon(drawIcon);
        fillColorOptionsMenuItem.setIcon(fillIcon);        
        
        drawColorOptionsMenuItem.addActionListener(theEvent -> {
            final Color selectedColor = JColorChooser.showDialog(myPanel, "Draw color",
                                                                 UW_PURPLE);
            if (selectedColor != null) {
                myPanel.setColor(selectedColor);
                drawIcon.setColor(selectedColor);
            }
        });
        fillColorOptionsMenuItem.addActionListener(theEvent -> {
            final Color selectedColor = JColorChooser.showDialog(myPanel, "Fill color",
                                                                 UW_GOLD);
            if (selectedColor != null) {
                myPanel.setFillColor(selectedColor);
                fillIcon.setColor(selectedColor);
            }
        });
        fillOptionsMenuItem.addActionListener(theEvent -> {
            myPanel.setFill();
        });
    }
    
    /**
     * Sets up Tools menu.
     */
    private void setupToolsMenu() {
        myToolsMenu.setMnemonic('T');
        add(myToolsMenu);
    }
    
    /**
     * Sets up About menu.
     */
    private void setupAboutMenu() {        
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        add(helpMenu);
                
        final JMenuItem aboutHelpMenuItem = new JMenuItem("About...", 'A');
        aboutHelpMenuItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myPanel, "TCSS 305 PowerPaint\nAutumn 2016\n"
                                        + "Evgeniia Shcherbinina", "About",
                                        JOptionPane.PLAIN_MESSAGE, 
                                        new ImageIcon("images/paint2.jpg"));
        });
        helpMenu.add(aboutHelpMenuItem);
    }
    
    /**
     * Creates a button in Tool menu.
     * @param theAction the action associated with this button
     */
    public void createToolMenuButton(final ToolAction theAction) { 
        final JRadioButtonMenuItem button = new JRadioButtonMenuItem(theAction);
        myToolsMenu.add(button);
        myButtonGroup.add(button);
        final String name = "images/" + button.getText().toLowerCase();
        final ImageIcon colorIcon = new ImageIcon(name + ".gif");
        final ImageIcon bwIcon = new ImageIcon(name + "_bw.gif");
        
        if (button.getText().equals("Line")) {
            button.setSelected(true);
            button.setIcon(colorIcon);
        }
        
        button.addChangeListener(theEvent -> {            
            if (button.isSelected()) {
                button.setIcon(colorIcon);
            } else {
                button.setIcon(bwIcon);
            }            
        });         
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("Drawing panel is not empty".equals(theEvent.getPropertyName())) {            
            if ((int) theEvent.getNewValue() != 0) {
                myClearButton.setEnabled(true);
            }
        }        
    }

}
