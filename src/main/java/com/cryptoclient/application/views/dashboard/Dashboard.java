package com.cryptoclient.application.views.dashboard;

import com.cryptoclient.application.views.View;
import com.cryptoclient.application.views.dashboard.components.Content;
import com.cryptoclient.application.views.dashboard.components.MenuPanel;

import java.awt.*;

public class Dashboard extends View {

    private MenuPanel menu;
    private Content content;
    private int menuBarWidth;

    public Dashboard() {
        this.setMenuBarWidth(250);
        this.setMenu(new MenuPanel(this.getMenuBarWidth()));
        this.setContent(new Content());
    }

    public int getMenuBarWidth() {
        return menuBarWidth;
    }

    public void setMenuBarWidth(int menuBarWidth) {
        this.menuBarWidth = menuBarWidth;
    }

    public MenuPanel getMenu() {
        return menu;
    }

    public void setMenu(MenuPanel menu) {
        this.menu = menu;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public void loadComponents() {

        this.setLayout(new BorderLayout());

        // Dégradé d'arrière-plan pour le tableau de bord
        this.setBackground(new Color(45, 50, 65));

        // Ajouter les composants principaux
        this.add(this.getMenu(), BorderLayout.WEST);
        this.add(this.getContent(), BorderLayout.CENTER);
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
        }
        super.paintComponent(g);
    }
}
