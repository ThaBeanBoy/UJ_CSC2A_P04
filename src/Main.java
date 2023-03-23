import acsse.csc2a.model.*;

import acsse.csc2a.model.Employee;

import acsse.csc2a.file.DataReader;
public class Main {
    public static void main(String[] args) {
        Ship ShipInDat = DataReader.readShip("data/ship.dat", "data/messages.dat");

        try{
            Employee EMP_220150124 = new Employee("220150124", "TG", "Chipoyera", ShipInDat);
            System.out.println(EMP_220150124.printMessages());
        }catch(Employee.dummy e){
            System.out.println("The Employee ID length should be greater than or equals to 6");
        }
    }
}