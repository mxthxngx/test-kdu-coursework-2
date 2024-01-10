package org.handson.question3;

import org.handson.question1and2.Constants;

public class GoldPlan extends HealthInsurancePlan {
    private static final double coverageGoldPlan = Constants.GOLDPLAN;
    private static final double premiumGold = Constants.GOLDPLAN_PREMIUM;
    public GoldPlan() {
        super(coverageGoldPlan,premiumGold);
    }
    /**
     * Computes the monthly premiumGold based on the given salary.
     *
     * @param  salary  the salary used to calculate the monthly premiumGold
     * @return         the computed monthly premiumGold
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premiumGold;
    }
}
