package org.handson.question4;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static  double coverageBronzePlan = Constants.BRONZEPLAN;
    private static  double premiumBronzePlan = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(coverageBronzePlan,premiumBronzePlan);
    }

    /**
     * Computes the monthly premiumBronzePlan based on the given salary, age, and smoking status.
     *
     * @param salary   the salary of the individual
     * @param age      the age of the individual
     * @param smoking  indicates if the individual is a smoker
     * @return         the computed monthly premiumBronzePlan
     */
    @Override
    double computeMonthlypremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*premiumBronzePlan+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
}
