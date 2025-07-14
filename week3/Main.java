//-----------------------------------------------------------------
//  Demonstrates the use of radio buttons with listener
//  and multiple dialog boxes for user interaction.
//  Demonstrates the use of the JOptionPane class.
//********************************************************************
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Main {
    public static JFrame frame;
    public static CheckingAccount myCheckingAccount;

    public static void main(String[] args) {;
        // Initialize Balance before presenting Main Menu Option Panel
        if (Main.initializeBalance()) {
            // If initial balance was successful set up main menu 
            frame = new JFrame("Checking Account:");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Display the 4 options in the Main Menu 
            EOptionPanel panel = new EOptionPanel();
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
        // If initialization fails, program will tell user that the session will close due 
        // to not setting up an initial balance
        } else {
            JOptionPane.showMessageDialog(null, "Balance not set, your session will close.");
        }
    }

    // Helper Function: Will prompt user for an initial balance before presenting the main menu
    public static boolean initializeBalance() {
        String balance = JOptionPane.showInputDialog(
            null, "Enter your initial balance:", "Initial Balance", JOptionPane.QUESTION_MESSAGE
        );

        // Validation Check of user input for initial balance, accepts a balance string 
        // and calls another helper function
        if (Main.isValidAmount(balance)) {
            // If user entered intial balance is acceptable, then create a checking account
            myCheckingAccount = new CheckingAccount(Double.parseDouble(balance));
            // Retuns true which later allows main to continue and present main menu GUI
            return true;
        // If user input of initial balance is invalid, then returns false to prevent showing 
        // main menu GUI and gives user feedback why
        } else {
            return false;
        }
    }

    // Helper Function: Given a String input from a JOptionPane input dialog box, checks if the input
    // is a valid dollar amount as a double data type, returns boolean on whether amount is valid
    public static boolean isValidAmount(String input) {
        // Verify if user did not cancel the dialog box or inputed an empty string
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No input provided.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Attempt to parse user input and cast to a double
        try {
            double amount = Double.parseDouble(input);
            // Also check if amount is negative, i.e. user should not be writing checks 
            // with negative amounts
            if (amount < 0) {
                JOptionPane.showMessageDialog(
                    null, "Amount cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
            return true;
        // If parse fails, let user know their input was invalid
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Helper Function: One of the four main menu GUI options. Lets users enter multiple transactions 
    // to their checking account in a loop
    public static void inputTransaction() {
        int again;
        // While entering transactions, hide main menu GUI
        frame.setVisible(false);

        // Start the transaction loop
        do {
            // User must enter a valid transaction code
            String codeString = JOptionPane.showInputDialog(null, "Enter your transaction code: ");

            // Check users transaction code
            switch (codeString) {
                // NULL check needed if user cancels first input dialog box 
                case null:
                    break;
                case "0":
                    // Set transaction summary for "end" transactions
                    Main.myCheckingAccount.setTransactionSummary(0, 0);
                    // Display a summary of current balance, total service charges, and final balance
                    JOptionPane.showMessageDialog(
                        null, Main.myCheckingAccount.printSummary(), "Transaction Summary", JOptionPane.INFORMATION_MESSAGE
                    );
                    break;
                case "1":
                case "2":
                    String amountString = JOptionPane.showInputDialog(null, "Enter your transaction amount: ");
                    // After user enters a transaction amount, perform validation check on their amount input
                    // using our previously used helper function
                    if (Main.isValidAmount(amountString)) {
                        // Parse input into correct data type for recording
                        int code = Integer.parseInt(codeString);
                        double amount = Double.parseDouble(amountString);
                        // Log the transaction
                        Main.myCheckingAccount.setTransaction(code, amount);
                        Main.myCheckingAccount.setTransactionSummary(code, amount);
                        // Display a summary to user of their transaction along with service charges
                        JOptionPane.showMessageDialog(
                            null, Main.myCheckingAccount.printSummary(), "Transaction Summary", JOptionPane.INFORMATION_MESSAGE
                        );
                    // Provide a catch if amount is invalid and had to be canceled
                    } else {
                        JOptionPane.showMessageDialog(null, "Transaction Canceled.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                // Provide a default message to user if transaction code entered is invalid
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Code: Enter 0, 1, or 2.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            
            // Prompt user if they need to enter another transaction, regardless if their previous transaction was
            // accepted or if there was an issue
            again = JOptionPane.showConfirmDialog(null, "Enter Another Transaction?");
        } while (again == JOptionPane.YES_OPTION);
        
        // When user is done entering transactions display main menu GUI again
        frame.setVisible(true);
    }

    // Helper Functions: For the rest of the 3 options in main menu GUI, display specific information from 
    // users checking account
    public static void displayAllTransactions() {
        frame.setVisible(false);
        JOptionPane.showMessageDialog(null, Main.myCheckingAccount.printTransactions(), "Transactions", JOptionPane.INFORMATION_MESSAGE);
        frame.setVisible(true);
    }
    public static void displayAllChecks() {
        frame.setVisible(false);
        JOptionPane.showMessageDialog(null, Main.myCheckingAccount.print(1), "Checks", JOptionPane.INFORMATION_MESSAGE);
        frame.setVisible(true);
    }
    public static void displayAllDeposits() {
        frame.setVisible(false);
        JOptionPane.showMessageDialog(null, Main.myCheckingAccount.print(2), "Deposits", JOptionPane.INFORMATION_MESSAGE);
        frame.setVisible(true);
    }
}
