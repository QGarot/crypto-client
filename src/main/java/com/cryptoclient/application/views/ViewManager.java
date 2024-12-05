package com.cryptoclient.application.views;

import com.cryptoclient.application.views.dashboard.Dashboard;
import com.cryptoclient.application.views.index.login.Login;
import com.cryptoclient.application.views.index.register.Register;
import com.cryptoclient.application.views.loading.Loading;
import com.cryptoclient.application.views.registrationfailed.Registrationfailed;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.application.Application;

import java.util.HashMap;

public class ViewManager {

    private final HashMap<String, View> views;
    private final Application application;

    public ViewManager(Application application) {
        this.views = new HashMap<>();
        this.application = application;
        this.loadViews();
    }

    private void loadViews() {
        this.getViews().put(Configuration.VIEW_LOGIN, new Login());
        this.getViews().put(Configuration.VIEW_REGISTER, new Register());
        this.getViews().put(Configuration.VIEW_DASHBOARD, new Dashboard());
        this.getViews().put(Configuration.VIEW_LOADING, new Loading());
        this.getViews().put(Configuration.VIEW_REGISTRATIONFAILED, new Registrationfailed());
    }

    private Application getApplication() {
        return this.application;
    }

    public HashMap<String, View> getViews() {
        return this.views;
    }

    public void displayView(String viewName) {

        // Make all visible views invisible
        for (View view : this.getViews().values()) {
            if (view.isVisible()) {
                view.setVisible(false);
            }
        }

        // Empty content pane
        this.getApplication().getContentPane().removeAll();

        // Display the view
        if (this.getViews().containsKey(viewName)) {
            // Get the view
            View view = this.getViews().get(viewName);
            view.loadComponents();
            // Show it
            this.getApplication().getContentPane().add(view);
            view.setVisible(true);
        }
    }
}
