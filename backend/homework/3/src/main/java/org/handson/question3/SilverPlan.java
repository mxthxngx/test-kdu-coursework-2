package org.handson.question3;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {

    private static final double PREMIUM_PLATINUM_PLANSilver = Constants.SILVERPLAN_PREMIUM;

    /**
     * Computes the monthly PREMIUM_PLATINUM_PLANSilver based on the given salary.
     *
     * @param  salary	the salary of the individual
     * @return         	the computed monthly PREMIUM_PLATINUM_PLANSilver
     */
    @Override
    double computeMonthlyPREMIUM_PLATINUM_PLAN(double salary) {
        return salary*PREMIUM_PLATINUM_PLANSilver;
    }
}
