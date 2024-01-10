package org.handson.question3;

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
     * A description of the entire Java function.
     *
     * @param  salary  description of parameter
     * @return         description of return value
     */

    @Override
    double computeMonthlyPremium(double salary) {
        return salary*PREMIUM_PLATINUM_PLAN;
    }
}
