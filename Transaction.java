public class Transaction {
    private int transId;
    private int transCode;
    private double transAmt;

    // Constructor
    public Transaction(int id, int code, double amount) {
        transId = id;
        transCode = code;
        transAmt = amount;
    }

    // Getters
    public int getTransId() {return transId;}
    public int getTransCode() {return transCode;}
    public double getTransAmount() {return transAmt;}

    // Print - Display all 3 private members in a standard column output
    public String toString () {
        return String.format(
            "%-10s%-15s$%-14.2f\n"
            , getTransId(), getCodeText(getTransCode()), getTransAmount() 
        )
        ;
    }

    // Helpers - Used to convert the transaction code into text for interpretation
    private String getCodeText(int tCode) {
        switch (tCode) {
            case 1: return "Check";
            case 2: return "Deposit";
            case 3: return "Srv. Chg.";
            default: return "Invalid Code";
        }
    }
}
