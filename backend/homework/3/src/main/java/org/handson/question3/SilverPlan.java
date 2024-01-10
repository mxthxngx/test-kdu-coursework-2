package org.handson.question3;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double coverageSilver = Constants.SILVERPLAN;
    private static final double premiumSilver = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(coverageSilver,premiumSilver);
    }
    /**
     * Computes the monthly premiumSilver based on the given salary.
     *
     * @param  salary	the salary of the individual
     * @return         	the computed monthly premiumSilver
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premiumSilver;
    }
}
