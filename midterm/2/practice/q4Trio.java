package practice;
public class q4Trio implements q4MenuItem {
    private q4Sandwich sandwich;
    private q4Salad salad;
    private q4Drink drink;

    public q4Trio(q4Sandwich sandwhich, q4Salad salad, q4Drink drink) {
        this.sandwich = sandwhich;
        this.salad = salad;
        this.drink = drink;
    }

    @Override
    public String getName() {
        return (
            sandwich.getName() + "/" +
            salad.getName() + "/" +
            drink.getName() + " Trio"
        );
    }

    @Override
    public double getPrice() {
        double price1 = sandwich.getPrice();
        double price2 = salad.getPrice();
        double price3 = drink.getPrice();
        double smallest = price1;

        if (smallest > price2) {
            smallest = price2;
        }
        if (smallest > price3) {
            smallest = price3;
        }
        return price1 + price2 + price3 - smallest;
    }
    
}
