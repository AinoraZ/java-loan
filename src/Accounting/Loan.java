package Accounting;

public class Loan {
    double loanWanted;
    double yearlyPercent;
    int loanYears;
    int loanMonths;
    double monthlyPercent;
    double installment;
    int convertedMonths;

    public void calculateConvertedMonths(){
        convertedMonths = loanYears * 12 + loanMonths;
    }

    void calculateInstallment(){
        installment = moneyRound(loanWanted / (double) convertedMonths, 2);
    }


    public double payForLoanMonthly(int month){
        return 0;
    }

    public int getConvertedMonths() {
        return convertedMonths;
    }

    public double amountRepaid(int month){
        return installment;
    }

    public double amountOwned(int month){
        return 0;
    }

    public double payForLoanTotal(){
        return 0;
    }

    public double getLoanWanted(){
        return loanWanted;
    }

    public int getLoanYears(){
        return loanYears;
    }

    public int getLoanMonths(){
        return loanMonths;
    }

    public double getYearlyPercent(){
        return yearlyPercent;
    }

    public void setLoanWanted(double loanWanted){
        this.loanWanted = loanWanted;
    }

    public void setLoanYears(int loanYears){
        this.loanYears = loanYears;
    }

    public void setLoanMonths(int loanMonths){
        this.loanMonths = loanMonths;
    }

    public void setYearlyPercent(double yearlyPercent){
        this.yearlyPercent = yearlyPercent;
    }

    public double moneyRound(double amount, int places){
        double multiplier = Math.pow(10, places);
        amount = Math.round(amount * multiplier) / multiplier;
        return amount;
    }
}
