package com.cryptoclient.listener.register;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.index.register.Register;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.listener.ViewListener;
import com.cryptoclient.networking.Connection;
import com.cryptoclient.networking.packets.headers.OutgoingHeaders;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterListener extends ViewListener<Register> {

    public RegisterListener(Application application, Connection connection, Register registerView) {
        super(application, connection, registerView);
    }

    @Override
    public void listen() {
        this.listenGoToLogin();
        this.listenRegisterSubmit();
    }

    private void listenGoToLogin() {
        this.getView().getLoginLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getApp().getViewManager().displayView(Configuration.VIEW_LOGIN);
            }
        });
    }

    private void listenRegisterSubmit() {
        this.getView().getRegisterButton().addActionListener(e -> {
            String password = this.getView().getPasswordField().getText(); // TODO: getText() is deprecated
            String confirmPassword = this.getView().getConfirmPasswordField().getText();

            if (!password.equals(confirmPassword)) {
                this.getApp().getViewManager().displayView(Configuration.VIEW_REGISTRATIONFAILED);
            } else {
                // Si les mots de passe correspondent, envoyer le paquet
                JSONObject packet = new JSONObject();
                packet.put("header", OutgoingHeaders.REGISTER_SUBMIT_REQUEST);
                packet.put("username", this.getView().getUsernameField().getText());
                packet.put("password", password);
                this.getConnection().sendPacket(packet);
                this.getApp().getViewManager().displayView(Configuration.VIEW_LOGIN);

            }
        });
    }
}
