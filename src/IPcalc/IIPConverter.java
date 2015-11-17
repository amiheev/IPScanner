package IPcalc;

import java.net.InetAddress;

/**
 * Created by Alexey on 16.11.2015.
 */
public interface IIPConverter {

    public static final long FIRST_OCTET = 256 * 256 * 256;
    public static final long SECOND_OCTET = 256 * 256;
    public static final long THIRD_OCTET = 256;

    public long ipAddressToLongConverter(InetAddress inetAddress);

    public String longToStringIPConvertation(long ip);
}

