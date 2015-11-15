package IPcalc;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 13.11.2015.
 */
public class IPRangeCollector extends IPConverter {
    private List<InetAddress> addressList;
    private String firstIP;
    private String secondIP;

    public IPRangeCollector(String firstIP, String secondIP){
        this.firstIP = firstIP;
        this.secondIP = secondIP;
        this.addressList = new ArrayList<>();
    }

    public List<InetAddress> createIPRangeList() throws IOException{
            InetAddress adr = InetAddress.getByName(firstIP);
            InetAddress adr2 = InetAddress.getByName(secondIP);
            long ip1 = inetAddressToLongConvertation(adr);
            long ip2 = inetAddressToLongConvertation(adr2);
            addressList.add(adr);
            while (ip1 != ip2) {
                ip1++;
                if (!longToStringIPConvertation(ip1).contains(".0")) {
                    addressList.add(InetAddress.getByName(longToStringIPConvertation(ip1)));
                }
            }

        return addressList;
    }
}
