//Marius
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class PhilosopherPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int diameter = 100;
        int x = getWidth() / 5 - diameter / 2;
        int y = getHeight() / 2 - diameter / 4;

        g.setColor(Color.YELLOW);
        g.fillOval(x, y, diameter, diameter);

        g.setColor(getBackground());
        int[] xPoints = {x, x + diameter, x};
        int[] yPoints = {y + diameter, y + diameter, y};
        g.fillPolygon(xPoints, yPoints, 3);
    }

}
