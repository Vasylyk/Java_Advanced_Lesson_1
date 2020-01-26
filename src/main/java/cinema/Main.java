package cinema;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(new Time(8, 0), new Time(23, 0));
        Schedule schedule = new Schedule();
        Scanner sc = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1-add movie");
            System.out.println("2-add seance");
            System.out.println("3-remove movie");
            System.out.println("4-remove seance");
            System.out.println("5-show movies library");
            System.out.println("6-show seances in some day");

            switch (s.nextInt()) {
                case 1: {
                    System.out.println("Write title of movie:");
                    String title = sc.nextLine();
                    System.out.println("Duration of film");
                    System.out.println("Hours:");
                    int hourDuration = sc.nextInt();
                    System.out.println("Minutes:");
                    int minDuration = sc.nextInt();
                    String nextInt = sc.nextLine();
                    cinema.addMovieToLibrary(new Movie(title, new Time(hourDuration, minDuration)));
                    break;
                }
                case 2: {
                    System.out.println("Write title of movie:");
                    String title = sc.nextLine();
                    Optional<Movie> movie = cinema.getMoviesLibrary().stream().filter(x -> x.getTitle().equalsIgnoreCase(title)).findFirst();
                    System.out.println("Time to start");
                    System.out.println("Hours:");
                    int hour = sc.nextInt();
                    System.out.println("Minutes:");
                    int min = sc.nextInt();
                    String nextInt = sc.nextLine();
                    System.out.println("Write day");
                    String day = sc.nextLine().toUpperCase();
                    cinema.addSeance(new Seance(movie.get(), new Time(hour, min)), day);
                    break;
                }
                case 3:{
                    System.out.println("Write title of movie:");
                    String title = sc.nextLine();
                    Optional<Movie> movie = cinema.getMoviesLibrary().stream().filter(x -> x.getTitle().equalsIgnoreCase(title)).findFirst();
                    cinema.removeMovie(movie.get());
                    break;
                }
                case 4:{
                    System.out.println("Write from witch day you want to remove seance");
                    String day = sc.nextLine().toUpperCase();
                    System.out.println("Write title of movie, seance of witch you want to remove:");
                    String title = sc.nextLine();
                    Optional<Seance>seance = cinema.getSchedules().get(day).getSeances().stream().filter(x->x.getMovie().getTitle().equalsIgnoreCase(title)).findFirst();
                    cinema.removeSeance(seance.get(), day);
                    break;
                }
                case 5:{
                    System.out.println(cinema.getMoviesLibrary().toString());
                    break;
                }
                case 6:{
                    System.out.println("Write day:");
                    String day = sc.nextLine().toUpperCase();
                    cinema.showSchedule(day);
                    break;
                }
            }
        }
    }
}
