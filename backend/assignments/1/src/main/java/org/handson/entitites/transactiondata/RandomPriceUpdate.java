package org.handson.entitites.transactiondata;

import org.handson.Constants;
import org.handson.entitites.repositories.CoinRepository;
import org.handson.logger.MyLogger;

public class RandomPriceUpdate implements TransactionData {
    private String x;
    private String y;
    private Double z;
    
    public RandomPriceUpdate(Double z, String y)
    {
        this.z = z;
        this.y = y;
    }
    
    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public String getX() {
        return this.x;
    }
    
    public void setX(String x) {
        this.x = x;
    }
    
    public String getY() {
        return this.y;
    }

    public void setY(String y)
    {
         this.y = y;
    }
    
    public boolean doStuff()
    {   
        boolean status = CoinRepository.getInstance().updateCoinPrice(y, z);
        if(status)
        {
            MyLogger.customLogger("Price updated", Constants.INFO_LOGGER);
        }
        else
        {
            MyLogger.customLogger("Coin not found", Constants.ERROR_LOGGER);
        }
        return status;
    }
}