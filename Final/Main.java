package Final;

import javax.swing.*;
import java.awt.Font;

public class Main {
    public static JFrame frame;
    public static MenuBar menuBar;
    public static JTextArea ta;

    public static void main(String[] args) {
        frame = new JFrame("Final Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create & Set JMenuBar
        menuBar = new MenuBar();
        frame.setJMenuBar(menuBar);

        // Create Text Area for Text
        ta = new JTextArea(10,50);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        frame.getContentPane().add(ta);
        
        // Display application frame
        frame.pack();
        frame.setVisible(true);
    }
}