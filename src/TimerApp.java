import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerApp {
    private Timer timer;
    private TimerTask reminderTask;

    public TimerApp() {
        timer = new Timer();
        reminderTask = new ReminderTask();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Timer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JButton button1 = new JButton("Start Timer");
        panel.add(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    private void startTimer() {
        // Timer care se declanseaza la un anumit timp
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 12);
        date.set(Calendar.MINUTE, 30);
        date.set(Calendar.SECOND, 0);

        timer.schedule(reminderTask, date.getTime());

        // Timer care se declanseaza la un interval de timp specificat
        timer.schedule(new IntervalTask(), 0, 5000);
    }

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

    class IntervalTask extends TimerTask {
        public void run() {
            JOptionPane.showMessageDialog(null, "Au trecut 5 secunde.");
        }
    }
}
