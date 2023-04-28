package Aplicatie1;

import java.util.LinkedList;
import java.util.Queue;

public class Depozit1 implements DepozitInterface{

    private int dimensionStorage;
    private Queue<Integer> depozit = new LinkedList<>();

    public Depozit1(int dimension){
        dimensionStorage = dimension;
    }

    @Override
    public synchronized void put(int[] var1, String threadName) {
        if(depozit.size() >= dimensionStorage){
            System.out.println("Depozitul este plin");
        }

        while(depozit.size() >= dimensionStorage){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(Producer.planOfWork())
            return;

        depozit.add(var1[0]);

        int contor = Producer.incrementStartProduction(var1.length);
        System.out.println("Au fost produse " + contor + " elemente");

        System.out.println(threadName + " a adaugat in buffer: " + var1[0]);
        notifyAll();
    }


    @Override
    public synchronized int[] get(String threadName) {
        if(depozit.isEmpty()){
            System.out.println("Depozitul este gol");
        }

        while(depozit.isEmpty()){
            try {
                wait();

                if(Consumer.producedPlan())
                    return null;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int[] elemente = new int[0];
        if(Consumer.getElementsConsumed() == 46){
            elemente = new int[1];
            elemente[0] = depozit.poll();
            System.out.println(threadName + " a consumat din depozit: " + elemente[0]);
        }
        else
        {
            if(depozit.size() == 1){
                try {
                    wait();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                elemente = new int[2];
                elemente[0] = depozit.poll();
                elemente[1] = depozit.poll();

                System.out.println(threadName + " a consumat din depozit: " + elemente[0] + " si " + elemente[1]);
            }
        }

        int contorProduseConsumate = Consumer.incElementsConsumed(elemente.length);
        System.out.println("Au fost consumate " + contorProduseConsumate + " elemente.");
        notifyAll();
        return elemente;
    }

}
