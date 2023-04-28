import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Philosopher extends Thread {
    private int name;
    private State currentState;
    private Forks forks;
    private boolean wasHungry = false;
    private static int currentName = 0;

    public Philosopher(Forks forks) {
        this.forks = forks;
        this.currentState = State.Hungry;
        this.name = currentName++;
        System.out.println("Philosopher #" + name + " has reached the table!");
    }

    @Override
    public void run() {
        while (Forks.Main.isRunning()) {
            switch (currentState) {
                case Hungry:
                    if (!wasHungry) {
                        System.out.println("Philosopher #" + name + " is hungry");
                    }
                    wasHungry = true;
                    if (forks.takeForks(name)) {
                        this.currentState = State.Eating;
                    }
                    break;
                case Eating:
                    System.out.println("Philosopher #" + name + " is eating");

                    try {
                        Thread.sleep((int) (Math.random() * 1000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    forks.leaveForks(name);
                    this.currentState = State.Thinking;
                    wasHungry = false;
                    break;
                case Thinking:
                    System.out.println("Philosopher #" + name + " is thinking");

                    try {
                        Thread.sleep((int) (Math.random() * 2000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.currentState = State.Hungry;
                    wasHungry = false;
                    break;
            }
        }
    }

    private enum State {Hungry, Eating, Thinking}
}
class Forks {
    private int number;
    private State[] forks;
    private Lock lock = new ReentrantLock();

    public Forks(int number) {
        this.number = number;
        this.forks = new State[number];
        for (int i = 0; i < number; i++) {
            forks[i] = State.Free;
        }
        System.out.println("Forks are created!");
        printState();
    }

    public boolean takeForks(int position) {
        boolean success = false;
        lock.lock();
        State leftState;
        State rightState;
        if (position == 0) {
            leftState = forks[number - 1];
        } else {
            leftState = forks[position - 1];
        }

        rightState = forks[position];

        if (leftState == State.Free && rightState == State.Free) {
            if (position == 0) {
                forks[number - 1] = State.InUse;
            } else {
                forks[position - 1] = State.InUse;
            }
            forks[position] = State.InUse;
            success = true;
            printState();
        }
        lock.unlock();
        return success;
    }

    public void leaveForks(int position) {
        lock.lock();
        forks[position] = State.Free;
        if (position == 0) {
            forks[number - 1] = State.Free;
        } else {
            forks[position - 1] = State.Free;
        }
        System.out.println("Philosopher #" + position + " has finished eating");
        printState();
        lock.unlock();
    }

    private enum State {Free, InUse}

    private void printState() {
        System.out.println("Forks: " + forks);
    }

    public class Main {
        private static final int NUM_PHILOSOPHERS = 5;
        private static Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        private static Forks forks = new Forks(NUM_PHILOSOPHERS);

        // adăugăm variabila globală booleană "running"
        private static volatile boolean running = true;

        public static void main(String[] args) {
            for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(forks);
            }
            for (Philosopher philosopher : philosophers) {
                philosopher.start();
            }

            // așteptăm 10 secunde, apoi oprim programul
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // setăm variabila globală "running" la "false" pentru a opri programul
            running = false;
        }

        public static boolean isRunning() {
            return running;
        }
    }
}

