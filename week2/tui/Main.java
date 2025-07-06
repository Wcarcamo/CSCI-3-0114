package tui;
import java.util.Scanner;

public class Main {
   // global variables
   // define a CheckingAccount object to keep track of the
   // account information
   CheckingAccount myCheckingAccount;
   
   public static void main(String[] args) {
      // defines local variables
      Main main = new Main();
      int transaction = -1;
      double initBalance, transactionAmount;
      Scanner scan = new Scanner(System.in);

      // get initial balance from the user
      System.out.print("Enter your initial balance: ");
      initBalance = scan.nextDouble();
      main.myCheckingAccount = new CheckingAccount(initBalance);
      
      // perform in a loop until the transaction code = 0
      // get the transaction code from the user
      // and process it with appropriate helper method
      do {
         transaction = getTransCode(scan);
         
         switch (transaction) {
            case 0:
               main.myCheckingAccount.setLastTransaction(transaction);
               break;
            case 1:
            case 2:
               transactionAmount = getTransAmt(scan);
               main.myCheckingAccount.processTransaction(transaction, transactionAmount);
               break;
            default:
               System.out.println("Invalid transaction code. Enter [0, 1, 2]");
               break;
         }

         main.myCheckingAccount.setTransactionSummary();
         System.out.print(main.myCheckingAccount.toString());
      }
      while (transaction != 0);
      
      // When loop ends show final balance to user.
      scan.close();
   }

   public static int getTransCode(Scanner scan) {
      System.out.print("Enter your transaction code: ");
      return scan.nextInt();
   }

   public static double getTransAmt(Scanner scan) {
      System.out.print("Enter your transaction ammount: ");
      return scan.nextDouble();
   }
}