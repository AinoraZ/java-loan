package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataDump extends JFrame {
    JPanel mainPane;
    JScrollPane scrollPane;
    JPanel connectPane;
    JPanel topPane;
    JPanel monthPane;
    JTextArea monthText;
    List<String> dump;

    public DataDump(){
        reset();
    }

    public void reset(){
        this.getContentPane().removeAll();
        this.repaint();

        mainPane = new JPanel(new BorderLayout());
        connectPane = new JPanel(new BorderLayout());
        topPane = new JPanel(new GridLayout(0, 2));
        monthPane = new JPanel(new GridLayout(0, 5));
        monthText = new JTextArea();
        dump = new ArrayList<String>();

        JLabel label = new JLabel("Month");
        monthPane.add(label);
        label = new JLabel("Repaid");
        monthPane.add(label);
        label = new JLabel("Interest");
        monthPane.add(label);
        label = new JLabel("Total");
        monthPane.add(label);
        label = new JLabel("Owned");
        monthPane.add(label);

        connectPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPane.add(topPane, BorderLayout.PAGE_START);
        connectPane.add(monthPane, BorderLayout.PAGE_END);
        scrollPane = new JScrollPane(connectPane);
    }

    public void addGeneralDate(String text){
        JLabel label = new JLabel(text);
        topPane.add(label);


        JButton button = new JButton("Export");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("."));
                fc.setDialogTitle("Save Dump");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setAcceptAllFileFilterUsed(false);

                if (fc.showOpenDialog(mainPane) == JFileChooser.APPROVE_OPTION) {
                    try{
                        FileWriter fileWriter = new FileWriter(fc.getSelectedFile() + "/dump.txt");
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        for(String str : dump){
                            printWriter.print(str);
                        }
                        printWriter.close();

                        JOptionPane.showMessageDialog(null,"Dump successful");

                    }
                    catch(Exception err){
                        JOptionPane.showMessageDialog(null,"Dump error");
                        err.printStackTrace();
                    }
                }
            }
        });
        topPane.add(button);
    }

    public void addMonthly(int month, double repaid, double percentile, double total, double owned, String strDump){
        String string = String.format("%5d", month);
        JLabel label = new JLabel(string);
        monthPane.add(label);

        string = String.format("%.2f", repaid);
        label = new JLabel(string);
        monthPane.add(label);

        string = String.format("%.2f", percentile);
        label = new JLabel(string);
        monthPane.add(label);

        string = String.format("%.2f", total);
        label = new JLabel(string);
        monthPane.add(label);

        string = String.format("%.2f", owned);
        label = new JLabel(string);
        monthPane.add(label);

        dump.add(strDump);
    }

    public void showUI(){
        mainPane.add(scrollPane);
        this.add(mainPane);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        Rectangle r = this.getBounds();
        this.setSize(r.width + 50, 580);

        this.setVisible(true);
        setTitle("Data Dump");
    }
}
