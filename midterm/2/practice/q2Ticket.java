package practice;
public abstract class q2Ticket {
    private static int serialNumber = 0; // Unique ticket ID number

    public q2Ticket() {
        serialNumber = getNextSerialNumber();
    } 
    
    // Returns the price for this ticket
    public abstract double getPrice(); 
    
    // Returns a string with information about the ticket
    public String toString() {  
        return ("Number = " + serialNumber + "\nPrice = " +  getPrice()); 
    } 
    
    // Returns a new, unique serial number
    private static int getNextSerialNumber() {
        return ++serialNumber;
    };
}