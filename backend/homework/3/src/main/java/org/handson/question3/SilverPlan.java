package org.handson.question3;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double coverage = Constants.SILVERPLAN;
    private static final double premium = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(coverage,premium);
    }
    /**
     * Computes the monthly premium based on the given salary.
     *
     * @param  salary	the salary of the individual
     * @return         	the computed monthly premium
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premium;
    }
}
