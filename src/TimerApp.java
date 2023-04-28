import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

//Dorian
public class TimerApp {
    private Timer timer;
    private TimerTask reminderTask;
    private int remainingTime;

    public TimerApp() {
        timer = new Timer();
        reminderTask = new ReminderTask();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Timer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        // Adaugă un câmp text pentru intrarea utilizatorului
        JTextField inputField = new JTextField(10);
        panel.add(inputField);

        JButton button1 = new JButton("Start Timer");
        panel.add(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Inițializează timpul rămas la valoarea introdusă de utilizator
                remainingTime = Integer.parseInt(inputField.getText());

                startTimer();
            }
        });

        JButton button2 = new JButton("Stop Timer");
        panel.add(button2);

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
    //Pavel
    private void startTimer() {
        // Timer care se declanseaza la un anumit timp
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 12);
        date.set(Calendar.MINUTE, 30);
        date.set(Calendar.SECOND, 0);

        timer.schedule(reminderTask, date.getTime());

        // Timer care se declanseaza la un interval de timp specificat
        timer.schedule(new fiveseconds(), 0, 5000);
        timer.schedule(new Intervalpetimp(), 0, 5000);
    }

    //Marius
    private void stopTimer() {
        timer.cancel();
    }

    public static void main(String[] args) {
        TimerApp app = new TimerApp();
        app.createAndShowGUI();
    }

    class ReminderTask extends TimerTask {
        public void run() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            JOptionPane.showMessageDialog(null, "Este ora " + hour + ":" + minute + ".");
        }
    }
    //Pavel
    class Intervalpetimp extends TimerTask {
        public void run() {
            remainingTime -= 5;
            if (remainingTime <= 0) {
                JOptionPane.showMessageDialog(null, "Timpul a fost epuizat.");
                stopTimer();
            }
        }

    }
    class fiveseconds extends TimerTask {
        public void run() {
            JOptionPane.showMessageDialog(null, "Au trecut 5 secunde.");
        }
    }
}
