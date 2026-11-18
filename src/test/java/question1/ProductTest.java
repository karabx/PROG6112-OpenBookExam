package question1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private Product CreateProduct() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {
            {25, 18, 20},
            {15, 10, 12},
            {18, 15, 20}
        };
        return new Product(names, sales);
    }

    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        Product product = CreateProduct();

        int result = product.GetTotalProductsSold();

        assertEquals(153, result);
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product product = CreateProduct();

        double result = product.GetAverageProductSold();

        assertEquals(17.0, result, 0.001);
    }
}
