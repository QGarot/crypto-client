package com.cryptoclient.application.views.dashboard.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.stream.IntStream;

public class CryptoSubmenu extends JPanel {

    private DefaultListModel<String> cryptocurrenciesName;
    private JList<String> cryptocurrenciesItems;
    private JScrollPane listScroller;
    private JTextField searchField; // Search bar

    public CryptoSubmenu(DefaultListModel<String> cryptocurrenciesName) {
        this.setCryptocurrenciesName(cryptocurrenciesName);
        this.setLayout(new BorderLayout()); // Use BorderLayout for positioning
        this.setCryptocurrenciesItems(new JList<>());
        this.searchField = new JTextField();
        this.searchField.setPreferredSize(new Dimension(200, 25)); // Define search field size
        setupSearchField(); // Initialize the placeholder behavior
        setupSearchListener(); // Add listener for filtering
        this.loadItems();
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JScrollPane getListScroller() {
        return listScroller;
    }

    public void setListScroller(JScrollPane listScroller) {
        this.listScroller = listScroller;
    }

    public JList<String> getCryptocurrenciesItems() {
        return cryptocurrenciesItems;
    }

    public void setCryptocurrenciesItems(JList<String> cryptocurrenciesItems) {
        this.cryptocurrenciesItems = cryptocurrenciesItems;
    }

    public DefaultListModel<String> getCryptocurrenciesName() {
        return cryptocurrenciesName;
    }

    public void setCryptocurrenciesName(DefaultListModel<String> cryptocurrenciesName) {
        this.cryptocurrenciesName = cryptocurrenciesName;
    }

    private void setupSearchField() {
        // Placeholder text
        String placeholderText = "Rechercher";
        searchField.setText(placeholderText);
        searchField.setForeground(Color.GRAY);

        // Add focus listener
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholderText)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholderText);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setupSearchListener() {
        // Listen to changes in the search field
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }

            private void updateFilter() {
                String query = searchField.getText().trim();
                if (query.equals("Rechercher") || query.isEmpty()) {
                    // Restore the full list if the query is empty or equals the placeholder
                    restoreFullList();
                } else {
                    // Filter based on the query
                    filterCryptocurrencies(query);
                }
            }
        });
    }

    public void loadItems() {
        // Configure the list
        this.getCryptocurrenciesItems().setModel(this.getCryptocurrenciesName());
        this.getCryptocurrenciesItems().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getCryptocurrenciesItems().setFont(new Font("Arial", Font.PLAIN, 17));
        this.getCryptocurrenciesItems().setBackground(Color.WHITE);
        this.getCryptocurrenciesItems().setForeground(Color.BLACK);

        // Configure the scroll pane
        this.setListScroller(new JScrollPane(this.getCryptocurrenciesItems()));

        // Add the search field only to the top
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align search field to the left
        searchPanel.add(this.getSearchField());
        this.add(searchPanel, BorderLayout.NORTH); // Add search bar to the top
        this.add(this.getListScroller(), BorderLayout.CENTER); // Add the list in the center
    }

    public void filterCryptocurrencies(String query) {
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        IntStream.range(0, this.getCryptocurrenciesName().size())
                .mapToObj(this.getCryptocurrenciesName()::getElementAt)
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .forEach(filteredModel::addElement);
        this.getCryptocurrenciesItems().setModel(filteredModel);
    }

    public void restoreFullList() {
        // Restore the original list
        this.getCryptocurrenciesItems().setModel(this.getCryptocurrenciesName());
    }
}
