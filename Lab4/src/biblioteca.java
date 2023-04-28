import java.util.logging.Logger;
//Dorian
public class biblioteca implements Runnable {
    private final int numarCarti;
    private int cartiDisponibile;
    private int scriitoriInBiblioteca;
    private static final Logger logger = Logger.getLogger(biblioteca.class.getName());

    biblioteca(int n) {
        numarCarti = n;
        cartiDisponibile = n;
        scriitoriInBiblioteca = 0;
    }

    public synchronized void citesteCarte() {
        while (cartiDisponibile == 0 || scriitoriInBiblioteca > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cartiDisponibile--;
        logger.info(Thread.currentThread().getName() + " a citit o carte.");
        notifyAll();
    }

    public synchronized void scrieCarte() {
        while (cartiDisponibile > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cartiDisponibile = numarCarti;
        logger.info(Thread.currentThread().getName() + " a scris " + numarCarti + " cărți.");
        notifyAll();
    }

    public int getNumarCarti() {
        return numarCarti;
    }

    @Override
    public void run() {

    }
}
