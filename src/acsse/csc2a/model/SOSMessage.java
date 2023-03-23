package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.RECIPIENT_TYPE;

public class SOSMessage extends Message implements IValidation {
    private final RECIPIENT_TYPE Recipient;

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

    public final RECIPIENT_TYPE getRecipient(){ return this.Recipient; }

    //Implementing Validation Interface
    @Override
    public boolean validate() {
        return switch(this.Recipient){
            case GOVERNMENT, PUBLIC ->  true;
            default -> false;
        };
    }
}
