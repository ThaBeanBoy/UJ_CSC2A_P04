package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.RECIPIENT_TYPE;

public class EncryptedMessage extends Message implements  IValidation{
    public String Key;
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

    //Implementing Validation Interface
    @Override
    public boolean validate() {
        return (Key.length() > 10);
    }

    public static class KeyLengthErr extends Exception{
        public KeyLengthErr() {
            super("errorMessage");
        }
    }
}
