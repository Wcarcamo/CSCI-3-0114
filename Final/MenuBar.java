package Final;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class MenuBar extends JMenuBar {
    private JMenu menuMath, menuScience;
    private JMenuItem quadraticSolver, factorialSolver, areaCircle;
    private JMenuItem findElement, calcExitVelocity;
    
    public MenuBar() {
        /* Create 2 Menus */
        menuMath = new JMenu("Math");
        menuScience = new JMenu("Science");

        /* Create MenuItems for all Menus */
        quadraticSolver = new JMenuItem("Quadratic Solver");
        factorialSolver = new JMenuItem("Factorial Solver");
        areaCircle = new JMenuItem("Area of a Circle");

        findElement = new JMenuItem("Find Element");
        calcExitVelocity = new JMenuItem("Calc Exit Velocity");

        /* Add Action Listener For all MenuItems */
        menuListener listener = new menuListener();
        quadraticSolver.addActionListener(listener);
        factorialSolver.addActionListener(listener);
        areaCircle.addActionListener(listener);
        findElement.addActionListener(listener);
        calcExitVelocity.addActionListener(listener);

        /* Add Items to File*/
        menuMath.add(quadraticSolver);
        menuMath.add(factorialSolver);
        menuMath.add(areaCircle);
        /* Add Items to Accounts */
        menuScience.add(findElement);
        menuScience.add(calcExitVelocity);

        this.add(menuMath);
        this.add(menuScience);
    }

    private class menuListener implements ActionListener {
        //--------------------------------------------------------------
        //  Calls the method to process the Menu Items
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            String source = event.getActionCommand();

            if (source.equals("Quadratic Solver")) {
                runQuadraticSolver();
            } else if (source.equals("Factorial Solver")) {
                runFactorialSolver();
            } else if (source.equals("Area of a Circle")) {
                areaOfCircle();
            } else if (source.equals("Find Element")) {
                Main.ta.setText("Lookup chemical element selected");
            } else if (source.equals("Calc Exit Velocity")) {
                Main.ta.setText("Calculate exit velocity selected");
            } else {
                System.out.println("INVALID ACTION COMMAND");
            } 
        }
    }

    /**************************************************************************
        HELPER FUNCTIONS: Actions To Perform on selection of JMenu Items
    **************************************************************************/ 
    public void runQuadraticSolver() {
        double a, b, c;

        JTextField aText = new JTextField(1);
        JTextField bText = new JTextField(1);
        JTextField cText = new JTextField(1);

        JPanel quadraticPanel = new JPanel();
        quadraticPanel.setLayout(new BoxLayout(quadraticPanel, BoxLayout.Y_AXIS));

        quadraticPanel.add(new JLabel("a"));
        quadraticPanel.add(aText);
        quadraticPanel.add(new JLabel("b"));
        quadraticPanel.add(bText);
        quadraticPanel.add(new JLabel("C"));
        quadraticPanel.add(cText);

        int inputs = JOptionPane.showConfirmDialog(
            null, 
            quadraticPanel, 
            "Quadratic Solver", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (inputs == JOptionPane.OK_OPTION) {
            try {
                a = Double.parseDouble(aText.getText());
                b = Double.parseDouble(bText.getText());
                c = Double.parseDouble(cText.getText());

                if(a == 0) {
                    Main.ta.setText("a cannot be 0");
                } else {
                    double discriminant = b * b - 4 * a *c;
                    
                    if (discriminant >= 0) {
                        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                        Main.ta.setText("Root 1 = " + root1 + " | Root 2 = " + root2);
                    } else {
                        Main.ta.setText("TODO IMAGINARY FOR: a: " + a + ", b: " + b + ", c: " + c + ", discriminant:" + discriminant);
                    }
                }
            } catch (NumberFormatException e) {
                Main.ta.setText("One or more invalid inputs");
            }
        } else {
            Main.ta.setText("Quadratic Solver Canceled");
        }
    }

    public void runFactorialSolver() {
        int factorial;
        String input = JOptionPane.showInputDialog(
            null,
            "Enter an integer greater than or equal to 0",
            null,
            JOptionPane.QUESTION_MESSAGE
        );

        try {
            factorial = Integer.parseInt(input);
            if (factorial < 0) {
                Main.ta.setText("Invalid Input. Can not be less than 0");
            } else {
                Main.ta.setText("Factorial Solution is " + factorial(factorial));
            }
        } catch (NumberFormatException e) {
            Main.ta.setText("Invalid Input. Try Again");
        }
    }

    public int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public void areaOfCircle() {
        DecimalFormat fmt = new DecimalFormat("0.##");
        double area, rad;
        String radius = JOptionPane.showInputDialog(
            null,
            "Enter the radius of a circle",
            null,
            JOptionPane.QUESTION_MESSAGE
        );

        try {
            rad = Double.parseDouble(radius);
            if (rad <= 0) {
                Main.ta.setText("Radius can not be less than 0.");
            } else {
                area = Math.PI * rad * rad;
                Main.ta.setText("The area of the cirlce is " + fmt.format(area));
            }
        } catch (NumberFormatException e) {
            Main.ta.setText("Try Again. Please enter a number > 0.");
        }

    }
}
