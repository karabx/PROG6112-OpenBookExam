package question2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ProductApp extends JFrame {

    private static final String DATA_FILE = "data.txt";
    private static final String[] PRODUCTS = {
        "Cozy Nights Monarch",
        "Restonic Barbados",
        "Sealy Phuket"
    };

    private final JTextField txtCustomerName = new JTextField(20);
    private final JComboBox<String> cmbProduct = new JComboBox<>(PRODUCTS);
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product app");
        BuildMenu();
        BuildForm();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void BuildMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> ProcessSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> ClearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }

    private void BuildForm() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Customer Name:"), c);
        c.gridx = 1;
        add(txtCustomerName, c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Select Product:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(cmbProduct, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        add(new JLabel("Enter Price:"), c);
        c.gridx = 1;
        add(txtPrice, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(btnProcess, c);

        btnProcess.addActionListener(e -> ProcessSale());
    }

    private void ProcessSale() {
        String customerName = txtCustomerName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();

        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (price <= 0) {
            JOptionPane.showMessageDialog(this, "The price must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductCalculations calculations = new ProductCalculations(price);
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        String message = "Customer Name: " + customerName + "\n"
                + "Product: " + product + "\n"
                + "VAT: R" + vat + "\n"
                + "COMMISSION: R" + commission + "\n"
                + "TOTAL: R" + total;

        SaveToFile(customerName, product, vat, commission, total);
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void SaveToFile(String customerName, String product, double vat, double commission, double total) {
        DecimalFormat money = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.US));
        File file = new File(DATA_FILE);
        boolean newFile = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            if (newFile) {
                writer.println("DATA LOG");
                writer.println("***********************");
            }
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + product);
            writer.println("VAT: R" + money.format(vat));
            writer.println("COMMISSION: R" + money.format(commission));
            writer.println("TOTAL: R" + money.format(total));
            writer.println("***********************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The data could not be saved to " + DATA_FILE + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ClearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
