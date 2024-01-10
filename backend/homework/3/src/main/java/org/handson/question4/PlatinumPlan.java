package org.handson.question4;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double coverage = Constants.PLATINUMPLAN;
    private static final double premium = Constants.PLATINUMPLAN_PREMIUM;
    public PlatinumPlan() {
        super(coverage,premium);
    }

    /**
     * Computes the monthly premium for the insurance based on the salary, age, and smoking status.
     *
     * @param  salary   the salary of the policyholder
     * @param  age      the age of the policyholder
     * @param  smoking  true if the policyholder is a smoker, false otherwise
     * @return          the computed monthly premium
     */
    @Override
    double computeMonthlypremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*premium+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
    }

