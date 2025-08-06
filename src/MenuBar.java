package src;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.*;

public class MenuBar extends JMenuBar{
    private JMenu menuFile, menuAccounts, menuTransactions;
    private JMenuItem openFile, saveFile, addNewAccount, listTransactions, listChecks;
    private JMenuItem listDeposits, listServiceCharges, findAccount, listAccounts;
    private JMenuItem enterTransactions;
    
    public MenuBar() {
        /* Create 3 Menus */
        menuFile = new JMenu("File");
        menuAccounts = new JMenu("Accounts");
        menuTransactions = new JMenu("Transactions");

        /* Create MenuItems for all Menus */
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        addNewAccount = new JMenuItem("Add New Account");
        listTransactions = new JMenuItem("List All Transactions");
        listChecks = new JMenuItem("List All Checks");
        listDeposits = new JMenuItem("List All Deposits");
        listServiceCharges = new JMenuItem("List All Service Charges");
        findAccount = new JMenuItem("Find An Account");
        listAccounts = new JMenuItem("List All Accounts");

        enterTransactions = new JMenuItem("Enter Transactions");

        /* Add Action Listener For all MenuItems */
        menuListener listener = new menuListener();
        openFile.addActionListener(listener);
        saveFile.addActionListener(listener);
        addNewAccount.addActionListener(listener);
        listTransactions.addActionListener(listener);
        listChecks.addActionListener(listener);
        listDeposits.addActionListener(listener);
        listServiceCharges.addActionListener(listener);
        findAccount.addActionListener(listener);
        listAccounts.addActionListener(listener);
        enterTransactions.addActionListener(listener);

        /* Add Items to File*/
        menuFile.add(openFile);
        menuFile.add(saveFile);
        /* Add Items to Accounts */
        menuAccounts.add(addNewAccount);
        menuAccounts.add(listTransactions);
        menuAccounts.add(listChecks);
        menuAccounts.add(listDeposits);
        menuAccounts.add(listServiceCharges);
        menuAccounts.add(findAccount);
        menuAccounts.add(listAccounts);
        /* Add Items to Transactions */
        menuTransactions.add(enterTransactions);

        this.add(menuFile);
        this.add(menuAccounts);
        this.add(menuTransactions);
    }

    private class menuListener implements ActionListener {
        //--------------------------------------------------------------
        //  Calls the method to process the Menu Items
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            String source = event.getActionCommand();

            if (source.equals("Open File")) {
                Main.openFile();
            } else if (source.equals("Save File")) {
                Main.saveFile();
            } else if (source.equals("Add New Account")) {
                System.out.println("TODO:");
            } else if (source.equals("List All Transactions")) {
                listAll("Transactions");
            } else if (source.equals("List All Checks")) {
                listAll("Checks");
            } else if (source.equals("List All Deposits")) {
                listAll("Deposits");
            } else if (source.equals("List All Service Charges")) {
                // listAll("Service Charges");
                System.out.println("TODO:");
            } else if (source.equals("Find An Account")) {
                System.out.println("TODO:");
            } else if (source.equals("List All Accounts")) {
                System.out.println("TODO:");
            } else if (source.equals("Enter Transactions")) {
                enterTransaction();
            } else {
                System.out.println("INVALID ACTION COMMAND");
            } 
        }
    }

    /**************************************************************************
        HELPER FUNCTIONS: Actions To Perform on selection of radio options
    **************************************************************************/ 

    /* 
        Let users enter multiple transactions into their checking account in a 
        loop
    */
    private static void enterTransaction() {
        int again;
        // Hide main menu GUI
        Main.frame.setVisible(false);
        // Start the transaction loop
        do {
            // User must enter a valid transaction code
            String transaction = JOptionPane.showInputDialog(
                null, "Enter your transaction code: "
            );
            // Check users transaction code
            switch (transaction) {
                // NULL check needed if user cancels first input dialog box 
                case null: break;
                case "0": 
                    JOptionPane.showMessageDialog(
                        null, 
                        Main.myAccount.checking.toString(), 
                        null, 
                        JOptionPane.INFORMATION_MESSAGE
                    ); 
                    break;
                // Call helper functions for checks and deposits
                case "1": newCheck(); break;
                case "2": newDeposit(); break;
                // Provide a default message to user if transaction code entered
                // is invalid
                default:
                    JOptionPane.showMessageDialog(
                        null, 
                        "Invalid Code: Enter 0, 1, or 2.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    break;
            }
            // Prompt user if they need to enter another transaction, regardless
            //  if previous transaction was acceptable or if there was an issue
            again = JOptionPane.showConfirmDialog(null, "Enter Another Transaction?");
        } while (again == JOptionPane.YES_OPTION);
        
        // When user is done entering transactions display main menu GUI again
        Main.frame.setVisible(true);
    }

    // Handles logic for new check transactions
    private static void newCheck() {
        int number;
        double amount;

        // Get user check number and check if it's valid
        String checkNumber = JOptionPane.showInputDialog(
            null, 
            "Enter your check number: ", 
            null, 
            JOptionPane.QUESTION_MESSAGE
        );
        if (Main.isValid(checkNumber)) {
            number = Integer.parseInt(checkNumber);
        } else {
            return;
        }
            
        // Get user check amount and check if it's valid
        String checkAmount = JOptionPane.showInputDialog(
            null, 
            "Enter your check amount: ", 
            null, 
            JOptionPane.QUESTION_MESSAGE
        );
        if (Main.isValid(checkAmount)) {
            amount = Double.parseDouble(checkAmount);
        } else {
            return;
        }

        // If valid check numbers and amount, update account balance
        double newBalance = Main.myAccount.getBalance() - amount;
        Main.myAccount.setBalance(newBalance);

        // Creat a new check object and store it in checking transaction arraylist
        Check newCheck = new Check(
            Main.myAccount.checking.getTransactionID(), 
            TransactionCode.CHECK.ordinal(), 
            amount, 
            number
        );
        Main.myAccount.checking.addTransaction(newCheck);
        // Mark account as modified
        Main.myAccount.setModified(true);

        // Get a summary of transaction with all service charges applied and
        // display it to the user
        String summary = newCheck.setSummary();
        JOptionPane.showMessageDialog(
            null, 
            summary,
            null, 
            JOptionPane.INFORMATION_MESSAGE
        );        
    }

    // Handles logic for new deposit transactions
    private static void newDeposit() {
        double cashAmount, checksAmount;

        // Create a new panel to prompt user to enter 2 fields at once
        // in a dialog box
        JTextField cash = new JTextField(5);
        JTextField checks = new JTextField(5);

        JPanel myDeposits = new JPanel();
        myDeposits.setLayout(new BoxLayout(myDeposits, BoxLayout.Y_AXIS));

        myDeposits.add(new JLabel("Cash"));
        myDeposits.add(cash);
        myDeposits.add(new JLabel("Checks"));
        myDeposits.add(checks);

        int result = JOptionPane.showConfirmDialog(
            null, 
            myDeposits, 
            "Deposit Window", 
            JOptionPane.OK_CANCEL_OPTION
        );

        // Check if use clicked ok or canceled the deposit transaction
        if (result == JOptionPane.OK_OPTION) {
            // If user clicked ok, check if both cash and checks input fields
            // were left blank, if they were then stop transaction
            if (
                cash.getText().trim().isEmpty() && 
                checks.getText().trim().isEmpty()
            ) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Both Cash and Checks are empty.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            // Check if either cash or checks contained invalid input, if at 
            // least once is invalid stop transaction
            } else if (
                Main.isNotValid(cash.getText()) ||
                Main.isNotValid(checks.getText())
            ) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Either Cash or Checks was invalid.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            // If no issues detected with either cash or check input, continue
            // depositing transaction
            } else {
                try {
                    cashAmount = Double.parseDouble(cash.getText());
                } catch (NumberFormatException e) {
                    cashAmount = 0.0;
                }
                try {
                    checksAmount = Double.parseDouble(checks.getText());
                } catch (NumberFormatException e) {
                    checksAmount = 0.0;
                }
                
                // Update Balance with both deposits
                double newBalance = (
                    Main.myAccount.getBalance() + cashAmount + checksAmount
                );
                Main.myAccount.setBalance(newBalance);
                // Create new deposit object and add to transaction arraylist
                Deposit newDeposit = new Deposit(
                    Main.myAccount.checking.getTransactionID(), 
                    TransactionCode.DEPOSIT.ordinal(), 
                    cashAmount, 
                    checksAmount
                );
                Main.myAccount.checking.addTransaction(newDeposit);
                // Mark account as modified
                Main.myAccount.setModified(true);

                // Get a summary of transaction with all service charges 
                // applied and display it to the user
                String summary = newDeposit.setSummary();
                JOptionPane.showMessageDialog(
                    null, 
                    summary,
                    null, 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        // Handle when user cancels deposit input window
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Deposit Canceled.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Create 1 helper function that accepts type of transaction as a string
    // Then creates the specified list table
    private static void listAll(String type) {
        // Hide GUI menu
        Main.frame.setVisible(false);
        // Create default header before listing type of transactions
        String message =  "List All " + type + "\n";
        message += "Name: " + Main.myAccount.getName() + "\n\n";

        // Handle Listing All Transactions
        if (type.equals("Transactions")) {
            // Format column header with proper standard spacing
            message += String.format(
                "%-10s%-15s%-15s\n", "ID", "Type", "Amount"
            );
            // Loop through transactions array and get information from each record
            for (Transaction eachTransaction : Main.myAccount.checking.transactions) {
                message += String.format(
                    "%-10s%-15s%-15s\n", 
                    eachTransaction.getTransId(),
                    Main.convertText(eachTransaction.getTransCode()),
                    Main.formatDollar(eachTransaction.getTransAmount())
                );
            }
        // Handle Listing All Checks
        } else if (type.equals("Checks")) {
            // Format column header with standard spacing
            message += String.format(
                "%-10s%-10s%-15s\n", "ID", "Check", "Amount"
            );
        
            // Loop through transactions array and get information fromChecks only
            for (Transaction eachTrans : Main.myAccount.checking.transactions) {
                // Filter based the transaction class instance
                if (eachTrans instanceof Check) {
                    message += String.format(
                        "%-10s%-10s%-15s\n", 
                        eachTrans.getTransId(), 
                        ((Check)eachTrans).getCheckNumber(),
                        Main.formatDollar(eachTrans.getTransAmount())
                    );
                } 
            }
        // Handle Listing All Deposits
        } else if (type.equals("Deposits")) {
            // Format column header with standard spacing
            message += String.format(
                "%-10s%-10s%-10s%-15s\n", 
                "ID", "Cash", "Check", "Amount"
            );
        
            // Loop through transactions array and get information fromChecks only
            for (Transaction eachTrans : Main.myAccount.checking.transactions) {
                // Filter based the transaction class instance
                if (eachTrans instanceof Deposit) {
                    message += String.format(
                        "%-10s%-15s%-15s%-15s\n", 
                        eachTrans.getTransId(), 
                        Main.formatDollar(((Deposit)eachTrans).getCash()),
                        Main.formatDollar(((Deposit)eachTrans).getCheck()),
                        Main.formatDollar(((Deposit)eachTrans).getTransAmount())
                    );
                } 
            }
        }
        // Display list of specified transactions
        JOptionPane.showMessageDialog(
            null, 
            message, 
            type, 
            JOptionPane.INFORMATION_MESSAGE
        );
        Main.frame.setVisible(true);
    }
}
