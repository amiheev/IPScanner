package IPcalc;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 13.11.2015.
 */
public class IPRangeChecker {
    private List<String> reachableAddresses;

    public IPRangeChecker(){
        this.reachableAddresses = new ArrayList<>();
    }

    public List<String> checkIPRange(List<InetAddress> inetAddressList){
        boolean flag = false;
        try {

            for (InetAddress address : inetAddressList) {
                if (address.isReachable(null, 0, 1000)) {
                    flag = true;
                    reachableAddresses.add(address.getHostAddress());
                }
            }

        }catch (Exception e ){
            e.printStackTrace();

        }
        if (flag == false)reachableAddresses.add("Nothing is Reachable");

        return reachableAddresses;

    }

    public List<String> printIPList(List<InetAddress> inetAddressList) {
        List<String> list = new ArrayList<>();
        for (InetAddress address : inetAddressList) {
            list.add(address.getHostAddress());
        }
        return list;

    }




}
