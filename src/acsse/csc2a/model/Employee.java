package acsse.csc2a.model;

import acsse.csc2a.model.IValidation;
import acsse.csc2a.model.Ship;

public class Employee implements IValidation{
    private String EmployeeID;
    private String FirstName;
    private String LastName;
    private Ship ShipData;

    public String getEmployeeID(){return this.EmployeeID; }
    public String getFirstName(){return this.FirstName;}
    public String getLastName(){return this.LastName;}
    public Ship getShipData(){return this.ShipData;}

    //Implementing Validation Interface
    @Override
    public boolean validate() {
        return this.EmployeeID.length() >= 6;
    }
}
