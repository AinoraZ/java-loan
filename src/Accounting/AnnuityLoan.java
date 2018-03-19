package Accounting;

/**
 * AnnuityLoan class responsible for all annuity loan calculations.
 *
 * @author Ainoras Žukauskas
 * @version 2018-03-19
 */

public class AnnuityLoan extends Loan {

    /**
     * The Constructor of AnnuityLoan.
     * @param loanWanted double value of the amount of money wanted
     * @param yearlyPercent double value of the yearly interest rate
     * @param loanYears int value of the loan duration in years
     * @param loanMonths int value of the loan duration in months
     */
    public AnnuityLoan(double loanWanted, double yearlyPercent, int loanYears, int loanMonths){
        super.loanWanted = loanWanted;
        super.yearlyPercent = yearlyPercent;
        super.loanMonths = loanMonths;
        super.loanYears = loanYears;

        super.calculateConvertedMonths();

        super.monthlyPercent = yearlyPercent / 12.0 / 100.00;
        super.installment = (monthlyPercent * loanWanted) / (1 - (Math.pow(1.0 + super.monthlyPercent, -super.convertedMonths)));
    }

    /**
     * Calculates the amount of money to pay for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the the amount needed to pay for given month
     */
    public double payForLoanMonthly(int month){
        return moneyRound(super.installment, 2);
    }

    /**
     * Calculates the amount of money to pay for a given mont
     * @return int value of the total loan period in months
     */
    public int getConvertedMonths() {
        return convertedMonths;
    }

    /**
     * Calculates the amount of money repaid for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the amount repaid for a given month
     */
    public double amountRepaid(int month){
        return amountOwned(month - 1) - amountOwned(month);
    }

    /**
     * Calculates the amount of money still needed to be repaid for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the amount still needed to be repaid for a given month
     */
    public double amountOwned(int month){
        double owned;
        int months = convertedMonths;

        if(month >= months)
            return 0;

        owned = Math.pow(1.0 + monthlyPercent, month) * super.loanWanted -
                ((Math.pow(1.0 + monthlyPercent, month) - 1.0) / monthlyPercent) * payForLoanMonthly(month);


        return moneyRound(owned, 2);
    }

    /**
     * Calculates the amount of money needed to be paid throughout the loan period
     * @return double value rounded to 2 decimal places of the total amount needed to be paid throughout the loan period
     */
    public double payForLoanTotal(){
        double pay = 0.0;

        int months = convertedMonths;
        double loan = super.loanWanted;

        for(int x = 1; x <= convertedMonths; x++){
            pay += moneyRound((monthlyPercent * loan) / (1.0 - (Math.pow(1.0 + monthlyPercent, -months))),2);
        }

        return moneyRound(pay, 2);
    }
}
