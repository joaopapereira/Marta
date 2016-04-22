package uk.co.jpereira.Marta.api;

import uk.co.jpereira.Marta.ApplicationStatus;
import uk.co.jpereira.Marta.protocol.Stages;
import uk.co.jpereira.Marta.protocol.messages.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by joao on 4/18/16.
 */
@Path(BaseAPI.BASE_URL + "/hive")
public class HiveService extends BaseAPI {

    final private String STATUS = "status";

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public StatusMessage status() {
        StatusMessage msg = new StatusMessage();
        msg.setStatus(ApplicationStatus.WORKING);
        return msg;
    }

    @POST
    @Path("/location/new")
    @Produces(MediaType.APPLICATION_JSON)
    public ChallengeMessage foundLocation(
            @HeaderParam("X-Auth-Key") final String key) {
        String remoteHost = request.getRemoteHost();
        String localAddress = request.getLocalAddr();
        response.addHeader("X-Auth-Key", key);
        ChallengeMessage msg = ChallengeMessage.generate(remoteHost, localAddress, key);
        getSession().setAttribute("token", msg.getToken());
        getSession().setAttribute(STATUS, Stages.Challenging);
        return msg;
    }

    @POST
    @Path("/location/challenge")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseMessage challenge(
            @HeaderParam("X-Auth-Key") final String key,
            ChallengeResponseMessage challengeMessage) {
        if(getSession().getAttribute(STATUS) == null ||
                getSession().getAttribute(STATUS) != Stages.Challenging) {
            BaseMessage msg = new BaseMessage();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return msg;
        }
        if(getSession().getAttribute("token") == null) {
            ChallengeMessage msg = new ChallengeMessage();
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return msg;
        }

        getSession().setAttribute(STATUS, Stages.WaitingOnOffer);
        WhatOffersMessage msg = new WhatOffersMessage();
        return msg;
    }

    @POST
    @Path("/location/offers")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseMessage offers(
            OfferMessage offersMessage) {
        if(getSession().getAttribute(STATUS) == null ||
                getSession().getAttribute(STATUS) != Stages.WaitingOnOffer) {
            BaseMessage msg = new BaseMessage();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return msg;
        }

        getSession().setAttribute(STATUS, Stages.WaitingOnOffer);
        WhatOffersMessage msg = new WhatOffersMessage();
        return msg;
    }
}
