import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
//Marius
public class ProducerConsumerWithCyclicBarrier {

    private CyclicBarrier cyclicBarrier;
    private int[] buffer;
    private int index;

    public ProducerConsumerWithCyclicBarrier(int bufferSize) {
        this.cyclicBarrier = new CyclicBarrier(2);
        this.buffer = new int[bufferSize];
        this.index = 0;
    }

    public void produce(int value) throws InterruptedException, BrokenBarrierException {
        synchronized (this) {
            while (index == buffer.length) {
                wait();
            }
            buffer[index++] = value;
            System.out.println("Produced: " + value);
            notifyAll();
        }
        cyclicBarrier.await();
    }

    public int consume() throws InterruptedException, BrokenBarrierException {
        int value;
        synchronized (this) {
            while (index == 0) {
                wait();
            }
            value = buffer[--index];
            System.out.println("Consumed: " + value);
            notifyAll();
        }
        cyclicBarrier.await();
        return value;
    }
}
