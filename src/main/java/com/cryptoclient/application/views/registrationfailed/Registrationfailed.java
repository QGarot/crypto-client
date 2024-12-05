package com.cryptoclient.application.views.registrationfailed;

import com.cryptoclient.application.views.View;

import javax.swing.*;
import java.awt.*;

public class Registrationfailed extends View {

    private JLabel registrationfailedlabel;
    private JButton backRegister;
    private boolean componentsLoaded = false; // Indicateur pour vérifier si les composants ont été ajoutés

    public Registrationfailed() {
        // Utilisation d'un BoxLayout pour disposer les composants verticalement
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Création du label d'erreur
        this.setRegistrationfailedLabel(new JLabel("Erreur mot de passe differents", JLabel.CENTER));
        this.getRegistrationfailedLabel().setFont(new Font("Arial", Font.PLAIN, 45));
        this.getRegistrationfailedLabel().setForeground(new Color(133, 122, 129));
        this.getRegistrationfailedLabel().setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement

        // Création du bouton de retour
        this.setBackRegister(new JButton("Retour à la l'inscription "));
        this.getBackRegister().setFont(new Font("Arial", Font.PLAIN, 16));
        this.getBackRegister().setBackground(new Color(220, 53, 69)); // Couleur rouge pour un bouton de retour
        this.getBackRegister().setForeground(Color.WHITE);
        this.getBackRegister().setFocusPainted(false);
        this.getBackRegister().setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement

    }

    public JLabel getRegistrationfailedLabel() {
        return registrationfailedlabel;
    }

    public void setRegistrationfailedLabel(JLabel registrationfailedLabel) {
        this.registrationfailedlabel = registrationfailedLabel;
    }

    public JButton getBackRegister() {
        return backRegister;
    }

    public void setBackRegister(JButton backRegister) {
        this.backRegister = backRegister;
    }

    @Override
    public void loadComponents() {
        if (componentsLoaded) {
            return; // Évite d'ajouter les composants à nouveau
        }

        this.setBackground(new Color(39, 33, 37));

        // Ajout d'un espace entre les composants
        this.add(Box.createVerticalStrut(200)); // Espace supérieur
        this.add(this.getRegistrationfailedLabel());
        this.add(Box.createVerticalStrut(20)); // Espace entre le label et le bouton
        this.add(this.getBackRegister());

        componentsLoaded = true; // Marque les composants comme chargés
    }
}
