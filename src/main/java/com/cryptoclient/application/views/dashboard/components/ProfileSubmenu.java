package com.cryptoclient.application.views.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class ProfileSubmenu extends JPanel {

    private JButton logoutButton;

    public ProfileSubmenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(40, 43, 45));

        // Boutons stylisés
        this.setLogoutButton(createStyledButton("Déconnexion"));

        // Champ de texte avec un encadrant


        // Ajouter les éléments au panneau

        this.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement
        this.add(this.getLogoutButton());

    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JTextField createTextFieldWithBorder() {
        // Création d'un JTextField avec un encadrant
        JTextField field = new JTextField(20); // Taille 20 caractères
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Bordure grise de 1 pixel
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton;
    }
}
