import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Main extends Application {
    // global variables
    // define a CheckingAccount object to keep track of the
    // account information
    CheckingAccount myCheckingAccount;

    public void start(Stage primaryStage) throws Exception
    {
        // Initialize Local Variables/Objects
        Main main = new Main();
        int transaction;
        double initBalance, transactionAmount;

        // Get initial balance from the user
        TextInputDialog initialBalance = new TextInputDialog();
        initialBalance.setHeaderText(null);
        initialBalance.setTitle(null);
        initialBalance.setContentText("Enter your initial balance: ");
        
        Optional<String> balanceString = initialBalance.showAndWait();

        if (balanceString.isPresent()) {
            initBalance = Double.parseDouble(balanceString.get());
            main.myCheckingAccount = new CheckingAccount(initBalance);
            
            // perform in a loop until the transaction code = 0
            // get the transaction code from the user
            // and process it with appropriate helper method
            do {
                transaction = getTransCode();
                
                switch (transaction) {
                    case 0:
                        main.myCheckingAccount.setLastTransaction(transaction);
                        break;
                    case 1:
                    case 2:
                        transactionAmount = getTransAmt();
                        if (transactionAmount > 0)
                            main.myCheckingAccount.processTransaction(transaction, transactionAmount);
                        else {
                            warnUser(
                                "Invalid Amount:\n" + 
                                "- Transaction Canceled Or\n" +
                                "- Amount Less Than or Equal to 0"
                            );
                            transaction = -1; 
                        }
                        break;
                    default:
                        warnUser("Invalid transaction code. Valid Options: [0, 1, 2]");
                        break;
                }
                
                // Show Transaction Summary for Valid Transactions
                if (transaction == 0 || transaction == 1 || transaction == 2) {
                    main.myCheckingAccount.setTransactionSummary();
                    
                    Alert summary = new Alert(AlertType.INFORMATION);
                    summary.setHeaderText(null);
                    summary.setContentText(main.myCheckingAccount.toString());
                    summary.showAndWait();    
                }
            }
            while (transaction != 0);
        }
    }

    public int getTransCode() {
        TextInputDialog userTransaction = new TextInputDialog();
        userTransaction.setHeaderText(null);
        userTransaction.setTitle(null);
        userTransaction.setContentText("Enter your transaction code: ");
        
        Optional<String> transactionString = userTransaction.showAndWait();

        if (transactionString.isPresent())
            return Integer.parseInt(transactionString.get());
        else
            return -1; // Random Error Value
    }

    public double getTransAmt() {
        TextInputDialog userAmount = new TextInputDialog();
        userAmount.setHeaderText(null);
        userAmount.setTitle(null);
        userAmount.setContentText("Enter your transaction amount: ");
        
        Optional<String> amountString = userAmount.showAndWait();

        if (amountString.isPresent())
            return Double.parseDouble(amountString.get());
        else
            return 0;
    }

    public void warnUser(String message) {
        Alert warning = new Alert(AlertType.WARNING);
        warning.setHeaderText(null);
        warning.setContentText(message);
        warning.showAndWait();
    }
}