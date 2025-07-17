import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckingAccount {
    private ArrayList<Transaction> transactions;
    private double balance, totalServiceCharge;

    private boolean chargeBelow500 = false;
    private String transactionSummary = ""; 

    // Constructor
    public CheckingAccount(double initialBalance) {
        balance = initialBalance;
        totalServiceCharge = 0.0;
        transactions = new ArrayList<Transaction>();
    }

    // Getters
    public double getBalance() {return balance;}
    public double getServiceCharge() {return totalServiceCharge;}

    // Setters
    /* Create individual transactions and added them to transactions array */
    public void setTransaction(int code, double amount) {
        // Find the next available number to give as an ID for the new transaction
        // IDs will be 0-based, so start with 0 when empty then use size 
        int id = (transactions.isEmpty()) ? 0 : transactions.size();
        
        // Create a new transaction object and add it to the array
        Transaction input = new Transaction(id, code, amount);
        transactions.add(input);

        // Depending on transaction type, update our private members
        switch (code) {
            case 1: // Checks subtract amount from balance
                balance -= amount;
                break;
            case 2: // Deposits add amount to balance
                balance += amount;
                break;
            case 3: // Service Charges are a kept in a separate running tally
                totalServiceCharge += amount;
                break;
            default:
                break;
        }
    }

    // Generate a summary statement of users entered transaction
    public void setTransactionSummary(int lastTransaction, double lastAmount) {
        String summary;

        // Initialize transaction summary with information based on 
        // user input of the transaction
        summary = "Transaction: " + getTransactionText(lastTransaction, lastAmount) + "\n";
        summary += "Current Balance: " + formatDollar(getBalance()) + "\n";

        // Add optional service charge based on information within the transaction and 
        // from private members of their checking account
        if (lastTransaction == 1) {
            summary += "Service Charge: Check --- charge $0.15\n";
            setTransaction(3, 0.15);
        }
        if (lastTransaction == 2) {
            summary += "Service Charge: Deposit --- charge $0.10\n";
            setTransaction(3, 0.10);
        }
        if (lastTransaction == 1) {
            if (!chargeBelow500 && balance < 500) {
                chargeBelow500 = true;
                summary += "Service Charge: Below $500 --- charge $5.00\n";
                setTransaction(3, 5.0);
            }
            if (balance < 50) {
                summary += "Warning: Balance below $50\n";
            }
            if (balance < 0) {
                summary += "Service Charge: Below $0 --- charge $10.00\n";
                setTransaction(3, 10.0);
            }
        }

        summary += "Total Service Charge: " + formatDollar(getServiceCharge()) + "\n";

        if (lastTransaction == 0) {
            summary += "Final Balance: " + formatDollar(getBalance() - getServiceCharge());
        }
        transactionSummary = summary;
    }

    // Helper Functions
    // Used by setTransactionSummary() to convert information about transaction
    // into standarized messages
    private String getTransactionText(int code, double amount) {
        String transactionText;

        switch (code) {
            case 0:
                transactionText = "End";
                break;
            case 1:
                transactionText = "Check in Amount of " + formatDollar(amount);
                break;
            case 2:
                transactionText = "Deposit in Amount of " + formatDollar(amount);
                break;
            default:
                transactionText = "Invalid Transaction";
                break;
        }
        return transactionText;
    }
    
    // Used to display dollar amounts in correct format, negatives should be encased
    // in parenthesis instead of usual '-' prefix
    private String formatDollar(double amount) {
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        String text;

        if(amount < 0)
            text = "(" + dollar.format(amount * -1) + ")";
        else
            text = dollar.format(amount);

        return text;
    }

    // Print - Functions used for printing information from CheckingAccount class
    // they are used within dialog boxes when accessed through the main menu GUI
    public String printSummary() {
        return transactionSummary;
    }

    // Prints information about all transactions, including checks, deposits, 
    // and service charges. 
    public String printTransactions() {
        String listTransactions = "List All Transactions\n\n";
        
        // Format column header with proper standard spacing
        listTransactions += String.format("%-10s%-15s%-15s\n", "ID", "Type", "Amount");

        // Loop through transactions array and get information from each record
        for (Transaction eachTransaction : transactions) {
            listTransactions += eachTransaction.toString();
        }
        return listTransactions;
    }

    // Prints specified information on either Checks or Deposits, function flexes based 
    // on what user requests from the main menu
    public String print(int code) {
        String list = "List All ";
        
        // Flex which title to use based on main menu GUI option selection
        if (code == 1) {
            list += "Checks\n\n";
        } else { // code == 2
            list += "Deposits\n\n";
        }
        
        // Format column header with standard spacing
        list += String.format("%-10s%-15s\n", "ID", "Amount");

        // Loop through transactions array and get information from each record
        for (Transaction eachTrans : transactions) {
            // Filter based the transaction requested. User selects from main menu GUI which provides
            // transaction code
            if (eachTrans.getTransCode() == code) {
                list += String.format("%-10s%-15s\n", eachTrans.getTransId(), formatDollar(eachTrans.getTransAmount()));
            } 
        }
        return list;
    }
}