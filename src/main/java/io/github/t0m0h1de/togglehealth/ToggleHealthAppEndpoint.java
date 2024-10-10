package io.github.t0m0h1de.togglehealth;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class ToggleHealthAppEndpoint {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response health() {
        if (ToggleHealthAppService.isHealthy) {
            return Response.ok("healthy").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{message}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayStatus(final @PathParam("message") String message) {
        ToggleHealthAppService.applyMessage(message);
        if (ToggleHealthAppService.isHealthy) {
            return Response.ok("healthy").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
