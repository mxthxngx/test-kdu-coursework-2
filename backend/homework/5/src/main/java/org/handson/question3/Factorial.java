package org.handson.question3;

import org.handson.MyLogger;

public class Factorial implements Runnable
{
    private int number;
    Factorial(int n)
    {
        this.number=n;
    }
    /**
     * Calculates the factorial of the number.
     *
     * @param  paramName    description of parameter
     * @return              description of return value
     */
    @Override
    public void run() {

        int fact=1;
        for(int i=1;i<=number;i++)
        {
            fact=fact*i;
        }
        MyLogger.customLogger("Factorial of "+number+" is: "+fact,"DEBUG");
    }    
}