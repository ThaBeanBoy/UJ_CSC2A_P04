package acsse.csc2a.file;

// Binary IO classes
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
// Binary IO exceptions
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

// acsse classes
import acsse.csc2a.model.Ship;

import acsse.csc2a.model.Message;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.NormalMessage;

//Enums
import acsse.csc2a.model.MESSAGE_TYPE;
import acsse.csc2a.model.PLANET_TYPE;
import acsse.csc2a.model.LANGUAGE_TYPE;
import acsse.csc2a.model.RECIPIENT_TYPE;

import javax.xml.crypto.Data;

public class DataReader {
    public static Ship readShip(String shipFilePath, String MessagesFilePath){
        Ship Ship;
        String ShipID = "";
        StringBuilder ShipName = new StringBuilder();

        try(
            //Creating streams
            InputStream ShipStream = new FileInputStream(shipFilePath);
            InputStream MessagesStream = new FileInputStream(MessagesFilePath);
        ){
            DataInputStream ShipDataStream = new DataInputStream(ShipStream);
            DataInputStream messagesDataStream = new DataInputStream(MessagesStream);

            // Reading Ship Details
            ShipID = ShipDataStream.readUTF();
            try{
                while(true){ //The file itself has an end, so it's fine to make a while loop that has true inside it
                    ShipName.append(String.format(" %s", ShipDataStream.readUTF()));
                }
            }catch (EOFException e){
                Ship = new Ship(ShipID, ShipName.toString());
            }

            // Reading messages
            try{
                Message[] Messages = new Message[1];
                while(true){
                    String Message_ID, Content, AdditionalProperty;

                    Message_ID = messagesDataStream.readUTF();
                    Content = messagesDataStream.readUTF();
                    PLANET_TYPE SourcePlent = DataReader.StringToPlanet(messagesDataStream.readUTF());
                    PLANET_TYPE DestinationPalent = DataReader.StringToPlanet(messagesDataStream.readUTF());
                    MESSAGE_TYPE MessageType = DataReader.StringToMessageType(messagesDataStream.readUTF());
                    AdditionalProperty = messagesDataStream.readUTF();

                    Messages[0] =  switch (MessageType){
                        case SOSMessage -> new SOSMessage(
                                Message_ID,
                                Content,
                                SourcePlent,
                                DestinationPalent,
                                MessageType,
                                DataReader.StringToRecipient(AdditionalProperty)
                                );

                        case EncryptedMessage -> new EncryptedMessage(
                                Message_ID,
                                Content,
                                SourcePlent,
                                DestinationPalent,
                                MessageType,
                                AdditionalProperty
                        );

                        case NormalMessage ->  new NormalMessage(
                                Message_ID,
                                Content,
                                SourcePlent,
                                DestinationPalent,
                                MessageType,
                                Content.length()
                        );
                    };

                    //Saving message
                    Ship.addMessages(Messages);
                }
            }catch (EOFException e){
                return Ship;
            }
        }
        catch (FileNotFoundException e) {
            System.err.printf("""
                    Ship File Path: %s
                    Messages File Path: %s
                    One of the file paths are incorrect
                    %n""", shipFilePath, MessagesFilePath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IOException exception!");
            throw new RuntimeException(e);
        }
    }

    private static PLANET_TYPE StringToPlanet (String Planet){
        return switch(Planet){
            case "Mercury" -> PLANET_TYPE.Mercury;
            case "Venus" -> PLANET_TYPE.Venus;
            case "Mars" -> PLANET_TYPE.Mars;
            case "Jupiter" -> PLANET_TYPE.Jupiter;
            case "Saturn" -> PLANET_TYPE.Saturn;
            case "Uranus" -> PLANET_TYPE.Uranus;
            case "Neptune" -> PLANET_TYPE.Neptune;
            case "Pluto" -> PLANET_TYPE.Pluto;
            default -> PLANET_TYPE.Earth;
        };
    }

    private static MESSAGE_TYPE StringToMessageType(String Message){
        return switch(Message){
            case "SOSMessage" -> MESSAGE_TYPE.SOSMessage;
            case "EncryptedMessage" -> MESSAGE_TYPE.EncryptedMessage;
            default -> MESSAGE_TYPE.NormalMessage;
        };
    }

    private static LANGUAGE_TYPE StringToLanguageType(String Language){
        return switch(Language){
            case "Vane" -> LANGUAGE_TYPE.Vane;
            case "Mercurian" ->LANGUAGE_TYPE. Mercurian;
            case "Marsian" -> LANGUAGE_TYPE.Marsian;
            case "Jupe" -> LANGUAGE_TYPE.Jupe;
            case "French" -> LANGUAGE_TYPE.French;
            case "Neptunic" -> LANGUAGE_TYPE.Neptunic;
            case "Bananas" -> LANGUAGE_TYPE.Bananas;
            default -> LANGUAGE_TYPE.English;
        };
    }

    private static RECIPIENT_TYPE StringToRecipient(String Recipient){
        return switch(Recipient){
            case "GOVERNMENT" -> RECIPIENT_TYPE.GOVERNMENT;
            default -> RECIPIENT_TYPE.PUBLIC;
        };
    }
}
