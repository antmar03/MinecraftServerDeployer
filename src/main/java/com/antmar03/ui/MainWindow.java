package com.antmar03.ui;

import javax.swing.*;
import java.awt.*;

public abstract class MainWindow extends JFrame {
    private int width;
    private int height;

    protected abstract void initializeContainer(final Container container);

    public MainWindow(int width, int height) {
        this.setResizable(false);
        this.width = width;
        this.height = height;
    }

    protected JButton makeButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 20));

        return button;
    }

    protected JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        return label;
    }

    protected JPanel makeJPanel(LayoutManager manager) {
        JPanel panel = new JPanel();
        panel.setLayout(manager);
        panel.setBackground(Color.DARK_GRAY);
        return panel;
    }

    private void setWindowStyle() {
        this.setTitle("Minecraft Server Deployer");
    }

    private void createGUI() {
        this.setWindowStyle();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.width, this.height);

        this.initializeContainer(this.getContentPane());
    }

    public void open() {
        this.pack();
        this.createGUI();
        this.setVisible(true);
    }
}
