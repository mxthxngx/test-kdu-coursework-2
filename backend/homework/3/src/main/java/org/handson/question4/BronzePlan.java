package org.handson.question4;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static  double COVERAGE_BRONZE_PLAN = Constants.BRONZEPLAN;
    private static  double PREMIUM_BRONZE_PLAN = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(COVERAGE_BRONZE_PLAN,PREMIUM_BRONZE_PLAN);
    }

    /**
     * Computes the monthly PREMIUM_BRONZE_PLAN based on the given salary, age, and smoking status.
     *
     * @param salary   the salary of the individual
     * @param age      the age of the individual
     * @param smoking  indicates if the individual is a smoker
     * @return         the computed monthly PREMIUM_BRONZE_PLAN
     */
    @Override
    double computeMonthlyPREMIUM_PLATINUM_PLAN(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_BRONZE_PLAN+insuranceBrand.computeMonthlyPREMIUM_PLATINUM_PLAN(this,age,smoking);
    }
}
