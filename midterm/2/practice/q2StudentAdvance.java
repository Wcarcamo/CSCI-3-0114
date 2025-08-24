package practice;
public class q2StudentAdvance extends q2Advance {

    public q2StudentAdvance(int daysBefore) {
        super(daysBefore);
    }

    @Override
    public double getPrice() {return super.getPrice() / 2;}

    @Override
    public String toString() {  
        return (
            super.toString() + "\n" +"(student ID required)"
        ); 
    } 
    
}
