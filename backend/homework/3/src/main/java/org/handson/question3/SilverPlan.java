package org.handson.question3;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double COVERAGE = Constants.SILVERPLAN;
    private static final double PREMIUM = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(COVERAGE,PREMIUM);
    }
    /**
     * Computes the monthly PREMIUM based on the given salary.
     *
     * @param  salary	the salary of the individual
     * @return         	the computed monthly PREMIUM
     */
    @Override
    double computeMonthlyPREMIUM(double salary) {
        return salary*PREMIUM;
    }
}
