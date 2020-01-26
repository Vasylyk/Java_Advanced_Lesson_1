package cinemaTest;

import cinema.Time;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TimeTest {
    private Time time1;
    private Time time2;

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
        time1 = new Time(5, 15);
        time2 = new Time(30, 80);
    }
    @After
    public void after(){
        time2 = null;
        time1 = null;
    }

    @Test
    public void normalizeTimeTest(){
        Time.normalizeTime(time1);
        int actualHour = time1.getHour();
        int actualMin = time1.getMin();
        int expectedHour = 5;
        int expectedMin = 15;
        Assert.assertEquals(actualHour, expectedHour);
        Assert.assertEquals(actualMin, expectedMin);
    }

    @Test
    public void timeOverBordersTest(){
        Time.normalizeTime(time2);
        int actualHour = time2.getHour();
        int actualMin = time2.getMin();
        int expectedHour = 7;
        int expectedMin = 20;
        Assert.assertEquals(actualHour, expectedHour);
        Assert.assertEquals(actualMin, expectedMin);
    }

}
