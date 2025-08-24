import javax.swing.JOptionPane;

public class q6 {
    public static void main(String[] args) {
        String numStr, message;
        int value = 0, sum = 0, count = 0;

        do {
            numStr = JOptionPane.showInputDialog("Enter a number");
            value = Integer.parseInt(numStr);
            if (value >= 0) {
                sum += value;
                count += 1;
            }
        } while (value >= 0);

        message = (
            "The sum of the " + count + " numbers proccessed is " +
            sum
        );

        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Message", 
            JOptionPane.INFORMATION_MESSAGE
        );
    } // end main function

}// end Main class