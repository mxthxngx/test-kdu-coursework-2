package org.handson.question3;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static final double coverageBronzePlan = Constants.BRONZEPLAN;
    private static final double premiumBronzePlan = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(coverageBronzePlan,premiumBronzePlan);
    }
    /**
     * Computes the monthly premiumBronzePlan based on the given salary.
     *
     * @param  salary  the salary of the individual
     * @return         the computed monthly premiumBronzePlan
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premiumBronzePlan;
    }
}
