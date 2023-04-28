import java.util.Scanner;

public class Program {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceti numarul de scriitori: ");
            int numarScriitori = scanner.nextInt();
            System.out.print("Introduceti numarul de cititori: ");
            int numarCititori = scanner.nextInt();
            System.out.print("Introduceti numarul de carti: ");
            int numarCarti = scanner.nextInt();

            biblioteca biblioteca = new biblioteca(numarCarti);
            Thread[] scriitori = new Thread[numarScriitori];
            Thread[] cititori = new Thread[numarCititori];

            for (int i = 0; i < numarScriitori; i++) {
                scriitori[i] = new Thread(new Scriitor(biblioteca), "Scriitor " + (i + 1));
            }

            for (int i = 0; i < numarCititori; i++) {
                cititori[i] = new Thread(new Cititor(biblioteca, numarCarti), "Cititor " + (i + 1));
            }

            for (int i = 0; i < numarScriitori; i++) {
                scriitori[i].start();
            }

            for (int i = 0; i < numarCititori; i++) {
                cititori[i].start();
            }

            for (int i = 0; i < numarScriitori; i++) {
                try {
                    scriitori[i].join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            for (int i = 0; i < numarCititori; i++) {
                try {
                    cititori[i].join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
