package org.handson.components;

import org.springframework.beans.factory.annotation.Autowired;

public enum Tyre {
    MRF("MRF",300),
    BRIGESTONE("Brigestone",3000);

    private String brand;
    private double price;
    Tyre(String brand, double price)
    {
        this.brand = brand;
        this.price = price;
    }
    /**
     * Retrieves the price of the object.
     *
     * @return         	the price of the object
     */
    @Autowired
    public double getPrice()
    {
        return this.price;
    }
    /**
     * Get the name of the object.
     *
     * @return the brand name
     */
    public String getName()
    {
        return this.brand;
    }
@Autowired
    public void setPrice(double price)
    {
        this.price = price;
    }
}
