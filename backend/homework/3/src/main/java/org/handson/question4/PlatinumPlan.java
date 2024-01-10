package org.handson.question4;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double coveragePlatinumPlan = Constants.PLATINUMPLAN;
    private static final double premiumPlatinumPremium = Constants.PLATINUMPLAN_PREMIUM;
    public PlatinumPlan() {
        super(coveragePlatinumPlan,premiumPlatinumPremium);
    }

    /**
     * Computes the monthly premiumPlatinumPremium for the insurance based on the salary, age, and smoking status.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly premiumPlatinumPremium
     */
    @Override
    double computeMonthlypremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*premiumPlatinumPremium+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
    }

