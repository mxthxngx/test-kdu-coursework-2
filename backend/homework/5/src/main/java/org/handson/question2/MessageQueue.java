package org.handson.question2;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.handson.MyLogger;

public class MessageQueue {
    public volatile String lastMessage;
    private static MessageQueue messageQueue;
    ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();
    private MessageQueue()
    {

    }
    synchronized public static MessageQueue getInstance()
    {
        if(messageQueue == null)
        {
            messageQueue = new MessageQueue();
        }
        return messageQueue;
    }

    public void storeMessage(String message)
    {
        try{
        MyLogger.customLogger("Storing message: "+ message,"DEBUG");
        messageQueue.messages.add(message);
        }
        catch(Exception e)
        {
            MyLogger.customLogger("Exception in storing message", "ERROR");
        }
    }
    public String getMessage()
    {
        return messageQueue.messages.poll();
    }

}

