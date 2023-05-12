//Marius
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assertions.*;

public class ForksTest {

    @Test
    public void takeForksTest() {
        Forks forks = new Forks(7);
        assertTrue(forks.takeForks(0));
        assertFalse(forks.takeForks(0));
    }

    @Test
    public void leaveForksTest() {
        Forks forks = new Forks(7);
        forks.takeForks(0);
        forks.leaveForks(0);
        assertTrue(forks.takeForks(0));
    }

}
