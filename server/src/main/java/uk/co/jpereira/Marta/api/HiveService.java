package uk.co.jpereira.Marta.api;

import uk.co.jpereira.Marta.ApplicationStatus;
import uk.co.jpereira.Marta.protocol.messages.ChallengeMessage;
import uk.co.jpereira.Marta.protocol.messages.StatusMessage;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by joao on 4/18/16.
 */
@Path(BaseAPI.BASE_URL + "/hive/")
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
    public ChallengeMessage foundLocation() {
        return ChallengeMessage.generate();
    }
}
