package org.handson.question3;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double COVERAGE_SILVER_PLAN = Constants.SILVERPLAN;

    private static final double PREMIUM_PLATINUM_PLAN = Constants.SILVERPLAN_PREMIUM;
    @Override
    public double getCoverage()
    {
        return COVERAGE_SILVER_PLAN;
    }

    /**
     * Computes the monthly PREMIUM_PLATINUM_PLAN based on the given salary.
     *
     * @param  salary	the salary of the individual
     * @return         	the computed monthly PREMIUM_PLATINUM_PLAN
     */
    @Override
    double computeMonthlyPremium(double salary) {
        return salary*PREMIUM_PLATINUM_PLAN;
    }
}
