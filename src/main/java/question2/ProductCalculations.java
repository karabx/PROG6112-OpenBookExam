package question2;

public class ProductCalculations implements IProductCalculations {

    private static final double VAT_PERCENT = 15;
    private static final double COMMISSION_PERCENT = 5;

    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * VAT_PERCENT / 100;
    }

    @Override
    public double GetCommission() {
        return price * COMMISSION_PERCENT / 100;
    }

    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
