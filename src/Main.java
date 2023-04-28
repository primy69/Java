import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BrokenBarrierException;
//Dorian
class Producator implements Runnable {
    private ReentrantLock lock;
    private Condition depozitPlin, depozitGol;
    private int numarProduse;
    private String num;

    public Producator(ReentrantLock lock, int depozitPlin, int depozitGol, int numarProduse) {
        this.lock = lock;
        this.numarProduse = numarProduse;
    }

    public Producator(Semaphore depozitPlin, Semaphore depozitGol, int i) {
    }

    public Producator(CyclicBarrier bariera, int capacitateDepozit, int i) {

    }

    public void run() {
        for(int i=1; i<=numarProduse; i++) {
            try {
                lock.lock(); // blochează lock-ul
                while(depozitGol.awaitNanos(1) > 0) {} // așteaptă până când depozitul este gol
                producereObiect(i);
                depozitPlin.signal(); // notifică consumatorii că depozitul este plin
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // deblochează lock-ul
            }
        }
    }

    private void producereObiect(int numarObiect) {
        System.out.println("Producător " + Thread.currentThread().getName() + " a produs obiectul " + num);
    }
        }

class Consumator implements Runnable {
    private ReentrantLock lock;
    private Condition depozitPlin, depozitGol;
    private int numarConsumat;
    public Consumator(ReentrantLock lock, int depozitPlin, int depozitGol, int numarConsumat) {
        this.lock = lock;
        this.numarConsumat = numarConsumat;
        }

    public Consumator(Semaphore depozitPlin, Semaphore depozitGol, int i) {

    }

    public Consumator(CyclicBarrier bariera, int capacitateDepozit, int i) {

    }

    public void run() {
        for(int i=1; i<=numarConsumat; i++) {
        try {
        lock.lock(); // blochează lock-ul
        while(depozitPlin.awaitNanos(1) > 0) {} // așteaptă până când depozitul este plin
        consumareObiect(i);
        depozitGol.signal(); // notifică producătorii că depozitul este gol
        } catch (InterruptedException e) {
        e.printStackTrace();
        } finally {
        lock.unlock(); // deblochează lock-ul
        }
        }
        }

private void consumareObiect(int numarObiect) {
        System.out.println("Consumator " + Thread.currentThread().getName() + " a consumat obiectul " + numarObiect);
        }
        }
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int x = 2, y = 4, z = 50, d = 5;
        int cantitate = 0;
        int capacitate = 5;
        int capacitateDepozit = 10;
        ReentrantLock lock = new ReentrantLock();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selectați tipul de sincronizare (1 = Semaphore, 2 = Lock, 3 = CyclicBarrier):");
        int tipSincronizare = scanner.nextInt();

        if (tipSincronizare == 1) {
            ProducerConsumerSemaphore pc = new ProducerConsumerSemaphore();
            pc.start();
        } else if (tipSincronizare == 2) {

            for (int i = 1; i <= x; i++) {
                Thread producator = new Thread(new Producator(lock, cantitate, capacitate, z / x), "Producător " + i);
                producator.start();
            }

            for (int i = 1; i <= y; i++) {
                Thread consumator = new Thread(new Consumator(lock, cantitate, capacitate, z / y), "Consumator " + i);
                consumator.start();
            }
        } else if (tipSincronizare == 3) {
            ProducerConsumerWithCyclicBarrier pc = new ProducerConsumerWithCyclicBarrier(10);

            Thread producerThread = new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        pc.produce(i);
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            Thread consumerThread = new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        pc.consume();
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            producerThread.start();
            consumerThread.start();

            try {
                producerThread.join();
                consumerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Opțiune invalidă.");
        }
    }
}
