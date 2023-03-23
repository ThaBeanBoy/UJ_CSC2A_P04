package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.RECIPIENT_TYPE;

/**
 * A form of Message
 * @author TG Chipoyera
 * @version P04
 * @see Message,IValidation
 */
public class EncryptedMessage extends Message implements  IValidation{
    private String Key;

    /**
     * Construct an instance of EncryptedMessage
     * @param iD ID of the Message
     * @param contents The contents of the message
     * @param planet_source The planet where the message is coming from
     * @param planet_destination The planet where the message is destined for
     * @param message_type The type of message
     * @param Key The key to the message
     */
    public EncryptedMessage(
            String iD,
            String contents,
            PLANET_TYPE planet_source,
            PLANET_TYPE planet_destination,
            MESSAGE_TYPE message_type,
            String Key
    )
    {
        super(iD, contents, planet_source, planet_destination, message_type);
        this.Key = Key;
    }

    /**
     * Returns the key
     * @return String
     */
    public String getKey(){return this.Key; }

    //Implementing Validation Interface

    /**
     * Validates the EncrypedMessage
     * @return boolean
     */
    @Override
    public boolean validate() {
        return (Key.length() > 10);
    }

    /**
     * No longer in use
     */
    public static class KeyLengthErr extends Exception{
        public KeyLengthErr() {
            super("errorMessage");
        }
    }
}
