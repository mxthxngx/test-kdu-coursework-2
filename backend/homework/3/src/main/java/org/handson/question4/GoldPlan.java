package org.handson.question4;

import org.handson.question1and2.Constants;

public class GoldPlan extends HealthInsurancePlan {
    private static final double coverageGoldPlan = Constants.GOLDPLAN;
    private static final double premiumGoldPlan = Constants.GOLDPLAN_PREMIUM;
    public GoldPlan() {
        super(coverageGoldPlan,premiumGoldPlan);
    }

    /**
     * Computes the monthly premiumGoldPlan for an insurance policy based on the salary, age, and smoking status of the policyholder.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly premiumGoldPlan for the insurance policy
     */
    @Override
    double computeMonthlypremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*premiumGoldPlan+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
}
