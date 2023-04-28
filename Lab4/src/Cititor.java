import java.util.logging.Level;
import java.util.logging.Logger;
//Pawel
class Cititor implements Runnable {
    private final biblioteca biblioteca;
    private final int numarCartiDeCitit;
    private final Logger logger;

    Cititor(biblioteca b, int n) {
        biblioteca = b;
        numarCartiDeCitit = n;
        logger = Logger.getLogger(Cititor.class.getName());
    }

    public void run() {
        for (int i = 0; i < numarCartiDeCitit; i++) {
            biblioteca.citesteCarte();
            logger.log(Level.INFO, Thread.currentThread().getName() + " a citit o carte.");
        }
    }
}