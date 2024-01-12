package org.handson.question2;

import org.handson.MyLogger;

// this is the task class
public class MessageSender implements Runnable{
    private String message;
    MessageQueue queue = MessageQueue.getInstance();
    MessageSender(String message)
    {
        this.message=message;
    }
    public void run()
    {
        MyLogger.customLogger("Sending message: "+ message,"DEBUG");
        queue.storeMessage(message);

    }
    
}
