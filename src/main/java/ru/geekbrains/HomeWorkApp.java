package ru.geekbrains;

import javax.swing.*;

public class HomeWorkApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame mainWindow = new MainWindow();
            mainWindow.setVisible(true);
            mainWindow.setLocationRelativeTo(null);
        });
    }
}
