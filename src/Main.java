import acsse.csc2a.model.*;

import acsse.csc2a.file.DataReader;
public class Main {
    public static void main(String[] args) {
        Ship ShipInDat = DataReader.readShip("data/ship.dat", "data/messages.dat");
        System.out.println(ShipInDat.getName());
    }
}