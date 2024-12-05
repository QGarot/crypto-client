package com.cryptoclient.application.views.loading;

import com.cryptoclient.application.views.View;

import javax.swing.*;
import java.awt.*;

public class Loading extends View {

    private JLabel loadingLabel;
    private JButton backButton;
    private boolean componentsLoaded = false; // Indicateur pour vérifier si les composants ont été ajoutés

    public Loading() {
        // Utilisation d'un BoxLayout pour disposer les composants verticalement
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Création du label d'erreur
        this.setLoadingLabel(new JLabel("Erreur sur le mot de passe ou l'identifiant", JLabel.CENTER));
        this.getLoadingLabel().setFont(new Font("Arial", Font.PLAIN, 45));
        this.getLoadingLabel().setForeground(new Color(133, 122, 129));
        this.getLoadingLabel().setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement

        // Création du bouton de retour
        this.setBackButton(new JButton("Retour à la connexion"));
        this.getBackButton().setFont(new Font("Arial", Font.PLAIN, 16));
        this.getBackButton().setBackground(new Color(220, 53, 69)); // Couleur rouge pour un bouton de retour
        this.getBackButton().setForeground(Color.WHITE);
        this.getBackButton().setFocusPainted(false);
        this.getBackButton().setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement

    }

    public JLabel getLoadingLabel() {
        return loadingLabel;
    }

    public void setLoadingLabel(JLabel loadingLabel) {
        this.loadingLabel = loadingLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    @Override
    public void loadComponents() {
        if (componentsLoaded) {
            return; // Évite d'ajouter les composants à nouveau
        }

        this.setBackground(new Color(39, 33, 37));

        // Ajout d'un espace entre les composants
        this.add(Box.createVerticalStrut(200)); // Espace supérieur
        this.add(this.getLoadingLabel());
        this.add(Box.createVerticalStrut(20)); // Espace entre le label et le bouton
        this.add(this.getBackButton());

        componentsLoaded = true; // Marque les composants comme chargés
    }
}
