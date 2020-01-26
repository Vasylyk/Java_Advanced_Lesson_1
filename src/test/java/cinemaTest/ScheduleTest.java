package cinemaTest;

import cinema.Movie;
import cinema.Schedule;
import cinema.Seance;
import cinema.Time;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.Set;
import java.util.TreeSet;

public class ScheduleTest {
    private Schedule schedule;
    private Seance seance;
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
        schedule = new Schedule();
        seance = new Seance(new Movie("John Wick", new Time(2, 20)), new Time(10, 30));
        seance2 = new Seance(new Movie("Fast and Furious", new Time(1, 50)), new Time(14, 30));
    }
    @After
    public void after(){
        schedule = null;
        seance = null;
        seance2 = null;
    }

    @Test
    public void addSeanceTest(){
        schedule.addSeance(seance);
        schedule.addSeance(seance2);
        Set<Seance> expected = new TreeSet<>();
        expected.add(seance);
        expected.add(seance2);
        Assert.assertEquals(expected, schedule.getSeances());
    }

    @Test
    public void removeSeanceTest(){
        schedule.addSeance(seance);
        schedule.addSeance(seance2);
        schedule.removeSeance(seance);
        Set<Seance> expected = new TreeSet<>();
        expected.add(seance2);
        Assert.assertEquals(schedule.getSeances(), expected);
    }

}
