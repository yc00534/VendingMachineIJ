import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public Time() {
        getTime();
    }

    public String getTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(now);
    }
}
