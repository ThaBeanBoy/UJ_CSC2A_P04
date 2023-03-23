package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Ship;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.NormalMessage;

public class Employee implements IValidation{
    private String EmployeeID;
    private String FirstName;
    private String LastName;
    private Ship ShipData;

    public Employee(String EmployeeID, String FirstName, String LastName, Ship Ship) throws dummy{
        if(EmployeeID.length() <= 6)
            throw new dummy();

        this.EmployeeID = EmployeeID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ShipData = Ship;
    }

    public String printMessages(){
        StringBuilder finalPrint = new StringBuilder();

        finalPrint.append(String.format("%s %s Messages \n\n", this.getEmployeeID(), this.getLastName()));

        for(Message Message : this.ShipData.getMessages()) {
            //Getting Message properties
            finalPrint.append(String.format("""
                            ID: %s | %s -> %s | %s
                            Message Type:  %s | %s
                            
                            """,
                    // Message properties
                    Message.getID(),
                    Message.getPlanet_source(),
                    Message.getPlanet_destination(),
                    Message.getContents(),
                    Message.getMessage_type(),

                    //Child class properties
                    switch(Message.getMessage_type()){
                        case SOSMessage -> {
                            SOSMessage SOS = (SOSMessage) Message;
                            yield String.format("Recipient: %s", SOS.getRecipient());
                        }

                        case EncryptedMessage -> {
                            EncryptedMessage EM = (EncryptedMessage) Message;
                            yield String.format("Key: %s", EM.getKey());
                        }

                        case NormalMessage -> {
                            NormalMessage NM = (NormalMessage) Message;
                            yield String.format("Message length: %d", NM.getMessageLength());
                        }
                    }
            ));
        }

        return finalPrint.toString();
    }

    public final String getEmployeeID(){return this.EmployeeID; }
    public final String getFirstName(){return this.FirstName;}
    public final String getLastName(){return this.LastName;}
    public final Ship getShipData(){return this.ShipData;}

    //Implementing Validation Interface
    @Override
    public boolean validate() {
        return this.EmployeeID.length() >= 6;
    }

    public static class dummy extends Exception {}
}
