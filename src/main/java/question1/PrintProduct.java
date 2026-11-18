package question1;

import java.text.DecimalFormat;

public class PrintProduct {

    private final Product product;

    public PrintProduct(Product product) {
        this.product = product;
    }

    public void PrintHeading() {
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("**********************************************");
    }

    public void PrintTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageSales() {
        DecimalFormat format = new DecimalFormat("0.##");
        System.out.println("AVERAGE PRODUCT SALE: " + format.format(product.GetAverageProductSold()));
    }

    public void PrintMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct()
                + " (" + product.GetMostPopularTotal() + ")");
    }
}
