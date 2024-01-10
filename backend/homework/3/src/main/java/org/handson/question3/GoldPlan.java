package org.handson.question3;

import org.handson.question1and2.Constants;

public class GoldPlan extends HealthInsurancePlan {
        private static final double PREMIUM_GOLD_PLAN = Constants.GOLDPLAN_PREMIUM;

    /**
     * Computes the monthly PREMIUM_GOLD_PLAN based on the given salary.
     *
     * @param  salary  the salary used to calculate the monthly PREMIUM_GOLD_PLAN
     * @return         the computed monthly PREMIUM_GOLD_PLAN
     */
    @Override
    double computeMonthlyPremium(double salary) {
        return salary*PREMIUM_GOLD_PLAN;
    }
}
