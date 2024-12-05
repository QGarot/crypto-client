package com.cryptoclient.listener.dashboard;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.dashboard.Dashboard;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.listener.ViewListener;
import com.cryptoclient.networking.Connection;
import com.cryptoclient.networking.packets.headers.OutgoingHeaders;
import org.json.JSONObject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DashboardListener extends ViewListener<Dashboard> {

    public DashboardListener(Application app, Connection connection, Dashboard view) {
        super(app, connection, view);
    }

    @Override
    public void listen() {
        listenCryptocurrencySelection();
        listenSearch();
        listenLogout();
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
                String query = getView().getMenu().getCryptoSubmenu().getSearchField().getText();
                getView().getMenu().getCryptoSubmenu().filterCryptocurrencies(query);
            }
        });
    }

    private void listenCryptocurrencySelection() {
        this.getView().getMenu().getCryptoSubmenu().getCryptocurrenciesItems().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCryptocurrency = getView().getMenu().getCryptoSubmenu().getCryptocurrenciesItems().getSelectedValue();

                // Prepare the request to the server
                JSONObject packet = new JSONObject();
                packet.put("header", OutgoingHeaders.DASHBOARD_SELECT_CRYPTO_REQUEST);
                packet.put("cryptoName", selectedCryptocurrency);

                this.getConnection().sendPacket(packet);
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
