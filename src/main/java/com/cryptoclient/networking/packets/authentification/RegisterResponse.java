package com.cryptoclient.networking.packets.authentification;

import com.cryptoclient.application.Application;
import com.cryptoclient.application.views.index.register.Register;
import com.cryptoclient.config.Configuration;
import com.cryptoclient.networking.packets.Event;
import org.json.JSONObject;

public class RegisterResponse extends Event {
    @Override
    public void handle(Application application, JSONObject packet) {
        if ((boolean) packet.get("success")) {
            ((Register) application.getViewManager().getViews().get(Configuration.VIEW_REGISTER)).setMessage(packet.getString("message"), false);
            ((Register) application.getViewManager().getViews().get(Configuration.VIEW_REGISTER)).getRegisterButton().setEnabled(false);
        } else {
            ((Register) application.getViewManager().getViews().get(Configuration.VIEW_REGISTER)).setMessage(packet.getString("message"), true);
        }
    }
}
