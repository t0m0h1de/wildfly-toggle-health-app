package io.github.t0m0h1de.togglehealth;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ToggleHealthAppService {

    private static final String CONFUSE_MESSAGE = "confuse";
    public static boolean isHealthy = true;

    public static void applyMessage(String message) {
        if (message.equals(CONFUSE_MESSAGE)) {
            isHealthy = false;
        } else {
            isHealthy = true;
        }
    }
}