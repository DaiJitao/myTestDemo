package person.commvault.backup;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeProcessor {
    public static void main(String[] args) {
        System.out.println(getInstance().dateFormConvert(new BigInteger(String.valueOf(1378180800))));
        long ms = 1534472426000L;
        Date date = new Date(ms);
        System.out.println(date);
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(date));
    }



    private static TimeProcessor instance = new TimeProcessor();
	private TimeProcessor(){}
	public static TimeProcessor getInstance() {
		return instance;
	}

    public String dateFormConvert(BigInteger mss){
        if (mss.compareTo(BigInteger.ZERO) <= 0) {
            return 0 + "天" + 0 + "小时" + 0 + "分" +  0 + "秒";
        }
        BigInteger innerTemp = new BigInteger(String.valueOf(1000 * 60 * 60 * 24));
        BigInteger ff0 = mss.mod(innerTemp);
        BigInteger ff1 = new BigInteger(String.valueOf(1000 * 60 * 60));
        BigInteger days = mss.divide(innerTemp);
        BigInteger hours = ff0.divide(ff1);
        BigInteger ff2 = mss.mod(ff1);
        BigInteger ff3 = new BigInteger(String.valueOf(1000 * 60));
        BigInteger minutes =  ff2.divide(ff3) ;
        BigInteger ff4 = new BigInteger(String.valueOf(1000));
        BigInteger seconds = mss.mod(ff3).divide(ff4);
        return days + "天" + hours + "小时" + minutes + "分" +  seconds + "秒";
    }

}
