package Message;

import utils.MessageType;

import java.util.ArrayList;

public
class MessageFactory {

    public
    Message makeMessage(MessageType messageType, String op, ArrayList<String> params)
    throws NoSuchMessageException {

        switch (messageType) {
            case REQUEST:
                return new ClientRequestMessage(op, params);
            case REPLY:
                return new ServerReplyMessage(op, params);
            default:
                throw new NoSuchMessageException("Could not make this message!");
        }
    }
}
