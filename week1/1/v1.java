import java.text.DecimalFormat;
import java.util.Scanner;

public class v1 {
   public static void main(String[] args) {
      double radius, circumference, area;
      DecimalFormat fmt = new DecimalFormat("0.##");

      radius = getRadius();
      circumference = calcCirc(radius);
      area = calcArea(radius);

      System.out.println(
         "The circumference of the circle is : " + fmt.format(circumference)
      );
      
      System.out.println(
         "The area of the circle is : " + fmt.format(area)
      );
   }

   public static double getRadius() {
      double userRadius;
      Scanner scan = new Scanner(System.in);

      System.out.print("Enter the radius of the circle: ");
      userRadius = scan.nextDouble();
      
      scan.close();
      return userRadius;
   }

   public static double calcCirc(double userRadius) {
      return 2 * Math.PI * userRadius;
   }

   public static double calcArea(double userRadius) {
      return Math.PI * Math.pow(userRadius, 2);
   }
}