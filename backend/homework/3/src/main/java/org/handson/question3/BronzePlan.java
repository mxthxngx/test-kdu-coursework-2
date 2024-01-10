package org.handson.question3;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static final double COVERAGE_BRONZE_PLAN = Constants.BRONZEPLAN;
    private static final double PREMIUM_BRONZE_PLAN = Constants.BRONZEPLAN_PREMIUM;

    @Override
    public double getCoverage()
    {
        return COVERAGE_BRONZE_PLAN;
    }

    /**
     * Computes the monthly PREMIUM_BRONZE_PLAN based on the given salary.
     *
     * @param  salary  the salary of the individual
     * @return         the computed monthly PREMIUM_BRONZE_PLAN
     */
    @Override
    double computeMonthlyPremium(double salary) {
        return salary*PREMIUM_BRONZE_PLAN;
    }
}
