package practice;
public class q4Sandwich implements q4MenuItem {
    private String name;
    private double price;

    public q4Sandwich(String name) {
        this.name = name;
    }

    @Override
    public String getName() {return name;}

    @Override
    public double getPrice() {
        price = 0.0;
        if (getName().equals("Cheeseburger")) {
            price = 2.75;
        } 
        if (getName().equals("Club Sandwhich")) {
            price = 2.75;
        }
        return price;
    }
}