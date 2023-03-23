package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Ship;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.NormalMessage;

/**
 * Employee class, contains details about the employee and has a Ship class
 * @author TG Chipoyera
 * @version P04
 * @see Ship,SOSMessage,EncryptedMessage,NormalMessage,IValidation
 */
public class Employee implements IValidation{
    private final String EmployeeID;
    private final String FirstName;
    private final String LastName;
    private Ship ShipData;

    /**
     * Creates an instance of Employee class
     * @param EmployeeID The ID of the employee, the length can't be less than 6 characters
     * @param FirstName The first name of the employee
     * @param LastName The last name of the employee
     * @param Ship A ship objecgt
     * @throws dummy Exception is thrown if the length of the EmployeeID is less than 6
     */
    public Employee(String EmployeeID, String FirstName, String LastName, Ship Ship) throws dummy{
        if(EmployeeID.length() < 6)
            throw new dummy();

        this.EmployeeID = EmployeeID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ShipData = Ship;
    }

    /**
     * The string contains details about the employee and all the messages in the Ship
     * @return String
     */
    public final String printMessages(){
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

    /**
     * Checks & validates each message in the Ship.
     * @return boolean
     */
    public boolean sendMessages(){
        for(Message MSG : this.ShipData.getMessages()) {
            if (MSG.validate())
                continue;
            else
                return false;
        }

        return true;
    }

    /**
     * The employee's ID
     * @return String
     */
    public final String getEmployeeID(){return this.EmployeeID; }

    /**
     * Returns the first name of the employee
     * @return String
     */
    public final String getFirstName(){return this.FirstName;}

    /**
     * Returns the last name of the employee
     * @return String
     */
    public final String getLastName(){return this.LastName;}

    /**
     * Returns the ship that the employee composes of
     * @return Ship
     */
    public final Ship getShipData(){return this.ShipData;}

    //Implementing Validation Interface

    /**
     * Validates the employee
     * @return boolean
     */
    @Override
    public boolean validate() {
        return this.EmployeeID.length() >= 6;
    }

    public static class dummy extends Exception {}
}
