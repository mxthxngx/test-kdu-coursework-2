package org.handson.question4;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double COVERAGE_PLATINUM_PLAN = Constants.PLATINUMPLAN;
    private static final double PREMIUM_PLATINUM_PLAN = Constants.PLATINUMPLAN_PREMIUM;
    @Override
public double getCoverage()
    {
        return COVERAGE_PLATINUM_PLAN;
    }
    /**
     * Computes the monthly PREMIUM_PLATINUM_PLAN for the insurance based on the salary, age, and smoking status.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly PREMIUM_PLATINUM_PLAN
     */
    @Override
    double computeMonthlyPremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_PLATINUM_PLAN+insuranceBrand.computeMonthlyPremium(this,age,smoking);

    }
    }

