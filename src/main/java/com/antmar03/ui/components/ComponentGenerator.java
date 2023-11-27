package com.antmar03.ui.components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ComponentGenerator {
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font DEFAULT_BUTTON_FONT = new Font("Arial", Font.PLAIN, 15);
    private static final Color DEFAULT_BUTTON_BACKGROUND = Color.GRAY;
    private static final Color DEFUALT_BUTTON_BORDER = Color.BLACK;
    private static final Color DEFAULT_FONT_COLOR = Color.WHITE;
    private static final Insets DEFAULT_BUTTON_MARGIN = new Insets(10,10,10,10);
    private static final int DEFAULT_BUTTON_WIDTH = 100;
    private static final int DEFAULT_BUTTON_HEIGHT = 35;


    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(DEFAULT_BUTTON_FONT);
        button.setBackground(DEFAULT_BUTTON_BACKGROUND);
        button.setForeground(DEFAULT_FONT_COLOR);
        button.setMargin(DEFAULT_BUTTON_MARGIN);
        button.setBorder(BorderFactory.createLineBorder(DEFUALT_BUTTON_BORDER));
        button.setMinimumSize(new Dimension(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT));
        return button;
    }

    public static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(DEFAULT_FONT);
        label.setForeground(DEFAULT_FONT_COLOR);

        return label;
    }


}
