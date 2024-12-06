package com.cryptoclient.listener.dashboard;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.dashboard.Dashboard;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.listener.ViewListener;
import com.cryptoclient.networking.Connection;
import com.cryptoclient.networking.packets.headers.OutgoingHeaders;
import org.json.JSONObject;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardListener extends ViewListener<Dashboard> {

    public DashboardListener(Application app, Connection connection, Dashboard view) {
        super(app, connection, view);
    }

    @Override
    public void listen() {
        listenCryptocurrencySelection();
        listenLogout();
        listenSearch(); // Make sure to call listenSearch here
    }

    private void listenSearch() {
        this.getView().getMenu().getCryptoSubmenu().getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String query = getView().getMenu().getCryptoSubmenu().getSearchField().getText().trim();

                if (query.isEmpty() || query.equalsIgnoreCase("Rechercher")) {
                    // Si la barre de recherche est vide, restaurer la liste complète sans envoyer de requête
                    getView().getMenu().getCryptoSubmenu().restoreFullList();
                } else {
                    // Sinon, effectuer un filtrage local (sans action réseau)
                    getView().getMenu().getCryptoSubmenu().filterCryptocurrencies(query);
                }
            }
        });
    }

    private void listenCryptocurrencySelection() {
        this.getView().getMenu().getCryptoSubmenu().getCryptocurrenciesItems().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCryptocurrency = getView().getMenu().getCryptoSubmenu().getCryptocurrenciesItems().getSelectedValue();

                if (selectedCryptocurrency != null && !selectedCryptocurrency.isEmpty()) {
                    // Préparer la requête uniquement si une crypto a été sélectionnée
                    JSONObject packet = new JSONObject();
                    packet.put("header", OutgoingHeaders.DASHBOARD_SELECT_CRYPTO_REQUEST);
                    packet.put("cryptoName", selectedCryptocurrency);

                    // Envoyer la requête
                    this.getConnection().sendPacket(packet);
                }
            }
        });
    }

    private void listenLogout() {
        this.getView().getMenu().getProfileSubmenu().getLogoutButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getApp().getViewManager().displayView(Configuration.VIEW_LOGIN);
            }
        });
    }
}
