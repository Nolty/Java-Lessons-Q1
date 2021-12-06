package ru.geekbrains;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame implements ActionListener {
    private static final String[] buttonText = {"", "DEL", "C", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
    private final JLabel resultText;
    private boolean calculated = false;

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Калькулятор");
        setSize(300, 400);
        setResizable(false);

        this.resultText = new JLabel("0");
        this.resultText.setFont(new Font("Calibri", Font.BOLD, 34));

        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(getScoreboardPanel(), BorderLayout.CENTER);
        container.add(getKeyboardPanel(), BorderLayout.SOUTH);
    }

    private JPanel getScoreboardPanel() {
        JPanel inputTextPanel = new JPanel();
        inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.LINE_AXIS));
        inputTextPanel.setBorder(new EmptyBorder(10,10,10,10));
        inputTextPanel.add(Box.createVerticalGlue());
        inputTextPanel.add(Box.createHorizontalGlue());
        inputTextPanel.add(resultText);
        inputTextPanel.add(Box.createVerticalGlue());
        return inputTextPanel;
    }

    private JPanel getKeyboardPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        for (String text : buttonText) {
            JButton button = new JButton(text);
            if (text.isEmpty()) {
                button.setEnabled(false);
                button.setVisible(false);
            }

            buttonPanel.add(button);
            button.addActionListener(this);
        }

        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            StringBuilder sb = new StringBuilder();
            String buttonValue = ((JButton) source).getText();

            if (!resultText.getText().equals("0"))
                sb.append(resultText.getText());

            if (buttonValue.equals("DEL")) {
                if (sb.length() > 1) {
                    sb.delete(sb.length() - 1, sb.length());
                    resultText.setText(sb.toString());
                } else {
                    resultText.setText("0");
                }
                return;
            }

            if (buttonValue.equals("+/-")) {
                return;
            }

            if (buttonValue.equals("C")) {
                resultText.setText("0");
                return;
            }

            if (buttonValue.equals("=")) {
                List<String> postfix = Calculator.parse(sb.toString());
                Double value = Calculator.calc(postfix);
                resultText.setText(value.toString());
                calculated = true;
                return;
            }

            if (calculated && !Calculator.isOperator(buttonValue)) {
                resultText.setText(buttonValue);
                calculated = false;
                return;
            }

            sb.append(buttonValue);
            resultText.setText(sb.toString());
            calculated = false;
        }
    }
}
