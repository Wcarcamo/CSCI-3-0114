package src;
//-----------------------------------------------------------------
//  Demonstrates the use of radio buttons with listener
//  and multiple dialog boxes for user interaction.
//  Demonstrates the use of the JOptionPane class.
//********************************************************************
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;

public class Main {
    public static JFrame frame;
    public static Account myAccount;

    /*
     * MAIN
     */
    public static void main(String[] args) {;
        /* 
            Uncomment line below during development to test transactions 
            using function instead of manually entering everything through GUI
            every single time for regression testing
        */
        // sampleRun();
        
        // Set up Account before presenting Main Menu GUI Option Panel
        // Users will be prompted if they would like to load an existing account
        // from a file, if they do not load an account or there are issues
        // loading the account, users will have to initialize a new account
        if (loadAccount() || initializeAccount()) {
            // If setting up account was successful set up GUI menu 
            frame = new JFrame("Checking Account:");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Display the 4 options in the panel in the Main Menu
            CheckOptionsPanel panel = new CheckOptionsPanel();
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
        // If initialization fails, program will tell user that the session will
        // close due to not setting up an account
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Account not set up, your session will close."
            );
        }
    }

    /*
     * HELPER FUNCTIONS:
     */

     public static boolean loadAccount() {
        boolean hasAccount;

        // Prompt users to 
        int selection = JOptionPane.showConfirmDialog(
            null, 
            "Would you like to use an existing account?", 
            "Load Account", 
            JOptionPane.YES_NO_OPTION
        );

        if (selection == JOptionPane.YES_OPTION) {
            if(openFile())          
                hasAccount = true;
            else {
                JOptionPane.showMessageDialog(
                    null, 
                    (
                        "Account was not loaded.\n" +
                        "Please create a new account."
                    ),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                hasAccount = false; 
            }
        }
        else
            hasAccount = false;

        return hasAccount;
     }
    
    // Prompts user to set up an account before presenting the GUI main menu
    public static boolean initializeAccount() {
        // First get users account name and initial balance
        String name = JOptionPane.showInputDialog(
            null,
            "Enter the Account Name:",
            "Account Name",
            JOptionPane.QUESTION_MESSAGE
        );
        String balance = JOptionPane.showInputDialog(
            null, 
            "Enter your initial balance:", 
            "Initial Balance", 
            JOptionPane.QUESTION_MESSAGE
        );

        // Validation Check of user input for initial balance, accepts a balance
        // string and calls another helper function
        if (isValid(balance)) {
            // If balance is acceptable, then create a checking account
            myAccount = new Account(name, Double.parseDouble(balance));
            // Retuns true which allows main to continue and present GUI panel
            return true;
        // If balance is invalid, then returns false, give user feedback why
        // and prevent showing main menu GUI
        } else {
            return false;
        }
    }

    // Given a String input from a JOptionPane input dialog box, checks if the 
    // input can be converted to a valid double data type, 
    // returns boolean if successful or not
    public static boolean isValid(String input) {
        // Verify if user did not cancel the dialog box or inputed an empty string
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "No input provided.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        // Attempt to parse user input and cast to a double
        try {
            double number = Double.parseDouble(input);
            // Also check if input number is negative
            // i.e. user should not be writing checks with negative amounts
            if (number < 0) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Input cannot be negative.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
            return true;
        // If parse fails, let user know their input was invalid
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Invalid input.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
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
                JOptionPane.showMessageDialog(
                    null, 
                    "Input cannot be negative.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
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
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                myAccount = (Account) ois.readObject();
                // Make sure isModified is reset on new loads
                myAccount.resetModified();
                // If successful set and return the status of the function
                fileOpened = true;
                // System.out.println("Name: " + myAccount.getName());
                // System.out.println("Balance: " + myAccount.getBalance());
            // If problems arise, return status of the function as false
            } catch (IOException | ClassNotFoundException e) {
                fileOpened = false;
            }
        // User canceled 
        } else {
            fileOpened = false;
        }
        
        return fileOpened;
    }

    public static void saveFile() {
        int selection = JOptionPane.showConfirmDialog(
            null, 
            "Would you like to use the current default file:\n./acct.dat", 
            "Select An Option", 
            JOptionPane.YES_NO_CANCEL_OPTION
        );

        if (selection == JOptionPane.YES_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("acct.dat")
                )
            ) {
                oos.writeObject(Main.myAccount);
                // Make sure isModified is reset after every save
                myAccount.resetModified();
                System.out.println("Object serialized successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (selection == JOptionPane.NO_OPTION) {
            // Create a JFrame for the JFileChooser dialog
            JFrame frameSave = new JFrame("Save Object");
            frameSave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save"); // Set dialog title
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Start in user's home directory
            // fileChooser.setSelectedFile(new File("objectData.ser")); // Suggest a default filename

            // Show the save dialog
            int result = fileChooser.showSaveDialog(frameSave);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                    oos.writeObject(Main.myAccount);
                    System.out.println("Object serialized successfully to: " + selectedFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error saving file: " + e.getMessage());
                }
            } else {
                System.out.println("Save operation canceled by user.");
            }

            // Optional: Dispose of the frame if you don't need it anymore
            frameSave.dispose();
        }

        // if (selection != JOptionPane.CANCEL_OPTION) {
            
        // }
    }

    // Test Script for sample run - got tired of manually testing GUI
    public static void sampleRun() {
        double tmpAmount, tmpCash, tmpChecks;
        Check tmpCheck;
        Deposit tmpDeposit;
        
        myAccount = new Account("Will", 500.0);

        System.out.println("TRANSACTION # 1: CHECK - $50");
        tmpAmount = 50;
        myAccount.setBalance(myAccount.getBalance() - tmpAmount);
        tmpCheck = new Check(
            myAccount.checking.getTransactionID(), 
            TransactionCode.CHECK.ordinal(), 
            tmpAmount, 
            999
        );
        myAccount.checking.addTransaction(tmpCheck);
        System.out.print(tmpCheck.setSummary());
        System.out.print("\n\n");
        
        System.out.println("TRANSACTION # 2: DEPOSIT - $35 + 35");
        tmpCash = 35;
        tmpChecks = 35;
        tmpAmount = tmpCash + tmpChecks;
        myAccount.setBalance(myAccount.getBalance() + tmpAmount);
        tmpDeposit = new Deposit(
            myAccount.checking.getTransactionID(), 
            TransactionCode.DEPOSIT.ordinal(), 
            tmpCash, 
            tmpChecks
        );
        myAccount.checking.addTransaction(tmpDeposit);
        System.out.print(tmpDeposit.setSummary());
        System.out.print("\n\n  ");

        System.out.println("TRANSACTION # 3: CHECK - $480");
        tmpAmount = 480;
        myAccount.setBalance(myAccount.getBalance() - tmpAmount);
        tmpCheck = new Check(
            myAccount.checking.getTransactionID(), 
            TransactionCode.CHECK.ordinal(), 
            tmpAmount, 
            1000
        );
        myAccount.checking.addTransaction(tmpCheck);
        System.out.print(tmpCheck.setSummary());
        System.out.print("\n\n");

        System.out.println("TRANSACTION # 4: DEPOSIT - $60 + 0");
        tmpCash = 60;
        tmpChecks = 0;
        tmpAmount = tmpCash + tmpChecks;
        myAccount.setBalance(myAccount.getBalance() + tmpAmount);
        tmpDeposit = new Deposit(
            myAccount.checking.getTransactionID(), 
            TransactionCode.DEPOSIT.ordinal(), 
            tmpCash, 
            tmpChecks
        );
        myAccount.checking.addTransaction(tmpDeposit);
        System.out.print(tmpDeposit.setSummary());
        System.out.print("\n\n  ");

        System.out.println("TRANSACTION # 5: CHECK - $125");
        tmpAmount = 125;
        myAccount.setBalance(myAccount.getBalance() - tmpAmount);
        tmpCheck = new Check(
            myAccount.checking.getTransactionID(), 
            TransactionCode.CHECK.ordinal(), 
            tmpAmount, 
            1
        );
        myAccount.checking.addTransaction(tmpCheck);
        System.out.print(tmpCheck.setSummary());
        System.out.print("\n\n");

        System.out.print(
            "TODO: Next week think about how to test list dialog boxes " +
            "that are in private methods of CheckOptionsPanel class..."
        );
        System.out.print("\n\n");

        System.out.print(myAccount.checking.toString());
        System.out.print("\n\n");
    }
}
