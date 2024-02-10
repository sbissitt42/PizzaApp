import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BigYPizza {
    private JFrame frame;
    private double baseCost = 0.0;
    private int multiplier = 1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BigYPizza pizzaApp = new BigYPizza();
            pizzaApp.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("BigY Pizza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set a default size

        // Load background image
        setBackgroundImage("Pizza1.jpeg");

        JButton smallButton = createButton("Small ($5)", 5.0);
        JButton mediumButton = createButton("Medium ($10)", 10.0);
        JButton largeButton = createButton("Large ($15)", 15.0);
        JButton superButton = createButton("Super ($20)", 20.0);

        JButton pepperoniButton = createButton("Pepperoni (+$0.50)", 0.5);
        JButton mushroomsButton = createButton("Mushrooms (+$0.50)", 0.5);
        JButton onionsButton = createButton("Onions (+$0.50)", 0.5);

        JTextField multiplierField = new JTextField("1", 5);

        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.addActionListener(e -> {
            try {
                multiplier = Integer.parseInt(multiplierField.getText());
                double totalCost = baseCost * multiplier;
                JOptionPane.showMessageDialog(frame, "Total Cost for " + multiplier + " pizzas: $" + totalCost);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid multiplier. Please enter a valid number.");
            }
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(smallButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(mediumButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(largeButton, gbc);
        gbc.gridx = 3;
        buttonPanel.add(superButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        buttonPanel.add(pepperoniButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(mushroomsButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(onionsButton, gbc);

        JPanel multiplierPanel = new JPanel();
        multiplierPanel.add(new JLabel("Pizza Multiplier:"));
        multiplierPanel.add(multiplierField);
        multiplierPanel.add(calculateButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(multiplierPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JButton createButton(String label, double cost) {
        JButton button = new JButton(label);
        button.addActionListener(e -> baseCost += cost);
        return button;
    }

    private void setBackgroundImage(String imagePath) {
        try {
            URL imageURL = getClass().getResource(imagePath);
            if (imageURL != null) {
                Image img = new ImageIcon(imageURL).getImage();
                frame.setContentPane(new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                    }
                });
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
