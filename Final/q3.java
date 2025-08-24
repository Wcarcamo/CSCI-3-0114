package Final;

import java.text.DecimalFormat;

public class q3 {
    public static void main(String[] args) {
        DecimalFormat fmt = new DecimalFormat("#.00");
        
        SlickSquare ss = new SlickSquare(5);
        
        System.out.println("The square has side = "
                + fmt.format(ss.getSide())
                + " and diagonoal = " + fmt.format(ss.getDiagonal()));
    }
}