package com.cryptoclient.application.views.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private CryptoSubmenu cryptoSubmenu;
    private ProfileSubmenu profileSubmenu;

    private JPanel customPeriodPanel;
    private JComboBox<String> periodSelector;
    private String periodSelected;

    private int width;

    public MenuPanel(int width) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, 0));

        // Dégradé pour l'arrière-plan
        this.setBackground(new Color(45, 50, 65));

        // Initialiser les sous-menus
        this.setCryptoSubmenu(new CryptoSubmenu(new DefaultListModel<>()));
        this.getCryptoSubmenu().setPreferredSize(new Dimension(width, 600));
        this.setProfileSubmenu(new ProfileSubmenu());

        this.initPeriodSelectionPanel();

        // Ajouter les sous-menus
        this.add(this.getCryptoSubmenu(), BorderLayout.NORTH);
        this.add(this.customPeriodPanel, BorderLayout.CENTER);
        this.add(this.getProfileSubmenu(), BorderLayout.SOUTH);

        // Default period value
        this.periodSelected = "ONE_YEAR";
    }

    private void initPeriodSelectionPanel() {
        // Créer le panneau de sélection de période
        this.customPeriodPanel = new JPanel();
        this.customPeriodPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrer le contenu
        //this.customPeriodPanel.setBackground(new Color(35, 40, 55)); // Couleur différente pour le contraste

        // Créer un JComboBox pour la sélection de période
        periodSelector = new JComboBox<>(new String[]{"1 jour", "1 mois", "1 an"});
        periodSelector.setPreferredSize(new Dimension(150, 30)); // Taille du sélecteur
        periodSelector.setFocusable(false); // Retirer le focus visuel

        // Ajouter un événement au sélecteur
        periodSelector.addActionListener(e -> {
            String selectedPeriod = (String) periodSelector.getSelectedItem();
            System.out.println("Période sélectionnée : " + selectedPeriod);
            if (selectedPeriod.equals("1 jour")) {
                this.periodSelected = "ONE_DAY";
            } else if (selectedPeriod.equals("1 mois")) {
                this.periodSelected = "ONE_MONTH";
            } else {
                this.periodSelected = "ONE_YEAR";
            }
        });

        // Ajouter le sélecteur au panneau
        this.customPeriodPanel.add(periodSelector);
    }

    public String getPeriodSelected() {
        return this.periodSelected;
    }

    public CryptoSubmenu getCryptoSubmenu() {
        return cryptoSubmenu;
    }

    public void setCryptoSubmenu(CryptoSubmenu cryptoSubmenu) {
        this.cryptoSubmenu = cryptoSubmenu;
    }

    public ProfileSubmenu getProfileSubmenu() {
        return profileSubmenu;
    }

    public void setProfileSubmenu(ProfileSubmenu profileSubmenu) {
        this.profileSubmenu = profileSubmenu;
    }
}
