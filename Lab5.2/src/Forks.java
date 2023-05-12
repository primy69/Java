//Pavel
import java.util.concurrent.locks.*;

public class Forks {

    private int number;
    private state forks[];
    private enum state { Free, inUse };
    private Lock lock = new ReentrantLock();

    public Forks(int number) {
        this.number = number;
        this.forks = new state[number];
        for (int i = 0; i < number; i++) {
            forks[i] = state.Free;
        }
        System.out.println("Forks are created!");
        printState();
    }

    public boolean takeForks (int position) {
        boolean success = false;
        lock.lock();
        state leftState;
        state rightState;
        if (position == 0) {
            leftState = forks[number-1];
        }
        else {
            leftState = forks[position-1];
        }

        rightState = forks[position];

        if (leftState == state.Free && rightState == state.Free) {
            if (position == 0) {
                forks[number-1] = state.inUse;
            }
            else {
                forks[position-1] = state.inUse;
            }
            forks[position] = state.inUse;
            System.out.println("Philosopher #"+position+" has taken the forks.");
            printState();
            success = true;
        }
        lock.unlock();
        return success;
    }

    public void leaveForks (int position) {
        lock.lock();
        if (position == 0) {
            forks[number-1] = state.Free;
        }
        else {
            forks[position-1] = state.Free;
        }
        forks[position] = state.Free;
        System.out.println("Philosopher #"+position+" has left the forks.");
        printState();
        lock.unlock();
    }

    private void printState() {
        System.out.print("Current state: ");
        for (int i = 0; i<number; i++){
            System.out.print(i+":"+forks[i]+"  ");
        }
        System.out.println();
    }
}
