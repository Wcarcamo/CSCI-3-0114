package midterm;
public class Main {
    public static void main(String[] args) {
        Triangle triange = new Triangle(3.0, 4.0, 5.0);
        System.out.println(triange.getPerimeter());
        System.out.println(triange.getArea());
        System.out.println(triange.isARightTriange());
    }
}