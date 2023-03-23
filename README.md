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

### Polymorphism

```
Message[] Messages = new Message[0];

Messages = appendArray(Messages, switch (MessageType){
                        case "SOSMessage" -> new SOSMessage(
                                Message_ID,
                                Content,
                                DataReader.StringToPlanet(SourcePlent),
                                DataReader.StringToPlanet(DestinationPalent),
                                DataReader.StringToMessageType(MessageType),
                                DataReader.StringToRecipient(AdditionalProperty)
                                );
                        
                    });
```

I also used it for display purposes, more specifically at the enhanced switch statement.
```java
public class Employee{
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
}
```

## Binary IO

### Short description

Binary IO is a way for programmers to read/write to files stored on the disk at byte level. You can also utilise Text IO
to automatically encode & decode Binary, but Binary IO doesn't require any sort of conversion, since the files are saved
as bytes. This makes Binary IO more efficient. 

#### Filtered IO Classes

![Binary IO Classes](./docs/Binary_IO_Classes.png)

When working with Binary files, it's recommended to use buffered streams [(```BufferedInputStream```/ ```BufferedOutputStream```)]().
Reason being is that  buffer streams are stored RAM instead of Memory, therefore working with buffer is faster than
trying to access files from disk.

#### Filtered IO

Using Filtered IO, you can extract primitive data types from a file. In order for us to do this, we need a filtered stream
instance [(```FilterInputStream``` / ```FilterOutputStream```)](#Filtered-IO-Classes),

#### Object IO

I think Object IO falls out the scope of this practical.

#### ARM (Automatic Resource Management)

Just like in the [last practical](https://github.com/ThaBeanBoy/UJ_CSC2A_P03), we can use ARM to read & write Binary files.

### Reading Ship from Binary file

## Cool things

```java
public class Employee{
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
}
```