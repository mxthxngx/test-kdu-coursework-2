package org.handson.entitites.transactiondata;

import org.handson.Constants;
import org.handson.entitites.repositories.CoinRepository;
import org.handson.logger.MyLogger;

public class SomeOtherData implements TransactionData {
    private String a;
    private String b;
    private long c;

    public SomeOtherData(long d)
    {
        this.c = d;
    }

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public boolean doTheThing()
    {
        boolean ok = CoinRepository.getInstance().updateVolume(b, c, "SOMETHING");
        if(ok)
        {
            MyLogger.customLogger("It worked, I guess", Constants.INFO_LOGGER);
        }
        else
        {
            MyLogger.customLogger("Error. Something broke.", Constants.ERROR_LOGGER);
        }
        return ok;
    }
}