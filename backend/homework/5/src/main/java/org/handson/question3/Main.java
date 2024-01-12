package org.handson.question3;

import org.handson.MyLogger;

class Main{
   
    public static void main(String[] args)
    {
        Factorial factorial = new Factorial(15);
        Thread thread1 = new Thread(factorial);
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            MyLogger.customLogger(e.toString(), "ERROR");;
            Thread.currentThread().interrupt();
        }
        Factors factors = new Factors(15);
        Thread thread2 = new Thread(factors);
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
           MyLogger.customLogger(e.toString(), "ERROR");
           Thread.currentThread().interrupt();
        }
        MyLogger.customLogger("This statement should print in the end of the code after the execution of the other threads","INFO");
    }
}