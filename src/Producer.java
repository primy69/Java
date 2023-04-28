package Aplicatie1;

import java.util.Random;


public class Producer implements Runnable {

    private Thread thread;
    private DepozitInterface buffer;
    private int prodNum;
    private static int maxProduction = 47;
    private static int startProduction = 0;
    private static int[] production = new int[]{1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};

    public Producer (DepozitInterface buffer, String name,int prodNum)
    {
        this.thread = new Thread(this,name);
        this.buffer = buffer;
        this.prodNum = prodNum;
    }

    public void start()
    {
        this.thread.start();
    }
    public void run() {

        Random randElement = new Random();


        while(startProduction < maxProduction)
        {
            if (startProduction == 46) {
                int randomElement = randElement.nextInt(0,15);
                int[] products = new int[1];
                products[0] = production[randomElement];
                buffer.put(products,thread.getName());
            }else
            {
                int randomElement;
                int[] products = new int[prodNum];
                for (int i = 0; i < prodNum; i++)
                {
                    randomElement = randElement.nextInt(0,15);
                    products[i] = production[randomElement];
                }
                buffer.put(products,thread.getName());
            }
        }
        System.out.println(thread.getName() + " a terminat lucrul");
    }

    public static int incrementStartProduction(int num)
    {
        return startProduction +=num;
    }

    public static boolean planOfWork()
    {
        return startProduction>=maxProduction;
    }
}

