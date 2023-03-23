package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.RECIPIENT_TYPE;

/**
 * A form of message
 * @author TG Chipoyera
 * @version P04
 * @see Message,IValidation,RECIPIENT_TYPE
 */
public class SOSMessage extends Message implements IValidation {
    private final RECIPIENT_TYPE Recipient;

    /**
     * Constructs an instance of SOSMessage
     * @param iD ID of the Message
     * @param contents The contents of the message
     * @param planet_source The planet where the message is coming from
     * @param planet_destination The planet where the message is destined for
     * @param message_type The type of message
     * @param Recipient The recipient
     */
    public SOSMessage(
            String iD,
            String contents,
            PLANET_TYPE planet_source,
            PLANET_TYPE planet_destination,
            MESSAGE_TYPE message_type,
            RECIPIENT_TYPE Recipient
    ){
        super(iD, contents, planet_source, planet_destination, message_type);
        this.Recipient = Recipient;
    }

    /**
     * Returns the recipient
     * @return RECIPIENT_TYPE
     */
    public final RECIPIENT_TYPE getRecipient(){ return this.Recipient; }

    //Implementing Validation Interface

    /**
     * Validates the SOSMessage
     * @return boolean
     */
    @Override
    public boolean validate() {
        return switch(this.Recipient){
            case GOVERNMENT, PUBLIC ->  true;
            default -> false;
        };
    }
}
