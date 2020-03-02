package no.tull.tull;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/path")
public class MyResource {

    @Path("here")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response callMe(Struct in) {
        return Response.created(URI.create("http://her")).entity(new Struct("Hei", 7)).build();
    }
}
