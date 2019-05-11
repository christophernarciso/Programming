package util.timers;

public class Time {
    public static String format(long time) {
        StringBuilder t = new StringBuilder();
        long total_secs = time / 1000L;
        long total_mins = total_secs / 60L;
        long total_hrs = total_mins / 60L;
        long total_days = total_hrs / 24L;
        int secs = (int) total_secs % 60;
        int mins = (int) total_mins % 60;
        int hrs = (int) total_hrs % 24;
        int days = (int) total_days;
        if (days > 0) {
            if (days < 10) {
                t.append("0");
            }
            t.append(days);
            t.append(":");
        }
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }
}
