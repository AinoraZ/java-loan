package Accounting;

public class LinearLoan extends Loan{

    public LinearLoan(double loanWanted, double yearlyPercent, int loanYears, int loanMonths){
        this.loanWanted = loanWanted;
        this.yearlyPercent = yearlyPercent;
        this.loanMonths = loanMonths;
        this.loanYears = loanYears;

        this.calculateConvertedMonths();
        this.calculateInstallment();

        this.monthlyPercent = yearlyPercent / 12.0 / 100.00;
    }

    public double payForLoanMonthly(int month){

        double pay = 0.0;
        int months = this.convertedMonths;
        double loan = this.loanWanted;

        if(month > months)
            return 0;

        loan -= this.installment * month;
        pay = this.installment + (loan * monthlyPercent);

        return moneyRound(pay, 2);
    }

    public double amountRepaid(int month){
        return installment;
    }

    public int getConvertedMonths() {
        return convertedMonths;
    }

    public double amountOwned(int month){
        if(month >= this.convertedMonths)
            return 0;
        return moneyRound(this.loanWanted - ((double) month) * this.installment, 2);
    }

    public double payForLoanTotal(){
        double pay = 0.0;

        int months = this.convertedMonths;
        double loan = this.loanWanted;

        for(int x = 1; x <= this.convertedMonths; x++){
            //pay = (monthlyPercent * loan) / (1 - (Math.pow(1.0 + monthlyPercent, -months)));
            loan -= this.installment;
            pay += moneyRound(this.installment + (loan * this.monthlyPercent), 2);
            months--;
        }

        return pay;
    }
}
