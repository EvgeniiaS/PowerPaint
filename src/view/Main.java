package view;

import java.awt.EventQueue;

/**
 * 
 * @author Shcherbinina Evgeniia
 * @version 11/11/2016
 */
public final class Main {

    /**
     * 
     */
    private Main() {
        // do nothing
    }

    /**
     * 
     * @param theArgs 
     */
    public static void main(final String[] theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().start();
            }
        });
    }

}
