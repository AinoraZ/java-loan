package Accounting;

public class AnnuityLoan extends Loan {
    public AnnuityLoan(double loanWanted, double yearlyPercent, int loanYears, int loanMonths){
        this.loanWanted = loanWanted;
        this.yearlyPercent = yearlyPercent;
        this.loanMonths = loanMonths;
        this.loanYears = loanYears;

        this.calculateConvertedMonths();

        this.monthlyPercent = yearlyPercent / 12.0 / 100.00;
        this.installment = (monthlyPercent * loanWanted) / (1 - (Math.pow(1.0 + this.monthlyPercent, -this.convertedMonths)));
    }

    public double payForLoanMonthly(int month){
        return moneyRound(this.installment, 2);
    }

    public int getConvertedMonths() {
        return convertedMonths;
    }

    public double amountRepaid(int month){
        return amountOwned(month - 1) - amountOwned(month);
    }

    public double amountOwned(int month){
        double owned = 0.0;
        int months = convertedMonths;
        double loan = this.loanWanted;

        if(month >= months)
            return 0;

        owned = Math.pow(1.0 + monthlyPercent, month) * loan -
                ((Math.pow(1.0 + monthlyPercent, month) - 1.0) / monthlyPercent) * payForLoanMonthly(month);


        return moneyRound(owned, 2);
    }

    public double payForLoanTotal(){
        double pay = 0.0;

        int months = convertedMonths;
        double loan = this.loanWanted;

        for(int x = 1; x <= convertedMonths; x++){
            pay += moneyRound((monthlyPercent * loan) / (1.0 - (Math.pow(1.0 + monthlyPercent, -months))),2);
        }

        return moneyRound(pay, 2);
    }
}
