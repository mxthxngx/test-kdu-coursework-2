package org.handson.question3;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static final double COVERAGE = Constants.BRONZEPLAN;
    private static final double PREMIUM = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(COVERAGE,PREMIUM);
    }
    /**
     * Computes the monthly PREMIUM based on the given salary.
     *
     * @param  salary  the salary of the individual
     * @return         the computed monthly PREMIUM
     */
    @Override
    double computeMonthlyPREMIUM(double salary) {
        return salary*PREMIUM;
    }
}
