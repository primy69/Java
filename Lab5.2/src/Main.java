import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Forks forks = new Forks(8);

        JFrame frame = new JFrame("Dining Philosophers");

        JPanel panel = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < 8; i++) {
            Philosopher philosopher = new Philosopher(forks, 0);
            panel.add(philosopher.panel);

            philosopher.start();
        }

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
