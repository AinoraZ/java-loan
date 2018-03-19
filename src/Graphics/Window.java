package Graphics;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

/**
 * Window class responsible for the main program GUI interface window
 *
 * @author Ainoras Žukauskas
 * @version 2018-03-19
 */

public class Window extends JFrame{
    JPanel mainPane = new JPanel(new BorderLayout());
    JPanel labelPane = new JPanel(new GridLayout(0, 1));
    JPanel fieldPane = new JPanel(new GridLayout(0, 1));
    JPanel endPane = new JPanel(new GridLayout(0, 2));

    /**
     * The Constructor of Window.
     */
    public Window(){
        mainPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPane.add(labelPane, BorderLayout.CENTER);
        mainPane.add(fieldPane, BorderLayout.LINE_END);
        mainPane.add(endPane, BorderLayout.PAGE_END);
    }

    /* Add To Label Pane */
    /**
     * Add Elements to the left of the window
     * @param obj JFormattedTextField instance
     */
    public void addToLabelPane(JFormattedTextField obj){
        labelPane.add(obj);
    }

    /**
     * Add Elements to the left of the window
     * @param obj JTextField instance
     */
    public void addToLabelPane(JTextField obj){
        labelPane.add(obj);
    }

    /**
     * Add Elements to the left of the window
     * @param obj JLabel instance
     */
    public void addToLabelPane(JLabel obj){
        labelPane.add(obj);
    }
    /* Add To Label Pane */

    /* Add To Field Pane */
    /**
     * Add Elements to the right of the window
     * @param obj JFormattedTextField instance
     */
    public void addToFieldPane(JFormattedTextField obj){
        fieldPane.add(obj);
    }

    /**
     * Add Elements to the right of the window
     * @param obj JTextField instance
     */
    public void addToFieldPane(JTextField obj){
        fieldPane.add(obj);
    }

    /**
     * Add Elements to the right of the window
     * @param obj JLabel instance
     */
    public void addToFieldPane(JLabel obj){
        fieldPane.add(obj);
    }

    /* Add To Field Pane */

    /**
     * Add Elements to the end of the window
     * @param obj JButton instance
     */
    public void addToEndPane(JButton obj){
        endPane.add(obj);
    }

    /**
     * Add Radio Buttons to one group and to the end of the window
     * @param objs any number of JRadioButton instances
     * @return ButtonGroup of the radio buttons
     */
    public ButtonGroup addToEndPane(JRadioButton... objs){
        if(objs.length == 0)
            return null;

        ButtonGroup buttons = new ButtonGroup();

        for(JRadioButton obj: objs){
            buttons.add(obj);
            endPane.add(obj);
        }
        return buttons;
    }

    /**
     * Completes GUI set up and makes GUI visible.
     */
    public void showUI(){
        this.add(mainPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        Rectangle r = this.getBounds();
        this.setSize(r.width + 20, r.height);

        this.setVisible(true);
        setTitle("Loan calculator");
    }

    public JFormattedTextField addLabelFieldPair(String labelStr, int precision, int... maxMin){
        JLabel label = new JLabel(labelStr);

        NumberFormat nFormat = NumberFormat.getNumberInstance();
        double min = (maxMin.length == 1)? maxMin[0] : 0;
        double max = (maxMin.length == 2)? maxMin[1] : -1;
        NumberFormatter nFormatter;
        if(precision >= 0) {
            nFormat.setParseIntegerOnly(false);
            nFormat.setMaximumFractionDigits(precision);
            nFormat.setMinimumFractionDigits(precision);

            nFormatter = new NumberFormatter(nFormat);
            nFormatter.setMinimum(min);

            if(max != -1.0)
                nFormatter.setMaximum(max);
        }
        else{
            nFormat.setParseIntegerOnly(true);

            nFormatter = new NumberFormatter(nFormat);
            nFormatter.setMinimum((int) min);

            if((int) max != -1)
                nFormatter.setMaximum((int) max);
        }


        JFormattedTextField field = new JFormattedTextField(nFormatter);
        field.setValue(min);

        field.setColumns(10);
        label.setLabelFor(field);

        this.addToLabelPane(label);
        this.addToFieldPane(field);

        return field;
    }
}
