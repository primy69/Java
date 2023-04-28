package Aplicatie1;

import java.util.*;


public class Depozit3 implements DepozitInterface
{

    private int sizeStorage;
    private Queue<Integer> storage = new LinkedList<>();


    public Depozit3(int size)
    {
        sizeStorage = size;
    }

    public synchronized void put(int[] products, String threadName)
    {
        if(storage.size() >= sizeStorage)
        {
            System.out.println("Depozitul este plin!!!");
        }
        while(storage.size() >= sizeStorage)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        if(Producer.planOfWork())
            return;

        for (var i: products)
        {
            storage.add(i);
        }
        int i = Producer.incrementStartProduction(products.length);
        System.out.println("S-au produs" +i);

        if(products.length == 2)
        {
            System.out.println(threadName +"a fost adaugat" + products[0] +","+products[1]);
        }
        if(products.length == 1)
        {
            System.out.println(threadName + " a fost adaugat: " + products[0]);
        }
        notifyAll();

    }

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
                if (Consumer.producedPlan())
                    return null;
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        int[] products;
        products = new int[1];
        products[0] = storage.poll();
        System.out.println(threadName + " a fost consumat: " + products[0]);

        int i = Consumer.incElementsConsumed(1);
        System.out.println("S-au consumat " + i);

        notifyAll();

        return products;
    }
}

