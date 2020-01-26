package cinema;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Cinema {
    private TreeMap<String , Schedule> schedules = new TreeMap<>();
    private ArrayList<Movie> moviesLibrary = new ArrayList<>();
    private Time open;
    private Time close;

    public Cinema(Time open, Time close) {
        this.open = open;
        this.close = close;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    public TreeMap<String , Schedule> getSchedules() {
        return schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "schedules=" + schedules +
                ", moviesLibrary=" + moviesLibrary +
                ", open=" + open +
                ", close=" + close +
                '}';
    }

    public void addMovieToLibrary(Movie movie){
        moviesLibrary.add(movie);
    }

    public void addSeance(Seance seance, String day){
        if ((seance.getStartTime().getHour()>=open.getHour())&&(seance.getEndTime().getHour()<close.getHour())){
            Optional<Map.Entry<String , Schedule>>  daySchedule = schedules.entrySet().stream().filter(x->x.getKey().equals(day)).findFirst();
            if (daySchedule.isPresent()){
                daySchedule.get().getValue().addSeance(seance);
            } else {
                schedules.put(day, new Schedule());
                schedules.entrySet().stream().filter(x->x.getKey().equals(day)).findFirst().get().getValue().getSeances().add(seance);
            }
        } else {
            System.out.println("Cinema is closed in this time");
        }
    }

    public void removeMovie(Movie movie){
        moviesLibrary.remove(movie);
        schedules.entrySet().stream().forEach(x->x.getValue().getSeances().stream().forEach(seance -> {
            if (seance.getMovie().equals(movie)){
                x.getValue().removeSeance(seance);
            }
        }));
    }

    public void removeSeance (Seance seance, String day){
        schedules.entrySet().stream().filter(x->x.getKey().equals(day)).findFirst().get().getValue().removeSeance(seance);
    }

    public void showSchedule(String day){
        schedules.entrySet().stream().filter(x->x.getKey().equals(day)).findFirst().get().getValue().getSeances().stream()
                .forEach(seance -> System.out.println(seance.toString()));
    }
}
