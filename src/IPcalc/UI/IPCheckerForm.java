package IPcalc.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alexey on 14.11.2015.
 */
public class IPCheckerForm  extends JFrame{
    private JPanel IPPanel;
    private JTextField ipFirstText1;
    private JTextField ipFirstText2;
    private JTextField ipFirstText3;
    private JTextField ipFirstText4;
    private JTextField ipSecondText1;
    private JTextField ipSecondText2;
    private JTextField ipSecondText3;
    private JTextField ipSecondText4;
    private JTextField firstIP;
    private JButton checkButton;
    private JList ipAddressList;
    private JTextField secondIP;
    private JScrollPane scrollPane;
    private JButton saveButton;
    private JLabel saveResult;



    public IPCheckerForm(){}

    public void createUI(){
        setVisible(true);
        final IPListModel model = new IPListModel();
        ipAddressList.setModel(model);
        scrollPane.setViewportView(ipAddressList);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(IPPanel);
        firstIP.setForeground(Color.RED);
        secondIP.setForeground(Color.RED);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (saveResult.getText().length() > 0) {
                        saveResult.setText("");
                    }
                    model.removeAllElements();
                    String ip1 = ipFirstText1.getText() + "." + ipFirstText2.getText() + "." + ipFirstText3.getText() + "." + ipFirstText4.getText();
                    String ip2 = ipSecondText1.getText() + "." + ipSecondText2.getText() + "." + ipSecondText3.getText() + "." + ipSecondText4.getText();
                    if (model.validateIP(ip1, ip2) == 0){

                        for (String str : model.fillIPList(ip1, ip2)) {
                            model.addElement(str);
                        }
                    }else if (model.validateIP(ip1,ip2) == 1){
                        saveResult.setText("Error!. Please input correct ip address");
                    }else saveResult.setText("first ip address should be less then second");
                }catch (IOException io){
                    io.printStackTrace();
                    saveResult.setText("incorrect ip address");
                }
            }
        });



        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getIpList().size() == 0 || model.getIpList().get(0).contains("Nothing is Reachable")){
                    saveResult.setText("Nothing to save");
                }else {
                    File file = new File("IP List") ;
                    if (file.exists()){
                        JOptionPane optionPane = new JOptionPane();
                        int value =optionPane.showConfirmDialog(IPCheckerForm.this, "File is already exist, update it?");
                        if (value == JOptionPane.YES_OPTION){
                            model.writeIPToFile(file);
                            saveResult.setText("File was successfully updated");
                        }else if (value == JOptionPane.NO_OPTION){
                        }
                    }else {
                        model.writeIPToFile(file);
                    }
                }
            }
        });
    }
}


