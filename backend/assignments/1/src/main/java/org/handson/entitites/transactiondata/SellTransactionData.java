package org.handson.entitites.transactiondata;

import org.handson.Constants;
import org.handson.entitites.concrete.Trader;
import org.handson.entitites.repositories.CoinRepository;
import org.handson.entitites.repositories.TraderRepository;
import org.handson.logger.MyLogger;

public class SellTransactionData implements TransactionData {
    private String t;
    private String c;
    private long q;
    private String tw;
    
    public SellTransactionData(long q, String tw) {
        this.q = q;
        this.tw = tw;
        this.t = "sell";
    }

    public String getT() {
        return this.t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getC() {
        return this.c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public boolean doTransaction() {
        MyLogger.customLogger("SELLING COIN", Constants.INFO_LOGGER);
        Trader t = TraderRepository.getTrader(tw);

        while (!t.isBought(c)) {
            MyLogger.customLogger("Coin not bought by " + tw + " coin name " + c + " price " + q, Constants.DEBUG_LOGGER); 
            MyLogger.customLogger("Waiting for the trader to buy the coin!", Constants.INFO_LOGGER);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                MyLogger.customLogger("Error in selling coin", Constants.ERROR_LOGGER);
                return false;
            }
        }

        MyLogger.customLogger("Attempting to sell coin", Constants.INFO_LOGGER);
        if (CoinRepository.getInstance().updateVolume(c, q, "sell")) {
            MyLogger.customLogger("Coin was somehow sold", Constants.INFO_LOGGER);
            Double price = t.removeCoin(c, q);
            MyLogger.customLogger("Price per coin and quantity: " + price / q + " " + q, Constants.INFO_LOGGER);
            Double tmpProfit = (CoinRepository.getInstance().getCoinPrice(c) - price);
            MyLogger.customLogger("Selling price: " + CoinRepository.getInstance().getCoinPrice(c), Constants.INFO_LOGGER);
            Double netPL = t.getNetProfit() + tmpProfit;

            t.setNetProfit(netPL);
            return true;
        } else {
            MyLogger.customLogger("Something went wrong with selling", Constants.ERROR_LOGGER);
            return false;
        }
    }
}