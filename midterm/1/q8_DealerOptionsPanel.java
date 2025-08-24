import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DealerOptionsPanel extends JPanel
{
   private JLabel lbl;
   private JRadioButton rb1, rb2, rb3;
   private ButtonGroup bg;

   //------------------------------------------------------------------------
   // Sets up a panel with a label and a set of radio buttons
   // that present options to the user.
   //------------------------------------------------------------------------
   public DealerOptionsPanel()
   {
      lbl = new JLabel("Choose your option?");
      lbl.setFont(new Font("SansSerif", Font.BOLD, 24));

      rb1 = new JRadioButton("New Car Sales");
      rb1.setBackground(Color.white);
      rb2 = new JRadioButton("Used Car Sales");
      rb2.setBackground(Color.white);
      rb3 = new JRadioButton("Service Dept.");
      rb3.setBackground(Color.white);

      bg = new ButtonGroup();
      bg.add(rb1);
      bg.add(rb2);
      bg.add(rb3);

      DealerOptionListener listener = new DealerOptionListener();
      rb1.addActionListener(listener);
      rb2.addActionListener(listener);
      rb3.addActionListener(listener);

      add(lbl);
      add(rb1);
      add(rb2);
      add(rb3);
      setBackground(Color.white);
      setPreferredSize(new Dimension(250, 100));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   } // end panel constructor

   //*****************************************************************
   // the listener class for the radio buttons
   //*****************************************************************
   private class DealerOptionListener implements ActionListener
   {
      //------------------------------------------------------------------------
      // Calls the method to process the option for which radio
      // button was pressed.
      //------------------------------------------------------------------------
      @Override
      public void actionPerformed (ActionEvent event)
      {
         Object source = event.getSource();

         if (source == rb1) {
            newCarSales();
         } else if (source == rb2) {
            usedCarSales();
         } else if (source == rb3) {
            serviceDept();
         }
      } // end listener method

   } //end listener class

} //end panel class