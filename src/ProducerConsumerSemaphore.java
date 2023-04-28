import java.util.concurrent.Semaphore;
//Pavel
public class ProducerConsumerSemaphore {
    private static final int BUFFER_SIZE = 10;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore empty = new Semaphore(BUFFER_SIZE);
    private Semaphore full = new Semaphore(0);
    private int[] buffer = new int[BUFFER_SIZE];
    private int in = 0;
    private int out = 0;

    class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    empty.acquire();
                    mutex.acquire();
                    buffer[in] = i;
                    in = (in + 1) % BUFFER_SIZE;
                    System.out.println("Producer produced " + i);
                    mutex.release();
                    full.release();
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    full.acquire();
                    mutex.acquire();
                    int item = buffer[out];
                    out = (out + 1) % BUFFER_SIZE;
                    System.out.println("Consumer consumed " + item);
                    mutex.release();
                    empty.release();
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());
        producerThread.start();
        consumerThread.start();
    }
}
