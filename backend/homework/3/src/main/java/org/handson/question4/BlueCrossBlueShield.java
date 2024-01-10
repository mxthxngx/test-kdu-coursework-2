package org.handson.question4;

public class BlueCrossBlueShield implements InsuranceBrand{
    /**
     * Computes the monthly PREMIUM for a given health insurance plan, age, and smoking status.
     *
     * @param  insurancePlan  the health insurance plan
     * @param  age            the age of the individual
     * @param  smoking        the smoking status of the individual
     * @return                the computed monthly PREMIUM
     */
    public double computeMonthlyPREMIUM(HealthInsurancePlan insurancePlan, int age, boolean smoking)
    {
        Double COVERAGE = insurancePlan.getCOVERAGE();
        double PREMIUM = 0;
        switch(COVERAGE.toString())
        {
           case "0.9"->{
               if(age>55)
               {
                   PREMIUM+=200;
               }
               if(smoking)
               {
                   PREMIUM+=100;
               }
           }
            case "0.8"->{
                if(age>55)
                {
                    PREMIUM+=150;
                }
                if(smoking)
                {
                    PREMIUM+=90;
                }
            }
            case "0.7"->{
                if(age>55)
                {
                    PREMIUM+=100;
                }
                if(smoking)
                {
                    PREMIUM+=80;
                }
            }
            case "0.6"->{
                if(age>55)
                {
                    PREMIUM+=50;
                }
                if(smoking)
                {
                    PREMIUM+=70;
                }
            }
            default -> PREMIUM=0;

        }
        return PREMIUM;
    }
}
