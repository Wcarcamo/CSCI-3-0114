package Final;

public class SlickSquare extends Square {
    public SlickSquare(double initside) {
        super(initside);
    }
    
    public double getDiagonal () {
        return side * Math.sqrt(2);
    }
}
