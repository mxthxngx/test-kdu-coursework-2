package org.handson.question4;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double COVERAGE_PLATINUM_PLAN = Constants.PLATINUMPLAN;
    private static final double PREMIUM_PLATINUM_PLANPlatinumPremium = Constants.PLATINUMPLAN_PREMIUM;
    public double getCoverage()
    {
        return COVERAGE_PLATINUM_PLAN;
    }
    /**
     * Computes the monthly PREMIUM_PLATINUM_PLANPlatinumPremium for the insurance based on the salary, age, and smoking status.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly PREMIUM_PLATINUM_PLANPlatinumPremium
     */
    @Override
    double computeMonthlyPREMIUM_PLATINUM_PLAN(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_PLATINUM_PLANPlatinumPremium+insuranceBrand.computeMonthlyPREMIUM_PLATINUM_PLAN(this,age,smoking);

    }
    }

