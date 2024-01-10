package org.handson.question4;

public class BlueCrossBlueShield implements InsuranceBrand{
    /**
     * Computes the monthly PREMIUM_PLATINUM_PLAN for a given health insurance plan, age, and smoking status.
     *
     * @param  insurancePlan  the health insurance plan
     * @param  age            the age of the individual
     * @param  smoking        the smoking status of the individual
     * @return                the computed monthly PREMIUM_PLATINUM_PLAN
     */
    public double computeMonthlyPREMIUM_PLATINUM_PLAN(HealthInsurancePlan insurancePlan, int age, boolean smoking)
    {
        Double coverage = insurancePlan.getCoverage();
        double PREMIUM_PLATINUM_PLAN = 0;
        switch(coverage.toString())
        {
           case "0.9"->{
                     PREMIUM_PLATINUM_PLAN+=age>55?200:0;
                     PREMIUM_PLATINUM_PLAN+=smoking?100:0;
           }
            case "0.8"->{
                PREMIUM_PLATINUM_PLAN+=age>55?150:0;
                PREMIUM_PLATINUM_PLAN+=smoking?90:0;

            }
            case "0.7"->{
                PREMIUM_PLATINUM_PLAN+=age>55?100:0;
                PREMIUM_PLATINUM_PLAN+=smoking?80:0;

            }
            case "0.6"->{
                PREMIUM_PLATINUM_PLAN+=age>55?50:0;
                PREMIUM_PLATINUM_PLAN+=smoking?70:0;

            }
            default -> PREMIUM_PLATINUM_PLAN=0;

        }
        return PREMIUM_PLATINUM_PLAN;
    }
}
