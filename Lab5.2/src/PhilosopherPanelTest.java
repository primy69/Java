//Dorian
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class PhilosopherPanelTest {

    @Test
    public void testPreferredSize() {
        PhilosopherPanel panel = new PhilosopherPanel();
        Dimension expectedSize = new Dimension(50, 50);
        assertEquals(expectedSize, panel.getPreferredSize());
    }

    @Test
    public void testBackgroundColor() {
        Color expectedColor = Color.RED;
        PhilosopherPanel panel = new PhilosopherPanel();
        assertEquals(expectedColor, panel.getBackground());
    }
}
