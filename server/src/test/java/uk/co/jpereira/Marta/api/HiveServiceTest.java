package uk.co.jpereira.Marta.api;

import javax.ws.rs.core.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uk.co.jpereira.Marta.ApplicationStatus;
import uk.co.jpereira.Marta.protocol.messages.StatusMessage;

import static org.junit.Assert.*;

/**
 * Created by joao on 4/18/16.
 */
public class HiveServiceTest extends JerseyTest {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    protected Application configure() {
        return new ResourceConfig(HiveService.class);
    }

    @Test
    public void test() throws Exception{
        final String responseBody = target("api/1.0/hive/status").request().get(String.class);
        StatusMessage expected = new StatusMessage();
        expected.setStatus(ApplicationStatus.WORKING);
        assertEquals(mapper.writeValueAsString(expected), responseBody);
    }
}
