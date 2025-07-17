import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EOptionPanel extends JPanel {
    private JLabel prompt;
    private JRadioButton one, two, three, four;

   //-----------------------------------------------------------------
    //  Sets up a panel with a label and a set of radio buttons
    //  that present options to the user.
    //-----------------------------------------------------------------
    public EOptionPanel() {
        prompt = new JLabel("Choose action:");
        prompt.setFont(new Font("Helvetica", Font.BOLD, 28));

        one = new JRadioButton("Enter Transaction");
        one.setFont(new Font("Helvetica", Font.BOLD, 12));
        one.setBackground(Color.green);

        two = new JRadioButton("List All Transactions");
        two.setFont(new Font("Helvetica", Font.BOLD, 12));
        two.setBackground(Color.green);

        three = new JRadioButton("List All Checks");
        three.setFont(new Font("Helvetica", Font.BOLD, 12));
        three.setBackground(Color.green);

        four = new JRadioButton("List All Deposits");
        four.setFont(new Font("Helvetica", Font.BOLD, 12));
        four.setBackground(Color.green);

        ButtonGroup group = new ButtonGroup();
        group.add(one);
        group.add(two);
        group.add(three);
        group.add(four);

        EOptionListener listener = new EOptionListener();
        one.addActionListener(listener);
        two.addActionListener(listener);
        three.addActionListener(listener);
        four.addActionListener(listener);

      // add the components to the JPanel
        add(prompt);
        add(one);
        add(two);
        add(three);
        add(four);
        setBackground(Color.green);
        setPreferredSize(new Dimension(300, 300));
    }

   //*****************************************************************
   //  Represents the listener for the radio buttons
   //*****************************************************************
    private class EOptionListener implements ActionListener {
      //--------------------------------------------------------------
      //  Calls the method to process the option for which radio
      //  button was pressed.
      //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();

            if (source == one) {
                Main.inputTransaction();
            } else if (source == two) {
                Main.displayAllTransactions();
            } else if (source == three) {
                Main.displayAllChecks();
            } else {
                Main.displayAllDeposits();
            }
        }
    }

}
