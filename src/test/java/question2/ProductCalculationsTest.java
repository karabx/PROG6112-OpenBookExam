package question2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500);

        double result = calculations.GetVat();

        assertEquals(525.0, result, 0.001);
    }

    @Test
    public void GetCommission_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500);

        double result = calculations.GetCommission();

        assertEquals(175.0, result, 0.001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500);

        double result = calculations.GetTotal();

        assertEquals(4200.0, result, 0.001);
    }
}
