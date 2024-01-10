package org.handson.question3;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static final double coverage = Constants.BRONZEPLAN;
    private static final double premium = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(coverage,premium);
    }
    /**
     * Computes the monthly premium based on the given salary.
     *
     * @param  salary  the salary of the individual
     * @return         the computed monthly premium
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premium;
    }
}
