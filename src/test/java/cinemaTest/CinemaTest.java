package cinemaTest;

import cinema.*;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.TreeMap;

public class CinemaTest {
    private Cinema cinema;
    private Movie movie1;
    private Movie movie2;
    private Seance seance1;
    private Seance seance2;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("Success: "+description.getClassName()+"; "+description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Fail: "+description.getClassName()+"; "+description.getMethodName());
        }
    };

    @Before
    public void before(){
        cinema = new Cinema(new Time(8, 0), new Time(22, 0));
        movie1 = new Movie("John Wick", new Time(2, 20));
        movie2 = new Movie("Fast and Furious", new Time(1, 50));
        seance1 = new Seance(movie1, new Time(12, 20));
        seance2 = new Seance(movie2, new Time(10, 0));

    }
    @After
    public void after(){
        cinema = null;
        movie1 = null;
        movie2 = null;
        seance1 = null;
        seance2 = null;
    }

    @Test
    public void addMovieToLibraryTest(){
        cinema.addMovieToLibrary(movie1);
        cinema.addMovieToLibrary(movie2);
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movie1);
        expected.add(movie2);
        Assert.assertEquals(expected, cinema.getMoviesLibrary());
    }

    @Test
    public void addSeanceTest(){
        String day1 = "Monday";
        String day2 = "Sunday";
        cinema.addSeance(seance1, day1);
        cinema.addSeance(seance2, day2);
        TreeMap<String, Schedule> expected = new TreeMap<>();
        expected.put(day1, new Schedule());
        expected.put(day2,new Schedule());
        expected.get(day1).addSeance(seance1);
        expected.get(day2).addSeance(seance2);
        Assert.assertEquals(expected, cinema.getSchedules());
    }

    @Test
    public void removeMovieTest1(){
        cinema.addMovieToLibrary(movie1);
        cinema.addMovieToLibrary(movie2);
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movie1);
        cinema.removeMovie(movie2);
        Assert.assertEquals(expected, cinema.getMoviesLibrary());
    }

    @Test
    public void removeMovieTest2(){
        String day1 = "Monday";
        String day2 = "Sunday";
        cinema.addSeance(seance1, day1);
        cinema.addSeance(seance2, day2);
        cinema.removeMovie(movie1);
        TreeMap<String, Schedule> expected = new TreeMap<>();
        expected.put(day1, new Schedule());
        expected.put(day2,new Schedule());
        expected.get(day2).addSeance(seance2);
        Assert.assertEquals(expected, cinema.getSchedules());
    }

    @Test
    public void removeSeanceTest(){
        String day1 = "Monday";
        String day2 = "Sunday";
        cinema.addSeance(seance1, day1);
        cinema.addSeance(seance2, day2);
        cinema.removeSeance(seance1, day1);
        TreeMap<String, Schedule> expected = new TreeMap<>();
        expected.put(day1, new Schedule());
        expected.put(day2,new Schedule());
        expected.get(day2).addSeance(seance2);
        Assert.assertEquals(expected, cinema.getSchedules());
    }
}
