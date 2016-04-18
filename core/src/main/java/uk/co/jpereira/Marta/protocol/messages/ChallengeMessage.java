package uk.co.jpereira.Marta.protocol.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.jpereira.Marta.utils.Crypt;

import java.time.LocalDateTime;

/**
 * Created by joao on 4/18/16.
 */
public class ChallengeMessage extends BaseMessage {
    @JsonProperty
    private String token;
    @JsonProperty
    private String time;

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public static ChallengeMessage generate(String clientHost, String serverHost, String key) {
        ChallengeMessage message = new ChallengeMessage();
        String time = LocalDateTime.now().toString();
        message.setToken(Crypt.encrypt(clientHost+time+serverHost, key));
        return message;
    }
}
