package org.handson.question3;

import org.handson.question1and2.Constants;

public class GoldPlan extends HealthInsurancePlan {
    private static final double COVERAGE = Constants.GOLDPLAN;
    private static final double PREMIUM = Constants.GOLDPLAN_PREMIUM;
    public GoldPlan() {
        super(COVERAGE,PREMIUM);
    }
    /**
     * Computes the monthly PREMIUM based on the given salary.
     *
     * @param  salary  the salary used to calculate the monthly PREMIUM
     * @return         the computed monthly PREMIUM
     */
    @Override
    double computeMonthlyPREMIUM(double salary) {
        return salary*PREMIUM;
    }
}
