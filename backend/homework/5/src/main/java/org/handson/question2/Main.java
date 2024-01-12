package org.handson.question2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
      ExecutorService executorService = Executors.newFixedThreadPool(3);

    
      executorService.submit(new MessageSender("Message from Producer1"));
      executorService.submit(new MessageSender("Message from Producer2"));
      executorService.submit(new MessageSender("Message from Producer3"));

     
      executorService.submit(new MessageReceiver("Consumer1 reads:"));
      executorService.submit(new MessageReceiver("Consumer2 reads:"));
      executorService.submit(new MessageReceiver("Consumer3 reads:"));

      
      executorService.shutdown();
  }
}