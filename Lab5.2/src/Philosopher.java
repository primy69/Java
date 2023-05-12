//Pavel
import java.awt.*;

public class Philosopher extends Thread {

    private int name;
    private state currentState;
    private enum state {Hungry, Eating, Thinking};
    private Forks forks;
    private boolean wasHungry = false;
    private static int currentName = 0;
    public int eats;

    PhilosopherPanel panel;
    public Philosopher(Forks forks, int eats) {
        this.forks = forks;
        this.currentState = state.Hungry;
        this.name = currentName++;
        this.eats = eats;
        System.out.println("Philosopher #"+name+" has riched the table!");
        panel = new PhilosopherPanel();
    }

    @Override
    public void run() {
        while (this.eats < 3) {
            switch (currentState) {
                case Hungry:
                    if (wasHungry == false) {
                        System.out.println("Philosopher #"+name+" is hungry");
                    }
                    wasHungry = true;
                    if (forks.takeForks(name) == true) {
                        this.currentState = state.Eating;
                        panel.setBackground(Color.BLACK);
                    }
                    break;
                case Eating:
                    System.out.println("Philosopher #"+name+" is eating");
                    try {
                        Thread.sleep( (int)(Math.random()*5000) );
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    forks.leaveForks(name);
                    this.currentState = state.Thinking;
                    this.eats++;
                    if(this.eats == 3)
                    {
                        System.out.println("Filosoful " + name + " a finisat sejurul la restaurant. Acesta a mancat " + this.eats + " feluri de mancare");
                    }
                    panel.setBackground(Color.YELLOW);
                    wasHungry = false;
                    break;
                case Thinking:
                    System.out.println("Philosopher #"+name+" is thinking");
                    panel.setBackground(Color.ORANGE);
                    try {
                        Thread.sleep( (int)(Math.random()*5000) );
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    this.currentState = state.Hungry;
                    wasHungry = false;
                    panel.setBackground(Color.WHITE);
                    break;
            }
        }
    }
}
