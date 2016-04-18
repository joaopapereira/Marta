package uk.co.jpereira.Marta.protocol.messages;

/**
 * Created by joao on 4/18/16.
 */
public class ChallengeMessage extends BaseMessage {
    public static ChallengeMessage generate() {
        ChallengeMessage message = new ChallengeMessage();
        return message;
    }
}
