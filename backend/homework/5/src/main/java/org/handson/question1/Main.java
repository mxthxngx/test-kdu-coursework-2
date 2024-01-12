package org.handson.question1;

/**
 * Another implementation of this problem is using the wait() function but works better when one producer is 
 * related to one sender, in this case multiple senders and multiple recievers send and retrieve data from a queue on a random basis of thread allocation
 * If one sender had been using multiple recievers or one reciever,then wait() can be used.
 */
public class Main {
    /**
     * Generates the function comment for the main function.
     *
     * @param  args  the command line arguments
     * @return       nothing
     */
    public static void main(String[] args) {
        MessageSender messageSender1 = new MessageSender("Message from Producer1");
        Thread thread1 = new Thread(messageSender1);
        MessageSender messageSender2 = new MessageSender("Message from Producer2");
        Thread thread2 = new Thread(messageSender2);
        MessageSender messageSender3 = new MessageSender("Message from Producer3");
        Thread thread3 = new Thread(messageSender3);

        thread1.start();
        thread2.start();
        thread3.start();

        MessageReceiver messageReceiver1 = new MessageReceiver("Consumer1 reads:");
        Thread thread4 = new Thread(messageReceiver1);
        thread4.start();
        MessageReceiver messageReceiver2 = new MessageReceiver("Consumer2 reads:");
        Thread thread5 = new Thread(messageReceiver2);
        thread5.start();
        MessageReceiver messageReceiver3 = new MessageReceiver("Consumer2 reads:");
        Thread thread6 = new Thread(messageReceiver3);
        thread6.start();
        
    }
}