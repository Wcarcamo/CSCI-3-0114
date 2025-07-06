import java.text.NumberFormat;

public class CheckingAccount {
    private double balance;
    private double totalServiceCharge;
    private int lastTransaction = 0;
    private double lastTransactionAmount = 0;
    private boolean chargeBelow500 = false;
    private String transactionSummary; 

    public CheckingAccount(double initialBalance) {
        balance = initialBalance;
        totalServiceCharge = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int tCode, double transAmt) {
        if (tCode == 1)
            balance -= transAmt;
        else // if(tCode == 2)
            balance += transAmt;
    }

    public double getServiceCharge() {
        return totalServiceCharge;
    }

    public void setServiceCharge(double currentServiceCharge) {
        totalServiceCharge += currentServiceCharge;
    }

    public void setLastTransaction(int code) {
        lastTransaction = code;
        lastTransactionAmount = 0;
    }

    public void processTransaction(int code, double amount) {
        lastTransaction = code;
        lastTransactionAmount = amount;
        this.setBalance(code, amount);
    }

    public void setChargeBelow500() {
        chargeBelow500 = true;
    }

    public void setTransactionSummary() {
        String summary;

        summary = "Transaction: " + getTransactionText(lastTransaction) + "\n";
        summary += "Current Balance: " + formatDollar(getBalance()) + "\n";

        if (lastTransaction == 1) {
            summary += "Service Charge: Check --- charge $0.15\n";
            setServiceCharge(0.15);
        }
        if (lastTransaction == 2) {
            summary += "Service Charge: Deposit --- charge $0.10\n";
            setServiceCharge(0.10);
        }
        if (!chargeBelow500 && balance < 500) {
            chargeBelow500 = true;
            summary += "Service Charge: Below $500 --- charge $5.00\n";
            setServiceCharge(5.0);
        }
        if (lastTransaction != 0 && balance < 50) {
            summary += "Warning: Balance below $50\n";
        }
        if (lastTransaction != 0 && balance < 0) {
            summary += "Service Charge: Below $0 --- charge $10.00\n";
            setServiceCharge(10.0);
        }

        summary += "Total Service Charge: " + formatDollar(totalServiceCharge) + "\n";

        if (lastTransaction == 0) {
            summary += "Final Balance: " + formatDollar(balance - totalServiceCharge) + "\n";
        }

        transactionSummary = summary;
    }

    public String toString() {
        return transactionSummary;
    }

    private String getTransactionText(int code) {
        String transactionText;

        switch (code) {
            case 0:
                transactionText = "End";
                break;
            case 1:
                transactionText = "Check in Amount of " + formatDollar(lastTransactionAmount);
                break;
            case 2:
                transactionText = "Deposit in Amount of " + formatDollar(lastTransactionAmount);
                break;
            default:
                transactionText = "Invalid Transaction";
                break;
        }
        return transactionText;
    }

    private String formatDollar(double amount) {
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        String text;

        if(amount < 0)
            text = "(" + dollar.format(amount * -1) + ")";
        else
            text = dollar.format(amount);

        return text;
    }
}