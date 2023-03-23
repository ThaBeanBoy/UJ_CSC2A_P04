package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;

/**
 * A form of message
 * @author TG Chipoyera
 * @version P04
 * @see Message,IValidation
 */
public class NormalMessage extends Message implements IValidation {
    private final int MESSAGE_LENGTH;

    /**
     * Constructs an instance of NormalMessage
     * @param iD ID of the Message
     * @param contents The contents of the message
     * @param planet_source The planet where the message is coming from
     * @param planet_destination The planet where the message is destined for
     * @param message_type The type of message
     */
    public NormalMessage(
            String iD,
            String contents,
            PLANET_TYPE planet_source,
            PLANET_TYPE planet_destination,
            MESSAGE_TYPE message_type
    ){
        super(iD, contents, planet_source, planet_destination, message_type);
        this.MESSAGE_LENGTH = contents.length();
    }

    /**
     * Returns the length of a message
     * @return int
     */
    public int getMessageLength(){
        return this.MESSAGE_LENGTH;
    }

    //Implementing Validation Interface

    /**
     * Validates the NormalMessage
     * @return boolean
     */
    @Override
    public boolean validate() {
        return (this.contents.length() <= this.MESSAGE_LENGTH);
    }
}
