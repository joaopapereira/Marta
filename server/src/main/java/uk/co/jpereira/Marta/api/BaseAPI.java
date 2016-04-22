package uk.co.jpereira.Marta.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

/**
 * Created by joao on 4/18/16.
 */
public class BaseAPI {
    final static public String API_VERSION = "1.0";
    final static public String BASE_URL = "/api/" + API_VERSION;

    @Context
    protected HttpServletRequest request;
    @Context
    protected HttpServletResponse response;

    protected HttpSession getSession() {
        return request.getSession();
    }
}
