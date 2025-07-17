public class Check extends Transaction {
    /* MEMBERS */
    private int checkNumber; // check number for each check transaction

    /* CONSTRUCTOR */
    public Check(int tId, int tCode, double tAmt, int checkNumber) {
        super(tId, tCode, tAmt);
        this.checkNumber = checkNumber;
    }

    /* GETTERS */
    public int getCheckNumber() {return checkNumber;}

    /* SETTERS */
    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }
}