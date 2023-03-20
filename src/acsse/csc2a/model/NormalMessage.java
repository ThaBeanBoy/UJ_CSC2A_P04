package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;

public class NormalMessage extends Message implements IValidation {
    private final int MESSAGE_LENGTH;

    public NormalMessage(
            String iD,
            String contents,
            PLANET_TYPE planet_source,
            PLANET_TYPE planet_destination,
            MESSAGE_TYPE message_type,
            int MessageLength
    ){
        super(iD, contents, planet_source, planet_destination, message_type);
        this.MESSAGE_LENGTH = MessageLength;
    }

    public int getMessageLength(){
        return this.MESSAGE_LENGTH;
    }

    //Implementing Validation Interface
    @Override
    public boolean validate() {
        return (this.contents.length() <= this.MESSAGE_LENGTH);
    }
}
