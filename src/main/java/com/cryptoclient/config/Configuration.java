package com.cryptoclient.config;

/**
 * Global configuration of the client
 */
public class Configuration {
    // Window
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    public static final boolean WINDOW_RESIZABLE = false;
    public static final String WINDOW_TITLE = "Crypto-Client";

    // Network
    public static final String NETWORKING_HOST = "127.0.0.1";
    public static final int NETWORKING_PORT = 30000;

    // Views
    public static final String VIEW_LOGIN = "login";
    public static final String VIEW_REGISTER = "register";
    public static final String VIEW_DASHBOARD = "dashboard";
    public static final String VIEW_LOADING = "loading";
    public static final String VIEW_REGISTRATIONFAILED = "registrationfailed";

}
