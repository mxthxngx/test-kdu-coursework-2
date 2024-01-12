package org.handson.question3;

import org.handson.MyLogger;

class Factors implements Runnable
{    private int number;
    public Factors(int n)
    {
        this.number=n;
    }

    /**
     * Calculates the factors of a number
     *
     * @param  paramName  None
     * @return            None
     */
    @Override
    public void run() {

        for(int i=1;i<=number;i++)
        {
            if(number%i==0 )
            {
                MyLogger.customLogger("Factor of "+number+" is: "+i,"DEBUG");
                
            }
        }
    }
    

}