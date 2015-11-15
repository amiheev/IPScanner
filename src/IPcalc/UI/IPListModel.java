package IPcalc.UI;

import IPcalc.IPRangeChecker;
import IPcalc.IPRangeCollector;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 15.11.2015.
 */
public class IPListModel extends DefaultListModel {
    private List<String> ipList;
    private IPRangeChecker checker;
    private IPRangeCollector collector;
    public IPListModel(){
        this.ipList = new ArrayList<>();
    }

    public List<String> fillIPList(String ip1, String ip2) throws IOException{
        ipList.clear();
        checker = new IPRangeChecker();
        collector = new IPRangeCollector(ip1, ip2);
        ipList.addAll(checker.checkIPRange(collector.createIPRangeList()));
        return ipList;
    }

    public List<String> getIpList() {
        return this.ipList;
    }
    public void writeIPToFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String str : ipList){
                fileWriter.write(str + "\n");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public int validateIP(String ip1, String ip2){
        int result = 0;
        if (ip1.equals("...")  || ip2.equals("...") ){
            result=1;
        }else {
            String arr1[] = ip1.split("\\.");
            String arr2[] = ip2.split("\\.");
            if (Integer.parseInt(arr1[0]) <= Integer.parseInt(arr2[0]) ){
                if (Integer.parseInt(arr1[1]) <= Integer.parseInt(arr2[1])){
                    if (Integer.parseInt(arr1[2]) <= Integer.parseInt(arr2[2])){
                        if (Integer.parseInt(arr1[3]) <= Integer.parseInt(arr2[3])){
                        }else result=-1;
                    }else result=-1;
                }else result=-1;
            }else result= -1;
        }
        return result;
    }
}
