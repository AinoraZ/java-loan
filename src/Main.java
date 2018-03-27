import Accounting.*;
import Graphics.*;
import Graphics.Window;

import javax.swing.*;
import java.awt.event.*;

/**
 * Main class of the program
 *
 * @author Ainoras Žukauskas
 * @version 2018-03-19
 */

public class Main {
    public static void main(String args[]){
        createGUI();
    }

    /**
     * Sets up and creates the GUI of the program
     */
    private static void createGUI(){
        Window win = new Window();
        DataDump dump = new DataDump();

        JFormattedTextField amount = win.addLabelFieldPair("Amount", 2, 0);
        JFormattedTextField rate = win.addLabelFieldPair("Rate", 2, 0, 100);
        JFormattedTextField years = win.addLabelFieldPair("Years", -1, 0, 999);
        JFormattedTextField months = win.addLabelFieldPair("Months", -1, 0, 11);

        JRadioButton linear = new JRadioButton("Linear", true);
        JRadioButton annuity = new JRadioButton("Annuity");
        ButtonGroup methods = win.addToEndPane(linear, annuity);

        JButton calculate = new JButton("Calculate");
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loan loan;
                dump.reset();
                double tempAmount = (double) amount.getValue();
                double tempRate = (double) rate.getValue();
                int tempYears = Integer.parseInt(years.getText().replace(".", ""));
                int tempMonths = Integer.parseInt(months.getText().replace(".", ""));
                if(methods.isSelected(linear.getModel())){
                    loan = new LinearLoan(tempAmount, tempRate, tempYears, tempMonths);
                }
                else{
                    loan = new AnnuityLoan(tempAmount, tempRate, tempYears, tempMonths);
                }
                dump.addGeneralDate(String.format("Total: %.2f", loan.payForLoanTotal()));
                for(int x = 1; x <= loan.getConvertedMonths(); x++){
                    double repaid = loan.amountRepaid(x);
                    double total = (loan.payForLoanMonthly(x) > repaid)? loan.payForLoanMonthly(x): repaid;
                    double percentile = (total - repaid) > 0.0? total - repaid: 0.0;
                    double owned = loan.amountOwned(x);

                    String string = String.format("Month: %5d Repaid: %-8.2f  Interest: %-8.2f Total: %-8.2f  Owned: %-15.2f\n", x, repaid, percentile, total, owned);

                    dump.addMonthly(x, repaid, percentile, total, loan.amountOwned(x), string);
                }
                dump.addMonthlyTotal();
                dump.showUI();
            }
        });

        win.addToEndPane(calculate);
        win.showUI();
    }

}
