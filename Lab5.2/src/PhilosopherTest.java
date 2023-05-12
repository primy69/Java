//Dorian
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhilosopherTest {

    @Test
    public void testPhilosopherCreation() {
        Forks forks = new Forks(7);
        Philosopher philosopher = new Philosopher(forks, 0);
        assertNotNull(philosopher);
    }

    @Test
    public void testPhilosopherEating() {
        Forks forks = new Forks(7);
        Philosopher philosopher = new Philosopher(forks, 0);
        philosopher.run();
        assertEquals(3, philosopher.eats);//se verifica daca nr de mese consumate este 3
    }
}
