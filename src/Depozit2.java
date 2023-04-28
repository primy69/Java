package Aplicatie1;

import java.util.*;
public class Depozit2 implements DepozitInterface
{

    private int sizeStorage;
    private Queue<Integer> storage = new LinkedList<>();

    public Depozit2(int size)
    {
        sizeStorage = size;
    }

    @Override
    public synchronized void put(int[] var1, String threadName)
    {
        if (storage.size() >= sizeStorage)
        {
            System.out.println("Depozitul este plin");
        }
        while (storage.size() >= sizeStorage)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (Producer.planOfWork())
            return;

        for (var i : var1)
        {
            storage.add(i);
        }

        int i = Producer.incrementStartProduction(var1.length);
        System.out.println("S-au produs " + i);

        if (var1.length == 2)
        {
            System.out.println(threadName + " a fost adaugat: " + var1[0] + ", " + var1[1]);
        }
        if (var1.length == 1)
        {
            System.out.println(threadName + " a fost adaugat: " + var1[0]);
        }

        notifyAll();
    }

    @Override
    public synchronized int[] get(String threadName)
    {
        if (storage.isEmpty())
        {
            System.out.println("Depozitul este gol");
        }

        while (storage.isEmpty())
        {
            try
            {
                wait();
                if(Consumer.producedPlan())
                    return null;
            }catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

        int[] products;
        if (Consumer.getElementsConsumed() == 46)
        {
            products = new int[1];
            products[0] = storage.poll();
            System.out.println(threadName + " a fost consumat: " + products[0]);
        }
        else{
            products = new int[2];
            products[0] = storage.poll();
            products[1] = storage.poll();

            System.out.println(threadName + " a fost consumat : " + products[0] + ", " + products[1]);
        }

        int i = Consumer.incElementsConsumed(products.length);
        System.out.println("Au fost consumate " + i + " produse");

        notifyAll();

        return products;
    }
}

