package IPcalc;

import java.net.InetAddress;

/**
 * Created by Alexey on 13.11.2015.
 */
public abstract class IPConverter {
    private static final long FIRST_OCTET = 256*256*256;
    private static final long SECOND_OCTET = 256*256;
    private static final long THIRD_OCTET = 256;

    protected long inetAddressToLongConvertation(InetAddress inetAddress)
    {
        byte iab[] = inetAddress.getAddress();
        int i0 = iab[0]; if (i0<0) i0+=256;
        int i1 = iab[1]; if (i1<0) i1+=256;
        int i2 = iab[2]; if (i2<0) i2+=256;
        int i3 = iab[3]; if (i3<0) i3+=256;
        return (FIRST_OCTET *i0)+(SECOND_OCTET *i1)+(THIRD_OCTET *i2)+i3;
    }

    protected String longToStringIPConvertation(long ip)
    {
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
