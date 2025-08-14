package src;
import java.io.Serializable;
public class Account implements Serializable {
    /* MEMBERS */
    protected String name; 
    protected double balance; 
    protected CheckingAccount checking;
    
    /* CONSTRUCTOR */
    public Account(String acctName, double initBalance) {
        name = acctName;
        balance = initBalance;
        checking = new CheckingAccount();
    }

    /* GETTERS */
    public String getName() {return name;}
    public double getBalance() {return balance;}

    /* SETTERS */
    public void setName(String name) {this.name = name;}
    public void setBalance(double balance) {this.balance = balance;}

    /* PRINT */
    public String toString() {
        return (
            "Name: " + getName() + "\n" +
            "Balance: " + Main.formatDollar(getBalance()) + "\n" +
            "Total Service Charge: " + Main.formatDollar(checking.getServiceCharge()) + "\n"
        );
    }
}