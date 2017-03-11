/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package view;

import drawing.DrawingPanel;
import drawing.Ellipse;
import drawing.Line;
import drawing.Pencil;
import drawing.Rectangle;
import drawing.ToolInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * The graphical user interface for PowerPaint program.
 * @author Evgeniia Shcherbinina
 * @version 11/20/2016
 */
public class GUI extends JFrame {    

    /** Generated serial version ID. */
    private static final long serialVersionUID = 7033246162815741430L;
    
    /** Dimension of the drawing panel. */
    private static final Dimension PANEL_DIMENSION = new Dimension(600, 400);
    
    
    /**
     * Constructs the GUI.
     */
    public GUI() {
        super("TCSS 305 - PowerPaint");       
    }
    
    /**
     * Sets up the GUI.
     */
    public void start() {         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PANEL_DIMENSION);
        
        final ImageIcon img = new ImageIcon("images/paint2.jpg");
        setIconImage(img.getImage());
        
        final DrawingPanel panel = new DrawingPanel();
        panel.setBackground(Color.WHITE);
        panel.setVisible(true);
        panel.setPreferredSize(PANEL_DIMENSION);         
        panel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));        
        add(panel);        
      
        final GUIMenuBar menuBar = new GUIMenuBar(this, panel); 
        setJMenuBar(menuBar); 
        
        final GUIToolBar toolBar = new GUIToolBar();
        add(toolBar, BorderLayout.SOUTH);
       
        final ToolInterface[] tools = {new Line(), new Rectangle(), new Ellipse(), 
                                       new Pencil()};
        final List<ToolAction> menuToolActions = new ArrayList<>();
        for (int i = 0; i < tools.length; i++) {
            menuToolActions.add(new ToolAction(tools[i].getClass().getSimpleName(), 
                                new ImageIcon("images/" + tools[i].getClass().getSimpleName() 
                                              + "_bw.gif"), tools[i], panel));
        }        
        
        for (final ToolAction action: menuToolActions) {            
            toolBar.createToolBarButton(action);
            menuBar.createToolMenuButton(action);            
        }               
        
        final Toolkit kit = Toolkit.getDefaultToolkit();        
        setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - getHeight() / 2));
        
        pack();
        setVisible(true);         
    }
}
