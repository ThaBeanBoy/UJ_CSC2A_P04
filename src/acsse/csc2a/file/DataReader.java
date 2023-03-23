package acsse.csc2a.file;

// Binary IO classes
import java.io.*;

import acsse.csc2a.model.Ship;

public class DataReader {
    public static Ship readShip(String shipFilePath, String MessagesFilePath){
        try(
            //Creating streams
            InputStream ShipStream = new FileInputStream(shipFilePath);
            InputStream MessagesStream = new FileInputStream(MessagesFilePath);
        ){
            BufferedInputStream ShipBufferStream = new BufferedInputStream(ShipStream);
            BufferedInputStream MessagesInputStream = new BufferedInputStream(MessagesStream);

            System.out.println(ShipBufferStream.available());
        } catch (FileNotFoundException e) {
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

        return new Ship("SH1111", "Hello World");
    }
}
