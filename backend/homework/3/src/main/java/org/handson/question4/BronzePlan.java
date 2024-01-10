package org.handson.question4;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static final double COVERAGE_BRONZE_PLAN = Constants.BRONZEPLAN;
    private static final double PREMIUM_BRONZE_PLAN = Constants.BRONZEPLAN_PREMIUM;

    @Override
public double getCoverage()
    {
        return COVERAGE_BRONZE_PLAN;
    }

    /**
     * Computes the monthly PREMIUM_BRONZE_PLAN based on the given salary, age, and smoking status.
     *
     * @param salary   the salary of the individual
     * @param age      the age of the individual
     * @param smoking  indicates if the individual is a smoker
     * @return         the computed monthly PREMIUM_BRONZE_PLAN
     */
    @Override
   public double computeMonthlyPremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_BRONZE_PLAN+insuranceBrand.computeMonthlyPremium(this,age,smoking);
    }
}
