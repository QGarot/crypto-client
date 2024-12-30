package com.cryptoclient.networking.packets;

import com.cryptoclient.application.Application;
import com.cryptoclient.networking.packets.authentification.LoginResponse;
import com.cryptoclient.networking.packets.authentification.RegisterResponse;
import com.cryptoclient.networking.packets.dashboard.SelectCryptoResponse;
import com.cryptoclient.networking.packets.headers.IncomingHeaders;
import org.json.JSONObject;

import java.util.HashMap;

public class PacketsHandler {
    private final HashMap<Integer, Event> events;
    private final Application application;

    public PacketsHandler(Application application) {
        this.events = new HashMap<>();
        this.application = application;
        // Load all events the client can receive from the server
        this.registerEvents();
    }

    private Application getApplication() {
        return this.application;
    }

    private void registerEvents() {
        this.getEvents().put(IncomingHeaders.LOGIN_RESPONSE, new LoginResponse());
        this.getEvents().put(IncomingHeaders.DASHBOARD_SELECT_CRYPTO_RESPONSE, new SelectCryptoResponse());
        this.getEvents().put(IncomingHeaders.REGISTER_RESPONSE, new RegisterResponse());
    }

    public HashMap<Integer, Event> getEvents() {
        return this.events;
    }

    public void handle(JSONObject packet) {
        if (packet.has("header")) {
            int header = packet.getInt("header");
            if (this.getEvents().containsKey(header)) {
                // Trigger the event corresponding to this header
                this.getEvents().get(header).handle(this.getApplication(), packet);
            } else {
                System.out.println("Unregistered event header");
            }
        } else {
            System.out.println("Invalid packet format");
        }
    }
}
