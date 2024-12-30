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
import java.util.Objects;

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
            String username = this.getView().getUsernameField().getText();
            String password = this.getView().getPasswordField().getText(); // TODO: getText() is deprecated
            String confirmPassword = this.getView().getConfirmPasswordField().getText();

            // Check if inputs are empty
            if (Objects.equals(username, this.getView().getUsernamePlaceholder())) {
                username = "";
            }
            if (Objects.equals(password, this.getView().getPasswordPlaceholder())) {
                password = "";
            }
            if (Objects.equals(confirmPassword, this.getView().getConfirmPasswordPlaceholder())) {
                confirmPassword = "";
            }

            JSONObject packet = new JSONObject();
            packet.put("header", OutgoingHeaders.REGISTER_SUBMIT_REQUEST);
            packet.put("username", username);
            packet.put("password", password);
            packet.put("confirmPassword", confirmPassword);
            this.getConnection().sendPacket(packet);
        });
    }
}
