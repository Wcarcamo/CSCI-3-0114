package practice;
public class q4Salad implements q4MenuItem {
    private String name;
    private double price;

    public q4Salad(String name) {
        this.name = name;
    }

    @Override
    public String getName() {return name;}

    @Override
    public double getPrice() {
        price = 0.0;
        if (getName().equals("Spinach Salad")) {
            price = 1.25;
        } 
        if (getName().equals("Coleslaw")) {
            price = 1.25;
        }
        return price;
    }
}