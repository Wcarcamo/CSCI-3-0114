package exam;

public class Transport extends TariffProduct {
    private double merchantCost, merchantMarkup;

    public Transport(double tariffRate, double cost, double markup) {
        super(tariffRate);
        merchantCost = cost;
        merchantMarkup = markup;
    }

    public double getMerchantCost() {return merchantCost;}
    public double getMerchantMarkup() {return merchantMarkup;}

    @Override
    public double getMsrp() {
        return getMerchantCost() + getMerchantMarkup();
    }

    public void setMerchantMarkup(double markup) {
        merchantMarkup = markup;
    }
}
