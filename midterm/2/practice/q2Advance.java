package practice;
public class q2Advance extends q2Ticket {
    private int daysInAdvance; 

    public q2Advance(int days) {
        daysInAdvance = days;
    }

    public int getDaysInAdvance() {
        return daysInAdvance;
    }
    
    @Override
    public double getPrice() {
        if (getDaysInAdvance() > 10) {
            return 30.0;
        } else {
            return 40.0;
        }
    }
}
