package src;

import javax.swing.*;
// import javax.swing.JOptionPane;
// import javax.swing.JTextArea;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import java.io.*;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.util.Vector;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.event.*; // needed for windowlistener

public class Main {
    public static JFrame frame;
    public static MenuBar menuBar;
    public static JTextArea ta;
    public static Vector<Account> dataStore;
    public static Account account;
    public static boolean saved;

    /*
     * MAIN
     */
    public static void main(String[] args) {
        dataStore = new Vector<Account>();
        // Start up application
        frame = new JFrame("Checking Account:");
        
        // Create custom listener to prompt user to save before closing
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveOnClose();
            }
        });
        
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

    /**************************************************************************
        HELPER FUNCTIONS:
    **************************************************************************/ 
    
    public static void initializeAccount() {
        // Purpose: Create a new account, add it to vector datastore

        // Get an account name and initial balance
        String name = dialogQuestion("Account Name", "Enter the Account Name:");
        String balance = dialogQuestion("Initial Balance", "Enter your initial balance:");

        // Validation of initial balance, by calling another helper function
        if (!emptyInput(balance) && validDouble(balance)) {
            // If balance is acceptable, then create a new account
            account = new Account(name, Double.parseDouble(balance));
            // Add account to vector datastore and let user know
            dataStore.add(account);
            ta.setText("New account added for " + name);
            promptToSave();
        // If balance is invalid, then returns false, give user feedback why
        // and prevent showing main menu GUI
        } else {
            ta.setText("Account not added.");
        }
    }

    public static boolean emptyInput(String input) {
        // Given a String input from a JOptionPane input dialog box, checks if the 
        // input is empty or dialog canceled
        if (input == null || input.trim().isEmpty()) {
            dialogError("No input provided.");
            return true;
        } else {
            return false;
        }
    }

    public static boolean validDouble(String input) {
        // Attempt to parse user input and cast to a double
        try {
            double number = Double.parseDouble(input);
            // Also check if input number is negative
            // i.e. user should not be writing checks with negative amounts
            if (number < 0) {
                dialogError("Input cannot be negative.");
                return false;
            }
            return true;
        // If parse fails, let user know their input was invalid
        } catch (NumberFormatException e) {
            dialogError("Invalid input.");
            return false;
        }
    }

    // Validation check specifically for deposit transactions where 
    // different rules apply to what is a valid input
    public static boolean isNotValid(String input) {
        // Deposits can be left blank for either cash or checks inputs
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Attempt to parse user input and cast to a double
        try {
            double number = Double.parseDouble(input);
            // Also check if input number is negative,
            // i.e. user should not be using negative amounts
            if (number < 0) {
                dialogError("Input cannot be negative.");
                return true;
            }
            return false;
        // If parse fails, let user know their input was invalid
        } catch (NumberFormatException e) {
            return true;
        }
    }

    // Used to display dollar amounts in correct format, negatives should be 
    // encased in parenthesis instead of usual '-' prefix
    public static String formatDollar(double amount) {
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        String text;

        if(amount < 0)
            text = "(" + dollar.format(amount * -1) + ")";
        else
            text = dollar.format(amount);

        return text;
    }

    // Used to convert the transaction code into text for interpretation
    public static String convertText(int transactionCode) {
        switch (transactionCode) {
            case 1: return "Check";
            case 2: return "Deposit";
            case 3: return "Srv. Chg.";
            default: return "Invalid Code";
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean openFile() {
        boolean fileOpened;

        // Create File Chooser and set initial settings
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose File to Open");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        // Prompt user to select a file
        int result = fileChooser.showOpenDialog(null);
        // Handle submitted file
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get their file
            File selectedFile = fileChooser.getSelectedFile();

            // Try loading the file with their account data
            try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(selectedFile))
            ) {
                dataStore = (Vector<Account>)ois.readObject();
                // On default, load first element, TODO: Change later
                account = dataStore.elementAt(0);
                saved = true;
                // If successful set and return the status of the function
                fileOpened = true;
                ois.close();
            // If problems arise, return status of the function as false
            } catch (IOException | ClassNotFoundException e) {
                fileOpened = false;
            }
        // User canceled, returned false 
        } else {
            fileOpened = false;
        }
        
        return fileOpened;
    }

    public static boolean saveFile() {
        // Prompt user how to save
        int save = JOptionPane.showConfirmDialog(
            null, 
            "Would you like to use the current default file:\n./acct.dat", 
            "Select An Option", 
            JOptionPane.YES_NO_CANCEL_OPTION
        );

        // User selects to save using default location and filename
        if (save == JOptionPane.YES_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("acct.dat")
                )
            ) {
                oos.writeObject(dataStore);
                saved = true;
            } catch (IOException e) {
                saved = false;
            }
        // Users selects where and how to name filename using filechooser
        } else if (save == JOptionPane.NO_OPTION) {
            // Create a JFrame for the JFileChooser dialog
            JFrame frameSave = new JFrame("Save Object");
            frameSave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JFileChooser window with some default settings
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save");
            fileChooser.setCurrentDirectory(
                new File(System.getProperty("user.home"))
            );

            // Show the save JFileChooser window
            int result = fileChooser.showSaveDialog(frameSave);
            // Handle submited file
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                // Write to the file
                try (
                    ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(selectedFile)
                    )
                ) {
                    oos.writeObject(dataStore);
                    // Make sure isModified is reset after every save
                    saved = true;
                } catch (IOException e) {
                    saved = false;
                }
            // Exit if user cancels/closes filechooser
            } else {
                saved = false;
            }

            frameSave.dispose();
        // Handle user canceling initial dialog box
        } else {
            saved = false;
        }

        return saved;
    }
    
    private static void saveOnClose() {
        if (!saved) {
            // Prompt user to save changes
            int option = JOptionPane.showConfirmDialog(
                null,
                (
                    "The data in the application is not saved.\n" +
                    "Would you like to save it before exiting the application?"
                ),
                "Select an Option",
                JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                if(saveFile()) {
                    // Exit only if save was successful (user didn't cancel)
                    frame.dispose();
                    System.exit(0);
                // Recursive call to make sure if use does not want to save
                } else {
                    saveOnClose();
                }
            } else {
                // Exit without saving
                frame.dispose();
                System.exit(0);
            }
        // No changes detected, exit directly
        } else {
            frame.dispose();
            System.exit(0);
        }
    }

    public static void promptToSave() {
        saved = false;
    }

    public static String dialogQuestion(String message) {
        return JOptionPane.showInputDialog(
            null,
            message,
            null,
            JOptionPane.QUESTION_MESSAGE
        );
    } 
    public static String dialogQuestion(String title, String message) {
        return JOptionPane.showInputDialog(
            null,
            message,
            title,
            JOptionPane.QUESTION_MESSAGE
        );
    } 

    public static void dialogError(String message) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static void dialogInfo(String message) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Message", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public static int dialogConfirm(String message) {
        return JOptionPane.showConfirmDialog(
            null, 
            message, 
            null, 
            JOptionPane.YES_NO_CANCEL_OPTION
        );
    }
}
