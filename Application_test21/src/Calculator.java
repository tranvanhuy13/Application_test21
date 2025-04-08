import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame frame;
    private JTextField field1, field2;
    private JComboBox<String> operationBox;
    private JLabel resultLabel;
    private JTextArea historyArea;

    private Calculate calc = new Calculate();
    private History history = new History();

    public Calculator() {
        frame = new JFrame("Swing Calculator");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Number 1:"));
        field1 = new JTextField();
        inputPanel.add(field1);
        inputPanel.add(new JLabel("Number 2:"));
        field2 = new JTextField();
        inputPanel.add(field2);
        frame.add(inputPanel);

        // Operation selector
        JPanel opPanel = new JPanel();
        String[] ops = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(ops);
        opPanel.add(new JLabel("Operation:"));
        opPanel.add(operationBox);
        frame.add(opPanel);

        // Result
        JPanel resultPanel = new JPanel();
        JButton calcButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: ");
        resultPanel.add(calcButton);
        resultPanel.add(resultLabel);
        frame.add(resultPanel);

        // History button
        JButton historyButton = new JButton("Show History");
        frame.add(historyButton);

        // History area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        frame.add(scrollPane);

        // Exit button
        JButton exitButton = new JButton("Exit");
        frame.add(exitButton);

        // Action listener for Calculate
        calcButton.addActionListener(e -> {
            try {
                double num1 = Double.parseDouble(field1.getText());
                double num2 = Double.parseDouble(field2.getText());
                String op = (String) operationBox.getSelectedItem();
                double result = 0;

                switch (op) {
                    case "+": result = calc.add(num1, num2); break;
                    case "-": result = calc.subtract(num1, num2); break;
                    case "*": result = calc.multiply(num1, num2); break;
                    case "/": result = calc.divide(num1, num2); break;
                }

                String record = num1 + " " + op + " " + num2 + " = " + result;
                resultLabel.setText("Result: " + result);
                history.add(record);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
            } catch (ArithmeticException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Action listener for History
        historyButton.addActionListener(e -> {
            historyArea.setText(history.getAll());
        });

        // Exit
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}