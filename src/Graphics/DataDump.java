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
    JPanel topPane;
    JPanel monthPane;
    JPanel monthHeader;
    List<String> dump;
    JPanel topConnect;

    public DataDump(){
        reset();
    }

    public void reset(){
        this.getContentPane().removeAll();
        this.repaint();

        mainPane = new JPanel(new BorderLayout());
        topPane = new JPanel(new GridLayout(0, 2));
        topConnect = new JPanel(new BorderLayout());
        monthPane = new JPanel(new GridLayout(0, 5));
        monthHeader = new JPanel(new GridLayout(0, 5));
        dump = new ArrayList<String>();

        JLabel label = new JLabel("Month");
        monthHeader.add(label);
        label = new JLabel("Repaid");
        monthHeader.add(label);
        label = new JLabel("Interest");
        monthHeader.add(label);
        label = new JLabel("Total");
        monthHeader.add(label);
        label = new JLabel("Owned");
        monthHeader.add(label);

        monthPane.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));

        topConnect.add(topPane, BorderLayout.PAGE_START);
        topConnect.add(monthHeader, BorderLayout.PAGE_END);
        mainPane.add(topConnect, BorderLayout.PAGE_START);
        scrollPane = new JScrollPane(monthPane);
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
        if(r.height > 560)
            r.height = 560;
        this.setSize(r.width + 50, r.height);

        this.setVisible(true);
        setTitle("Data Dump");
    }
}
