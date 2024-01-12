package org.handson.question1;

import org.handson.MyLogger;

public class MessageSender implements Runnable{
    private String message;
    MessageQueue queue = MessageQueue.getInstance();
    MessageSender(String message)
    {
        this.message=message;
    }
    /**
     * A description of the entire Java function.
     *
     * @param  paramName  description of parameter
     * @return            description of return value
     */
    public void run()
    {
        MyLogger.customLogger("Sending message: "+ message,"DEBUG");
        queue.storeMessage(message);

    }
    
}
