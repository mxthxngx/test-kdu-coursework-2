package org.handson.question4;

public class BlueCrossBlueShield implements InsuranceBrand{
    /**
     * Computes the monthly premium for a given health insurance plan, age, and smoking status.
     *
     * @param  insurancePlan  the health insurance plan
     * @param  age            the age of the individual
     * @param  smoking        the smoking status of the individual
     * @return                the computed monthly premium
     */
    public double computeMonthlypremium(HealthInsurancePlan insurancePlan, int age, boolean smoking)
    {
        Double coverage = insurancePlan.getCoverage();
        double premium = 0;
        switch(coverage.toString())
        {
           case "0.9"->{
                     premium+=age>55?200:0;
                     premium+=smoking?100:0;
           }
            case "0.8"->{
                premium+=age>55?150:0;
                premium+=smoking?90:0;

            }
            case "0.7"->{
                premium+=age>55?100:0;
                premium+=smoking?80:0;

            }
            case "0.6"->{
                premium+=age>55?50:0;
                premium+=smoking?70:0;

            }
            default -> premium=0;

        }
        return premium;
    }
}
