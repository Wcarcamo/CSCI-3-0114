public class Account {
    /* MEMBERS */
    protected String name; // The person who owns the account
    protected double balance;// do not define this in CheckingAccount class
    
    /* CONSTRUCTOR */
    public Account(String acctName, double initBalance) {
        balance = initBalance;
        name = acctName;
    }

    /* GETTERS */
    public String getName() {return name;}
    public double getBalance() {return balance;}

    /* SETTERS */
    public void setName(String name) {this.name = name;}
    public void setBalance(double balance) {this.balance = balance;}
}