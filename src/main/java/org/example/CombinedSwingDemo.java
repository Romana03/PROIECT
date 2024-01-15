import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class CombinedSwingDemo {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JTextField nameField;
    private JComboBox<String> fruitCombo;
    private Color chosenColor;
    private JSlider priceSlider;
    private JSpinner installmentSpinner;

    private static class CarConfiguration {
        private String clientName;
        private String carModel;
        private Color carColor;
        private int carPrice;
        private int installmentNumber;

        public CarConfiguration() {
            // Constructor gol
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public Color getCarColor() {
            return carColor;
        }

        public void setCarColor(Color carColor) {
            this.carColor = carColor;
        }

        public int getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(int carPrice) {
            this.carPrice = carPrice;
        }

        public int getInstallmentNumber() {
            return installmentNumber;
        }

        public void setInstallmentNumber(int installmentNumber) {
            this.installmentNumber = installmentNumber;
        }

        @Override
        public String toString() {
            return "CarConfiguration{" +
                    "clientName='" + clientName + '\'' +
                    ", carModel='" + carModel + '\'' +
                    ", carColor=" + carColor +
                    ", carPrice=" + carPrice +
                    ", installmentNumber=" + installmentNumber +
                    '}';
        }
    }

    public CombinedSwingDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        CombinedSwingDemo combinedSwingDemo = new CombinedSwingDemo();
        combinedSwingDemo.showDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Formular configurare masina");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel = new JLabel("Configureaza-ti masina", JLabel.CENTER);
        Font headerFont = new Font("Arial", Font.BOLD, 30);
        headerLabel.setFont(headerFont);

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());

        mainFrame.add(headerLabel, BorderLayout.NORTH);
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.add(statusLabel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private void showDemo() {
        showLabelDemo();
        showButtonDemo();
        showColorChooserDemo();
        showComboboxDemo();
        showSliderDemo();
        showSpinnerDemo();
    }

    private void showLabelDemo() {
        JLabel nameLabel = new JLabel("Nume client:", JLabel.RIGHT);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        nameLabel.setFont(labelFont);

        nameField = new JTextField(20);

        GridBagConstraints gbcNameLabel = new GridBagConstraints();
        gbcNameLabel.gridx = 0;
        gbcNameLabel.gridy = 0;
        gbcNameLabel.insets = new Insets(10, 0, 10, 400);
        controlPanel.add(nameLabel, gbcNameLabel);

        GridBagConstraints gbcNameField = new GridBagConstraints();
        gbcNameField.gridx = 1;
        gbcNameField.gridy = 0;
        gbcNameField.insets = new Insets(10, -300, 10, 800);
        controlPanel.add(nameField, gbcNameField);

        JLabel label = new JLabel("SIMPLU, USOR, RAPID", JLabel.CENTER);
        Font labelTextFont = new Font("Arial", Font.PLAIN, 25);
        label.setFont(labelTextFont);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLUE);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.gridwidth = 2;
        gbcLabel.insets = new Insets(10, 10, 200, 10);
        controlPanel.add(label, gbcLabel);
    }

    private void showButtonDemo() {
        JButton okButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Datele au fost salvate cu succes.");
                saveToJson();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Datele nu au fost salvate.");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(250, 10, 10, 10);
        controlPanel.add(buttonPanel, gbc);
    }

    private void showColorChooserDemo() {
        JLabel chooseLabel = new JLabel("Alege culoarea masinii:", JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        chooseLabel.setFont(labelFont);

        JButton chooseButton = new JButton("Choose the color");

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenColor = JColorChooser.showDialog(mainFrame,
                        "Choose the color", Color.white);
                if (chosenColor != null) {
                    statusLabel.setText("Ai selectat culoarea " + chosenColor.toString());
                }
            }
        });

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        gbcLabel.insets = new Insets(-300, -330, 10, 10);
        controlPanel.add(chooseLabel, gbcLabel);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new Insets(-20, 15, 30, 10);
        controlPanel.add(chooseButton, gbcButton);
    }

    private void showComboboxDemo() {
        JLabel chooseCarLabel = new JLabel("Alege modelul masinii:", JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        chooseCarLabel.setFont(labelFont);

        final DefaultComboBoxModel<String> fruitsName = new DefaultComboBoxModel<>();

        fruitsName.addElement("Mercedes");
        fruitsName.addElement("BMW");
        fruitsName.addElement("Audi");
        fruitsName.addElement("Volvo");
        fruitsName.addElement("Fiat");
        fruitsName.addElement("Volskwagen");
        fruitsName.addElement("Skoda");
        fruitsName.addElement("Dacia");
        fruitsName.addElement("Citroen");
        fruitsName.addElement("Peugeot");
        fruitsName.addElement("Seat");
        fruitsName.addElement("Range Rover");

        fruitCombo = new JComboBox<>(fruitsName);
        fruitCombo.setSelectedIndex(0);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(150, -250, 30, 100);
        controlPanel.add(chooseCarLabel, gbcLabel);

        GridBagConstraints gbcCombo = new GridBagConstraints();
        gbcCombo.gridx = 0;
        gbcCombo.gridy = 0;
        gbcCombo.insets = new Insets(130, -10, 10, 10);
        controlPanel.add(fruitCombo, gbcCombo);

        fruitCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCar = fruitCombo.getSelectedItem().toString();
                statusLabel.setText("Ai ales masina: " + selectedCar);
            }
        });
    }

    private void showSliderDemo() {
        JLabel priceLabel = new JLabel("Alege pretul masinii(euro):", JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        priceLabel.setFont(labelFont);

        priceSlider = new JSlider(JSlider.HORIZONTAL, 5000, 75000, 5000);
        priceSlider.setMajorTickSpacing(1000);
        priceSlider.setMinorTickSpacing(5000);
        priceSlider.setPaintTicks(true);
        priceSlider.setPaintLabels(true);

        priceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Ai ales pretul de (euro): " + priceSlider.getValue());
            }
        });

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        gbcLabel.insets = new Insets(-180, -320, 10, 10);
        controlPanel.add(priceLabel, gbcLabel);

        GridBagConstraints gbcSlider = new GridBagConstraints();
        gbcSlider.gridx = 0;
        gbcSlider.gridy = 1;
        gbcSlider.anchor = GridBagConstraints.NORTH;
        gbcSlider.insets = new Insets(20, 60, 30, 10);
        controlPanel.add(priceSlider, gbcSlider);
    }

    private void showSpinnerDemo() {
        JLabel installmentLabel = new JLabel("Alege numarul de rate pentru achitarea masinii:", JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        installmentLabel.setFont(labelFont);

        SpinnerModel spinnerModel = new SpinnerNumberModel(6, 6, 48, 6);
        installmentSpinner = new JSpinner(spinnerModel);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        gbcLabel.insets = new Insets(-90, -180, 10, 10);
        controlPanel.add(installmentLabel, gbcLabel);

        GridBagConstraints gbcSpinner = new GridBagConstraints();
        gbcSpinner.gridx = 0;
        gbcSpinner.gridy = 3;
        gbcSpinner.anchor = GridBagConstraints.NORTH;
        gbcSpinner.insets = new Insets(-210, 15, 30, -180);
        controlPanel.add(installmentSpinner, gbcSpinner);
    }

    private void saveToJson() {
        CarConfiguration carConfig = new CarConfiguration();
        carConfig.setClientName(nameField.getText());
        carConfig.setCarModel(fruitCombo.getSelectedItem().toString());
        carConfig.setCarColor(chosenColor);
        carConfig.setCarPrice(priceSlider.getValue());
        carConfig.setInstallmentNumber((int) installmentSpinner.getValue());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(carConfig);

            try (FileWriter fileWriter = new FileWriter("car_config.json", true)) {
                fileWriter.append(json).append("\n");
                statusLabel.setText("Datele au fost salvate cu succes in fisierul car_config.json.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("A aparut o eroare la salvarea datelor.");
        }
    }
}