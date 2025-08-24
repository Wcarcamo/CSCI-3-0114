package practice;
public class q5Deal {
    private String myInfo; // Type of deal
    private int myQty; // Number of items
    private double myPrice; // Cost of the item

    // Constant tax rate for the deal
    protected static final double TAX_RATE = 0.07;

    // Constructor
    public q5Deal(String information, int quantity, double price) {
        myInfo = information;
        myQty = quantity;
        myPrice = price;
    }

    // Accessors
    public String getInformation() {
        return myInfo;
    }

    public int getQuantity() {
        return myQty;
    }

    public double getPrice() {
        return myPrice;
    }

    public double getTotal() {
        double daCost = myQty * myPrice;
        double daTax = daCost * TAX_RATE;
        return daCost + daTax;
    }
}