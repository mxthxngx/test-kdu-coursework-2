package org.handson.question4;

import org.handson.question1and2.Constants;

public class GoldPlan extends HealthInsurancePlan {
    private static final double COVERAGE_GOLD_PLAN = Constants.GOLDPLAN;
    private static final double PREMIUM_GOLD_PLAN = Constants.GOLDPLAN_PREMIUM;
    @Override
public double getCoverage()
    {
        return COVERAGE_GOLD_PLAN;
    }
    /**
     * Computes the monthly PREMIUM_GOLD_PLAN for an insurance policy based on the salary, age, and smoking status of the policyholder.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly PREMIUM_GOLD_PLAN for the insurance policy
     */
    @Override
    double computeMonthlyPremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_GOLD_PLAN+insuranceBrand.computeMonthlyPremium(this,age,smoking);
    }
}
