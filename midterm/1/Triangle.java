package midterm;
public class Triangle {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        double area, s;
        
        s = getPerimeter() * 0.5;
        area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        
        return area;
    }

    public boolean isARightTriange() {
        if (Math.pow(c, 2) == (Math.pow(a, 2) + Math.pow(b, 2))) {
            return true;
        } else {
            return false;
        }
    }
}
