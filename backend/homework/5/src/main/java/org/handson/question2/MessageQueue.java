package org.handson.question2;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

import org.handson.MyLogger;


public class MessageQueue {

    private static final AtomicReference<MessageQueue> INSTANCE = new AtomicReference<>();
    private ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();

    private MessageQueue() {   }

    public static MessageQueue getInstance() {
        INSTANCE.compareAndSet(null, new MessageQueue());
        return INSTANCE.get();
    }

    public void storeMessage(String message) {
        MyLogger.customLogger("Storing message: " + message, "DEBUG");
        messages.add(message);
    }

    public String getMessage() {
        return messages.poll();
    }
}
