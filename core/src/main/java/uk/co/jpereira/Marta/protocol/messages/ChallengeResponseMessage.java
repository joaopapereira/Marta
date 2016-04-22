package uk.co.jpereira.Marta.protocol.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by joao on 4/20/16.
 */
@XmlRootElement
public class ChallengeResponseMessage extends BaseMessage {

    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
