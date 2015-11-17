package IPcalc;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 13.11.2015.
 */
public class IPRangeCollector implements IIPConverter {
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
            long ip1 = ipAddressToLongConverter(adr);
            long ip2 = ipAddressToLongConverter(adr2);
            addressList.add(adr);
            while (ip1 != ip2) {
                ip1++;
                String ipArray[] = longToStringIPConvertation(ip1).split("\\.");
                if (!ipArray[3].equals("0")) {
                    addressList.add(InetAddress.getByName(longToStringIPConvertation(ip1)));
                }
            }

        return addressList;
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
        long a = ip;
        long r0 = a / FIRST_OCTET;
        a -= (r0* FIRST_OCTET);
        long r1 = a / SECOND_OCTET;
        a -= (r1* SECOND_OCTET);
        long r2 = a / THIRD_OCTET;
        a -= (r2* THIRD_OCTET);
        long r3 = a;

        String ips = r0+"."+r1+"."+r2+"."+r3;
        return ips;
    }
}
