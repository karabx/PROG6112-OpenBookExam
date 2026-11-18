package question1;

import java.util.Scanner;

public class Main {

    private static final int MONTHS = 3;

    public static void main(String[] args) {
        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        int[][] sales = new int[productNames.length][MONTHS];

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < productNames.length; i++) {
            for (int j = 0; j < MONTHS; j++) {
                sales[i][j] = ReadSales(input, j + 1, productNames[i]);
            }
            System.out.println();
        }

        Product product = new Product(productNames, sales);
        PrintProduct printer = new PrintProduct(product);

        printer.PrintHeading();
        printer.PrintTotalSales();
        printer.PrintAverageSales();
        printer.PrintMostPopularProduct();
    }

    private static int ReadSales(Scanner input, int month, String productName) {
        while (true) {
            System.out.print("Enter number of sales for month " + month + " for " + productName + " mattress: ");
            String line = input.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a whole number of 0 or more.");
        }
    }
}
