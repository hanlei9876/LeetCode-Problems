package java_practice.Java_Multi_Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LostUpdate {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(6);

        Balance balance = new Balance();

        for(int i = 0; i < 1000; i++) {
            pool.execute(() -> balance.incrementBalance());
        }

        pool.shutdown();

        if (pool.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println(balance.balance);
        }
    }
}

class Balance {
    int balance;

    // void incrementBalance() {
    synchronized void incrementBalance() {
        balance++;
    }
}
