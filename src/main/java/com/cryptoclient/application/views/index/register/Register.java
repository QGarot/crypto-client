package com.cryptoclient.application.views.index.register;

import com.cryptoclient.application.views.View;
import com.cryptoclient.application.views.index.components.KeyboardHandler;
import com.cryptoclient.application.views.index.components.PasswordField;
import com.cryptoclient.application.views.index.components.SubmitButton;
import com.cryptoclient.application.views.index.components.UsernameField;
import com.cryptoclient.application.views.index.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class Register extends View {

    private int textSize;
    private int inputColumns;

    private String usernamePlaceholder;
    private String passwordPlaceholder;
    private String confirmPasswordPlaceholder;

    private JLabel title;
    private JLabel message;
    private UsernameField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private JLabel loginLabel;
    private JButton registerButton;

    public Register() {
        this.textSize = 23;
        this.inputColumns = 11;

        // Initialize placeholders
        this.usernamePlaceholder = "Pseudo";
        this.passwordPlaceholder = "Mot de passe";
        this.confirmPasswordPlaceholder = "Confirmation";


        // Initialize components
        this.title = new JLabel("Inscription");
        this.message = new JLabel();
        this.usernameField = new UsernameField(this.getUsernamePlaceholder(), this.inputColumns, this.textSize);
        this.passwordField = new PasswordField(this.getPasswordPlaceholder(), this.inputColumns, this.textSize);
        this.confirmPasswordField = new PasswordField(this.getConfirmPasswordPlaceholder(), this.inputColumns, this.textSize);
        this.loginLabel = new JLabel("Retourner sur la page de connexion");
        this.registerButton = new SubmitButton("S'inscrire", this.textSize);

        // Apply styles
        this.usernameField.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));
        this.passwordField.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));
        this.confirmPasswordField.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));
        this.registerButton.setBorder(new RoundedBorder(20, new Color(0, 122, 255)));

        // Configure keyboard navigation
        configureKeyboardNavigation();
    }

    public String getUsernamePlaceholder() {
        return this.usernamePlaceholder;
    }

    public String getPasswordPlaceholder() {
        return this.passwordPlaceholder;
    }

    public String getConfirmPasswordPlaceholder() {
        return this.confirmPasswordPlaceholder;
    }

    private void configureKeyboardNavigation() {
        KeyboardHandler.configureNavigation(usernameField, passwordField, confirmPasswordField, registerButton);
    }

    public JLabel getMessage() {
        return this.message;
    }

    public void setMessage(String message, boolean error) {
        if (error) {
            this.getMessage().setForeground(new Color(239, 40, 40));
        } else {
            this.getMessage().setForeground(new Color(34, 200, 36));
        }
        this.getMessage().setText(message);
    }

    public JLabel getLoginLabel() {
        return this.loginLabel;
    }

    public JButton getRegisterButton() {
        return this.registerButton;
    }

    public UsernameField getUsernameField() {
        return this.usernameField;
    }

    public PasswordField getPasswordField() {
        return this.passwordField;
    }

    public PasswordField getConfirmPasswordField() {
        return this.confirmPasswordField;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(0, 0, new Color(45, 50, 65), 0, height, new Color(30, 35, 50));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        } else {
            super.paintComponent(g);
        }
    }

    @Override
    public void loadComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title configuration
        this.title.setFont(new Font("Arial", Font.BOLD, 40));
        this.title.setForeground(new Color(255, 255, 255));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        this.add(this.title, gbc);

        // Error configuration
        this.message.setFont(new Font("Arial", Font.PLAIN, 26));
        //this.error.setForeground(new Color(239, 40, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        this.add(this.message, gbc);

        // Username field
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        this.add(this.usernameField, gbc);

        // Password field
        gbc.gridy = 3;
        this.add(this.passwordField, gbc);

        // Confirm password field
        gbc.gridy = 4;
        this.add(this.confirmPasswordField, gbc);

        // Login label
        this.loginLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        this.loginLabel.setForeground(new Color(0, 172, 237));
        this.loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(this.loginLabel, gbc);

        // Register button
        gbc.gridy = 6;
        this.add(this.registerButton, gbc);
    }
}
