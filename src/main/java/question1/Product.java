package question1;

public class Product implements IProduct {

    private final String[] productNames;
    private final int[][] sales;

    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    private int[] GetProductTotals() {
        int[] totals = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                totals[i] += sales[i][j];
            }
        }
        return totals;
    }

    private int GetMostPopularIndex() {
        int[] totals = GetProductTotals();
        int best = 0;
        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > totals[best]) {
                best = i;
            }
        }
        return best;
    }

    public int GetMostPopularTotal() {
        return GetProductTotals()[GetMostPopularIndex()];
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int productTotal : GetProductTotals()) {
            total += productTotal;
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int[] row : sales) {
            count += row.length;
        }
        if (count == 0) {
            return 0;
        }
        return (double) GetTotalProductsSold() / count;
    }

    @Override
    public String GetMostPopularProduct() {
        return productNames[GetMostPopularIndex()];
    }
}
