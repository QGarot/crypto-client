package com.cryptoclient.listener.Registrationfailed;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.registrationfailed.Registrationfailed;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.listener.ViewListener;
import com.cryptoclient.networking.Connection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationfailedListener extends ViewListener<Registrationfailed>{
    public RegistrationfailedListener(Application application, Connection connection, Registrationfailed registrationfailedView) {
        super(application, connection, registrationfailedView);
    }

    @Override
    public void listen() {
        this.gobackregister();
    }

    private void gobackregister() {
        this.getView().getBackRegister().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getApp().getViewManager().displayView(Configuration.VIEW_REGISTER);
            }
        });
    }

}