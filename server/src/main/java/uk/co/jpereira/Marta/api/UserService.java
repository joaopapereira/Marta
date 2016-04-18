package uk.co.jpereira.Marta.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
/**
 * Created by joao on 4/12/16.
 */
@Path("/webapi")
public class UserService {
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        String output = "Hi from Jersey REST: " + name;
        return output;
    }
}
