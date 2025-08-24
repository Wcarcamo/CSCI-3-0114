package Final;

public class CoolCircle extends Circle {
    public CoolCircle(double r) {
        super(r);
    }
    
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}