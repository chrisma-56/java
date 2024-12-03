
import java.util.concurrent.atomic.AtomicInteger;

public class Program6_1 extends Thread {
    private int[] coins;
    private int n;
    private int sum;
    private AtomicInteger result; // Thread-safe result variable

    // Constructor for the thread
    public Program6_1(int[] coins, int n, int sum, AtomicInteger result) {
        this.coins = coins;
        this.n = n;
        this.sum = sum;
        this.result = result;
    }

    // Recursive function to count combinations
    private int countRecur(int[] coins, int n, int sum) {
        if (sum == 0) return 1; // One valid combination
        if (sum < 0 || n == 0) return 0; // No valid combination
        // Include the current coin and exclude it
        return countRecur(coins, n, sum - coins[n - 1]) +
               countRecur(coins, n - 1, sum);
    }

    @Override
    public void run() {
        // Each thread calculates part of the result
        int partialResult = countRecur(coins, n, sum);
        result.addAndGet(partialResult); // Safely update the shared result
    }

    // Main method
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 5;
        AtomicInteger result = new AtomicInteger(0); // Shared result container

        // Divide tasks across threads
        int numThreads = 3;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Program6_1(coins, coins.length, sum, result);
            threads[i].start(); // Start each thread
        }

        // Wait for all threads to complete
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final result
        System.out.println("Number of ways to make the sum: " + result.get());
    }
}

