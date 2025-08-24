package practice;
public class q5Purchase extends q5Deal {
    private boolean isCash;
    public static final double DISCOUNT = 0.10;

    public q5Purchase(String information, int quantity, double price, boolean cash) {
        super(information, quantity, price);
        isCash = cash;
    }

    public boolean getIsCash() {return isCash;}

    @Override
    public double getTotal() {
        double cost = getPrice() * getQuantity();

        if (getIsCash()) {
            double discount = cost * DISCOUNT;
            cost -= discount;
        }

        double total = cost * TAX_RATE;
        return total;
    }
}
