import java.text.DecimalFormat;
import java.util.Scanner;

public class v2 
{ 
   public static void main (String[] args) 
   { 
      double radius;
      radius = getRadius();

      Circle myCircle = new Circle(radius);

      DecimalFormat fmt = new DecimalFormat("0.##");
      System.out.println(
         "The circumference of the circle is : " + fmt.format(myCircle.getCircumference())
      );
      
      System.out.println(
         "The area of the circle is : " + fmt.format(myCircle.getArea())
      );
   } 
   public static double getRadius() 
   { 
      double userRadius;
      Scanner scan = new Scanner(System.in);

      System.out.print("Enter the radius of the circle: ");
      userRadius = scan.nextDouble();

      return userRadius;
   } 
}

