package io.github.t0m0h1de.togglehealth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Run integration tests against the server and the deployed application.
 */
@RunAsClient
@ExtendWith(ArquillianExtension.class)
public class ToggleHealthAppApplicationIT {

    @Test
    public void testToggleHealthEndpointHealthy() {
        try (Client client = ClientBuilder.newClient()) {
            Response response = client
                    .target(URI.create("http://localhost:8080/"))
                    .path("/health/healthy")
                    .request()
                    .get();

            assertEquals(200, response.getStatus());
            assertEquals("healthy", response.readEntity(String.class));
        }
    }

    @Test
    public void testToggleHealthEndpointSayHealthy() {
        try (Client client = ClientBuilder.newClient()) {
            Response response = client
                    .target(URI.create("http://localhost:8080/"))
                    .path("/health")
                    .request()
                    .get();

            assertEquals(200, response.getStatus());
            assertEquals("healthy", response.readEntity(String.class));
        }
    }

    @Test
    public void testToggleHealthEndopointConfused() {
        try (Client client = ClientBuilder.newClient()) {
            Response response = client
                    .target((URI.create("http://localhost:8080/")))
                    .path("/health/confuse")
                    .request()
                    .get();

            assertEquals(400, response.getStatus());
        }
    }

    @Test
    public void testToggleHealthEndopointSayUnhealthy() {
        try (Client client = ClientBuilder.newClient()) {
            client.target(URI.create("http://localhost:8080/"))
                    .path("/health/confuse").request().get();
            Response response = client
                    .target((URI.create("http://localhost:8080/")))
                    .path("/health")
                    .request()
                    .get();

            assertEquals(400, response.getStatus());
        }
    }
}
