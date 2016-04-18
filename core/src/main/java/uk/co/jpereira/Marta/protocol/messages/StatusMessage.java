package uk.co.jpereira.Marta.protocol.messages;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.jpereira.Marta.ApplicationStatus;

/**
 * Created by joao on 4/18/16.
 */
@XmlRootElement
public class StatusMessage extends BaseMessage{
    @JsonProperty
    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
