# UJ CSC2A Practical 4

## Table Of Content

## Additional Information

This practical adds on to the [previous practical (Prac03)](https://github.com/ThaBeanBoy/UJ_CSC2A_P03). This time we 
have been provided with a JAR file

## UML

## The JAR file

### IntelliJ

### Build batch file

## Interface implementation

## Object-oriented Concepts
One of the main things Our lecturer wanted us to grasp in this project was making a class inherit from a parent class.
In the [JAR file](./lib/MWSCB.jar) provided to us, a ```Message``` class was provided to us with all the relavent properties
& methods. I honestly woud've preffered if he let us build upon [last week's practical](https://github.com/ThaBeanBoy/UJ_CSC2A_P03).
I think this would have given us the opportunity to play around with the ```public```, ```private``` & ```protected```. But
I guess that's simple to understand anyway.

**NB: I'll be demonstrating inheritance using one of the child class (NormalMessage Class)**

```java
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
```

obviously we had to ```extend``` the Message in order to inherit, but there was 1 thing I actually learned here. Initially, 
the EncryptedMessage constructor would throw an Err exception. If the key length was less than 10 characters, an exception
would be thrown. But the IDE was complaining and said that the bas class' constructor should be called first.

## Binary IO
