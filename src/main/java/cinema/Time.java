package cinema;

import java.util.Objects;

public class Time {
    private int min;
    private int hour;

    public Time(int hour, int min) {
        boolean flag = true;
        while (flag) {
            flag=false;
            if (hour >= 24) {
                hour -= 24;
                flag=true;
            }
            if (min >= 60) {
                min -= 60;
                hour++;
                flag=true;
            }
        }
        this.min = min;
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return " "+hour +" hours "+min+" min";
    }

    public static void normalizeTime(Time time){
        if (time.getMin()>=60){
            time.min-=60;
            time.hour++;
        }
        if (time.getHour()>=24){
            time.hour-=24;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return min == time.min &&
                hour == time.hour;
    }

    @Override
    public int hashCode() {

        return Objects.hash(min, hour);
    }
}
