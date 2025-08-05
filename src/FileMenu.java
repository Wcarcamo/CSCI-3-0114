package src;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class FileMenu extends JMenu{
    private JMenuItem openFile, saveFile;
    
    public FileMenu() {
        super("File");

        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        menuListener listener = new menuListener();
        openFile.addActionListener(listener);
        saveFile.addActionListener(listener);

        this.add(openFile);
        this.add(saveFile);
    }

    private class menuListener implements ActionListener {
        //--------------------------------------------------------------
        //  Calls the method to process the option for which radio
        //  button was pressed.
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            String source = event.getActionCommand();

            if (source.equals("Open File")) {
                // open();
                System.out.println("TODO: Open File");
            } else if (source.equals("Saave File")) {
                // save();
                System.out.println("TODO: Save File");
            } 
        }
    }
}
