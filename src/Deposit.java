package src;
public class Deposit extends Transaction {
    /* MEMBERS */
    private double cash, check;
    
    /* 
        CONSTRUCTOR 
    */
    public Deposit(int tId, int tCode, double tCash, double tCheck) {
        super(tId, tCode, (tCash + tCheck));
        this.cash = tCash;
        this.check = tCheck;
    }
    
    /* 
        GETTERS 
    */
    public double getCash() {return cash;}
    public double getCheck() {return check;}

    /* 
        PRINT 
    */
    public String toString() {
        String print = "Transaction: Deposit in Amount of ";
        print += Main.formatDollar(this.getTransAmount()) + "\n";
        print += "Current Balance: ";
        print += Main.formatDollar(Main.account.getBalance()) + "\n";
        
        return print;
    }
}
