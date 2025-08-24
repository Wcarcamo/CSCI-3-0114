package Final;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class q6 {
    public static void main(String[] args) {
        getDatFile();
    }

    public static String getDatFile() {
        boolean valid = false;
        String file = "";

        do {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose File to Open");
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile.getName().endsWith(".dat")) {
                    valid = true;
                    file = selectedFile.getName();
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "File does not end in .dat", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } while (!valid);
        return file;
    }
}
