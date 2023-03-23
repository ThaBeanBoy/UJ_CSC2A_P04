package acsse.csc2a.file;

// Binary IO classes
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
// Binary IO exceptions
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

// acsse classes
import acsse.csc2a.model.Ship;
import acsse.csc2a.model.Message;

public class DataReader {
    private static DataInputStream StreamDataStream;

    public static Ship readShip(String shipFilePath, String MessagesFilePath){
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
            while(true){ //The file itself has an end, so it's fine to make a while loop that has true inside it
                ShipName.append(String.format(" %s", ShipDataStream.readUTF()));
            }
        }catch (EOFException e){
            return new Ship(ShipID, ShipName.toString());
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
}
