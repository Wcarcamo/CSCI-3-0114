package src;
import java.io.Serializable;

public class Transaction implements Serializable {
    private int transId;
    private int transCode;
    private double transAmt;
    private String transSummary;

    /* 
        CONSTRUCTOR 
    */
    public Transaction(int id, int code, double amount) {
        transId = id;
        transCode = code;
        transAmt = amount;
    }

    /* 
        GETTERS 
    */
    public int getTransId() {return transId;}
    public int getTransCode() {return transCode;}
    public double getTransAmount() {return transAmt;}
    public String getTransSummary() {return transSummary;}

    /* 
        SETTERS 
    */
    // Generate a summary statement of user entered transaction + service
    // charges
    public String setSummary() {
        String summary;

        // Initialize transaction summary with information based on 
        // user input of the transaction
        summary = Main.account.getName() + "'s Account\n";
        summary += toString();

        // Add optional service charge based on information within the 
        // transaction and from private members of their checking account
        // now this leverages use of instanceof instead of transaction codes
        if (this instanceof Deposit) {
            // Default service Charge for all deposits
            summary += "Service Charge: Deposit --- charge $0.10\n";
            Main.account.checking.setServiceCharge(3, 0.10);
        }
        if (this instanceof Check) {
            // Default service charge for all checks
            summary += "Service Charge: Check --- charge $0.15\n";
            Main.account.checking.setServiceCharge(3, 0.15);

            // Additional service charges & warnings based on state conditions
            // of other classes
            if (
                !Main.account.checking.getChargedBelow500() &&
                Main.account.getBalance() < 500
            ) {
                Main.account.checking.setChargedBelow500(true);
                summary += "Service Charge: Below $500 --- charge $5.00\n";
                Main.account.checking.setServiceCharge(3, 5.0);
            }
            if (Main.account.getBalance() < 50) {
                summary += "Warning: Balance below $50\n";
            }
            if (Main.account.getBalance() < 0) {
                summary += "Service Charge: Below $0 --- charge $10.00\n";
                Main.account.checking.setServiceCharge(3, 10.0);
            }
        }

        summary += "Total Service Charge: ";
        summary += Main.formatDollar(Main.account.checking.getServiceCharge());
        
        transSummary = summary;
        return summary;
    }

    /*
        PRINT
    */
    public String toString() {
        String print = "Transaction: Service Charge in Amount of ";
        print += Main.formatDollar(transAmt) + "\n";
        print += "Current Balance: ";
        print += Main.formatDollar(Main.account.getBalance()) + "\n";

        return print;
    }
}
