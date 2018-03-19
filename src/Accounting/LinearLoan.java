package Accounting;

/**
 * LinearLoan class responsible for all Linear loan calculations.
 *
 * @author Ainoras Žukauskas
 * @version 2018-03-19
 */

public class LinearLoan extends Loan{
    private double loanWanted;

    /**
     * The Constructor of LinearLoan.
     * @param loanWanted double value of the amount of money wanted
     * @param yearlyPercent double value of the yearly interest rate
     * @param loanYears int value of the loan duration in years
     * @param loanMonths int value of the loan duration in months
     */
    public LinearLoan(double loanWanted, double yearlyPercent, int loanYears, int loanMonths){
        super.loanWanted = loanWanted;
        super.yearlyPercent = yearlyPercent;
        super.loanMonths = loanMonths;
        super.loanYears = loanYears;

        super.calculateConvertedMonths();
        super.calculateInstallment();

        super.monthlyPercent = yearlyPercent / 12.0 / 100.00;
    }

    /**
     * Calculates the amount of money to pay for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the the amount needed to pay for given month
     */
    public double payForLoanMonthly(int month){

        double pay = 0.0;
        int months = super.convertedMonths;
        this.loanWanted = super.loanWanted;

        if(month > months)
            return 0;

        this.loanWanted -= super.installment * month;
        pay = super.installment + (this.loanWanted * super.monthlyPercent);

        return moneyRound(pay, 2);
    }

    /**
     * Calculates the amount of money repaid for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the amount repaid for a given month
     */
    public double amountRepaid(int month){
        return super.installment;
    }

    /**
     * Calculates the amount of money to pay for a given mont
     * @return int value of the total loan period in months
     */
    public int getConvertedMonths() {
        return super.convertedMonths;
    }

    /**
     * Calculates the amount of money still needed to be repaid for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the amount still needed to be repaid for a given month
     */
    public double amountOwned(int month){
        if(month >= super.convertedMonths)
            return 0;
        return moneyRound(super.loanWanted - ((double) month) * super.installment, 2);
    }

    /**
     * Calculates the amount of money needed to be paid throughout the loan period
     * @return double value rounded to 2 decimal places of the total amount needed to be paid throughout the loan period
     */
    public double payForLoanTotal(){
        double pay = 0.0;
        this.loanWanted = super.loanWanted;

        for(int x = 1; x <= super.convertedMonths; x++){
            //pay = (monthlyPercent * loan) / (1 - (Math.pow(1.0 + monthlyPercent, -months)));
            this.loanWanted -= super.installment;
            pay += moneyRound(super.installment + (this.loanWanted * super.monthlyPercent), 2);
        }

        return pay;
    }
}
