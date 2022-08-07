import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {

        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Calendar date = Calendar.getInstance();
        date.set(2022,12,15,8,30,56);
        Date time = date.getTime();
        System.out.println(time);
        System.out.println(sp.format(time));

        Date date1 = new Date();
        long time1 = date1.getTime();
        System.out.println(time1);
        System.out.println(sp.format(time1));
    }
}
