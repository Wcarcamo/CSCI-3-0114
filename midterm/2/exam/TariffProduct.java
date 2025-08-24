package exam;
public abstract class TariffProduct implements Product {
    // Tariff rate for the purchased product
    private double tariffRate;

    // Method to get the manufacturer’s suggest retail price
    // of the product
    public abstract double getMsrp();

    // Constructor
    public TariffProduct(double rate) {
        tariffRate = rate;
    }

    public double getTariffRate() {return tariffRate;}

    // Returns the price of the product including the tariff
    public double priceToBuy() {
        double tariff = getMsrp() * getTariffRate();
        return getMsrp() + tariff;
    }
}