class Depozit {
    int cantitate = 0;
    int capacitate = 5;

    public synchronized void adauga() throws InterruptedException {
        if (cantitate == capacitate) {
            System.out.println("Depozitul este plin");
            wait();
        }
        cantitate++;
        System.out.println("S-a adaugat un obiect. Cantitate curenta: " + cantitate);
        notifyAll();
    }

    public synchronized void elimina() throws InterruptedException {
        if (cantitate == 0) {
            System.out.println("Depozitul este gol");
            wait();
        }
        cantitate--;
        System.out.println("S-a eliminat un obiect. Cantitate curenta: " + cantitate);
        notifyAll();
    }
}
