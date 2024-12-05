package com.cryptoclient.listener.loading;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.loading.Loading;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.listener.ViewListener;
import com.cryptoclient.networking.Connection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingListener extends ViewListener<Loading>{
    public LoadingListener(Application application, Connection connection, Loading loadingView) {
        super(application, connection, loadingView);
    }

    @Override
    public void listen() {
        this.gobackconnexion();
    }

    private void gobackconnexion() {
        this.getView().getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getApp().getViewManager().displayView(Configuration.VIEW_LOGIN);
            }
        });
    }

}