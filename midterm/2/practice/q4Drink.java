package practice;
public class q4Drink implements q4MenuItem {
    private String name;
    private double price;

    public q4Drink(String name) {
        this.name = name;
    }

    @Override
    public String getName() {return name;}

    @Override
    public double getPrice() {
        price = 0.0;
        if (getName().equals("Orange Soda")) {
            price = 1.25;
        } 
        if (getName().equals("Cappuccino")) {
            price = 3.50;
        }
        return price;
    }
}