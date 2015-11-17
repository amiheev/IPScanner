package IPcalc.UI;

import IPcalc.IIPConverter;
import IPcalc.IPRangeChecker;
import IPcalc.IPRangeCollector;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 15.11.2015.
 */
public class IPListModel extends DefaultListModel implements IIPConverter{
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
    public int validateIP(String ip1, String ip2) throws IOException{

        int result = 0;
        if (ip1.equals("...")  || ip2.equals("...") ){
            result=1;
        }else {
            InetAddress adr1 = InetAddress.getByName(ip1);
            InetAddress adr2 = InetAddress.getByName(ip2);
            long a = ipAddressToLongConverter(adr1);
            long b = ipAddressToLongConverter(adr2);
            if (a <= b) {

            }else result=-1;
        }
        return result;
    }
    @Override
    public long ipAddressToLongConverter(InetAddress inetAddress) {
        byte iab[] = inetAddress.getAddress();
        int i0 = iab[0]; if (i0<0) i0+=256;
        int i1 = iab[1]; if (i1<0) i1+=256;
        int i2 = iab[2]; if (i2<0) i2+=256;
        int i3 = iab[3]; if (i3<0) i3+=256;
        return (FIRST_OCTET *i0)+(SECOND_OCTET *i1)+(THIRD_OCTET *i2)+i3;
    }

    @Override
    public String longToStringIPConvertation(long ip) {
        return null;
    }
}
