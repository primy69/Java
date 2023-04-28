package Aplicatie1;

import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {

            System.out.println("Alegeti varianta de realizare:");
            System.out.println("1. Botosanu");
            System.out.println("2. Ciloci");
            System.out.println("3. Herghelegiu");

            System.out.print("Alegeti un utilizator: ");

            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    Depozit2 depozitVasile = new Depozit2(7);

                    Producer producer1 = new Producer(depozitVasile, "Producer-1", 2);
                    Producer producer2 = new Producer(depozitVasile, "Producer-2", 2);

                    Consumer consumer1 = new Consumer(depozitVasile, "Consumer-1");
                    Consumer consumer2 = new Consumer(depozitVasile, "Consumer-2");
                    Consumer consumer3 = new Consumer(depozitVasile, "Consumer-3");
                    Consumer consumer4 = new Consumer(depozitVasile, "Consumer-4");

                    producer1.start();
                    producer2.start();

                    consumer1.start();
                    consumer2.start();
                    consumer3.start();
                    consumer4.start();

                    break;
                case 2:
                    Depozit3 depozitIon = new Depozit3(7);

                    Producer producer3 = new Producer(depozitIon, "Producer-3", 2);
                    Producer producer4 = new Producer(depozitIon, "Producer-4", 2);

                    Consumer consumer5 = new Consumer(depozitIon, "Consumer-5");
                    Consumer consumer6 = new Consumer(depozitIon, "Consumer-6");
                    Consumer consumer7 = new Consumer(depozitIon, "Consumer-7");
                    Consumer consumer8 = new Consumer(depozitIon, "Consumer-8");

                    producer3.start();
                    producer4.start();

                    consumer5.start();
                    consumer6.start();
                    consumer7.start();
                    consumer8.start();
                    break;
                case 3:
                    Depozit1 depozitIanush = new Depozit1(7);

                    Producer producer5 = new Producer(depozitIanush, "Producer #1", 1);
                    Producer producer6 = new Producer(depozitIanush, "Producer #2", 1);

                    Consumer consumer9 = new Consumer(depozitIanush, "Consumer #9");
                    Consumer consumer10 = new Consumer(depozitIanush, "Consumer #10");
                    Consumer consumer11 = new Consumer(depozitIanush, "Consumer #11");
                    Consumer consumer12 = new Consumer(depozitIanush, "Consumer #12");

                    producer5.start();
                    producer6.start();

                    consumer9.start();
                    consumer10.start();
                    consumer11.start();
                    consumer12.start();

                    break;
                default:
                    System.out.println("Asa numar de optiune nu exista");
            }
     }
}


