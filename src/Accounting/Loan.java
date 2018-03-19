package Accounting;

/**
 * Loan class used as base for all other Loan types.
 *
 * @author Ainoras Žukauskas
 * @version 2018-03-19
 */

public class Loan {
    /**
     * Storage of the loan amount
     */
    double loanWanted;

    /**
     * Storage of the yearly loan rate
     */
    double yearlyPercent;

    /**
     * Storage of the loan period in years
     */
    int loanYears;

    /**
     * Storage of the partial loan period in months
     */
    int loanMonths;

    /**
     * Storage of the monthly loan rate
     */
    double monthlyPercent;

    /**
     * Storage of the monthly loan installment
     */
    double installment;

    /**
     * Storage of the total loan period in months
     */
    int convertedMonths;

    /**
     * Calculates the amount of money to pay for a given month
     */
    public void calculateConvertedMonths(){
        convertedMonths = loanYears * 12 + loanMonths;
    }

    /**
     * Calculates the installment of a loan
     */
    void calculateInstallment(){
        installment = moneyRound(loanWanted / (double) convertedMonths, 2);
    }

    /**
     * Calculates the amount of money to pay for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the the amount needed to pay for given month
     */
    public double payForLoanMonthly(int month){
        return 0;
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
        return installment;
    }

    /**
     * Calculates the amount of money still needed to be repaid for a given month
     * @param month int value of a given month
     * @return double value rounded to 2 decimal places of the amount still needed to be repaid for a given month
     */
    public double amountOwned(int month){
        return 0;
    }

    /**
     * Calculates the amount of money needed to be paid throughout the loan period
     * @return double value rounded to 2 decimal places of the total amount needed to be paid throughout the loan period
     */
    public double payForLoanTotal(){
        return 0;
    }

    /**
     * Getter for loanWanted
     * @return double value of loanWanted
     */
    public double getLoanWanted(){
        return loanWanted;
    }

    /**
     * Getter for loanYears
     * @return int value of loanYears
     */
    public int getLoanYears(){
        return loanYears;
    }

    /**
     * Getter for loanMonths
     * @return int value of loanMonths
     */
    public int getLoanMonths(){
        return loanMonths;
    }

    /**
     * Getter for yearlyPercent
     * @return double value of yearlyPercent
     */
    public double getYearlyPercent(){
        return yearlyPercent;
    }

    /**
     * Setter for loanWanted
     * @param loanWanted double value to be set
     */
    public void setLoanWanted(double loanWanted){
        this.loanWanted = loanWanted;
    }

    /**
     * Setter for loanYears
     * @param loanYears int value to be set
     */
    public void setLoanYears(int loanYears){
        this.loanYears = loanYears;
    }

    /**
     * Setter for loanMonths
     * @param loanMonths int value to be set
     */
    public void setLoanMonths(int loanMonths){
        this.loanMonths = loanMonths;
    }

    /**
     * Setter for yearlyPercent
     * @param yearlyPercent double value to be set
     */
    public void setYearlyPercent(double yearlyPercent){
        this.yearlyPercent = yearlyPercent;
    }

    /**
     * Rounds given amount to a given precision
     * @param amount double value of number to be rounded
     * @param places places to round
     * @return double value of the rounded amount
     */
    public double moneyRound(double amount, int places){
        double multiplier = Math.pow(10, places);
        amount = Math.round(amount * multiplier) / multiplier;
        return amount;
    }
}
