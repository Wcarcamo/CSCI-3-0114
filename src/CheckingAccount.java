package src;
import java.util.ArrayList;

public class CheckingAccount {
    protected ArrayList<Transaction> transactions;
    private double totalServiceCharge = 0.0;
    private int transactionID = 0;
    private boolean chargedBelow500 = false;

    /* 
        CONSTRUCTOR 
    */
    public CheckingAccount() {
        transactions = new ArrayList<Transaction>();
    }

    /*
        GETTERS
    */
    public double getServiceCharge() {return totalServiceCharge;}
    public boolean getChargedBelow500() {return chargedBelow500;}
    public int getTransactionID() {
        // Helps keep track of next available transaction IDs by incrementing 
        // every time it is called
        int availableID = transactionID;
        transactionID += 1;
        return availableID;
    }
    /*
        SETTERS
    */
    public void addTransaction(Transaction item) {transactions.add(item);} 
    public void setChargedBelow500(boolean status) {chargedBelow500 = status;}
    public void setServiceCharge(int code, double amount) {
        // Since service charge is not a class of its own yet for this class
        // I created a dedicated setter funtion to manage "service charge" 
        // transactions
        totalServiceCharge += amount;
        Transaction serviceCharge = new Transaction(getTransactionID(), code, amount);
        transactions.add(serviceCharge);
    }
    
    /* 
        PRINT 
    */
    public String toString() {
        double balance = Main.myAccount.getBalance();
        
        String print = "Transaction: End\n";
        print += "Current Balance: " + Main.formatDollar(balance) + "\n";
        print += "Total Service Charge: " + Main.formatDollar(totalServiceCharge) + "\n";
        print += "Final Balance: " + Main.formatDollar(balance - totalServiceCharge);

        return print;
    }
}