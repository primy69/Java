//Marius
public class Scriitor implements Runnable {

    private static System LogManager;
    private static final System.Logger logger = LogManager.getLogger(String.valueOf(Scriitor.class));

    private biblioteca biblioteca;

    public Scriitor(biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void run() {
        for (int i = 0; i < biblioteca.getNumarCarti(); i++) {
            biblioteca.scrieCarte();
            logger.log(System.Logger.Level.INFO, "Scriitorul a scris cartea {}", i + 1);
        }
    }

    private static class Logger {
    }
}
