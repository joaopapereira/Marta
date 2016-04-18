package uk.co.jpereira.Marta.api;

import org.glassfish.jersey.server.model.ParamQualifier;
import uk.co.jpereira.Marta.ApplicationStatus;
import uk.co.jpereira.Marta.protocol.messages.ChallengeMessage;
import uk.co.jpereira.Marta.protocol.messages.StatusMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by joao on 4/18/16.
 */
@Path(BaseAPI.BASE_URL + "/hive")
public class HiveService extends BaseAPI {
    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public StatusMessage status() {
        StatusMessage msg = new StatusMessage();
        msg.setStatus(ApplicationStatus.WORKING);
        return msg;
    }

    @POST
    @Path("/found_loc")
    @Produces(MediaType.APPLICATION_JSON)
    public ChallengeMessage foundLocation(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @HeaderParam("X-Auth-Key") final String key) {
        String remoteHost = request.getRemoteHost();
        String localAddress = request.getLocalAddr();
        response.addHeader("X-Auth-Key", key);
        return ChallengeMessage.generate(remoteHost, localAddress, key);
    }

    @POST
    @Path("/challenge")
    @Produces(MediaType.APPLICATION_JSON)
    public ChallengeMessage challenge(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @HeaderParam("X-Auth-Key") final String key) {
        String remoteHost = request.getRemoteHost();
        String localAddress = request.getLocalAddr();
        response.addHeader("X-Auth-Key", key);
        return ChallengeMessage.generate(remoteHost, localAddress, key);
    }
}
