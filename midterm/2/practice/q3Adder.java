package practice;
public class q3Adder {
    public static void main(String[] args) {
        int total = 0;
        for (String s : args) {
            try {
                total += Integer.parseInt(s);                
            } catch (NumberFormatException e) {
                System.out.println("Ignoring bad input: " + s);
            }
        }
        System.out.println("The total is " + total);
    }
}