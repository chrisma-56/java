import java.util.Arrays;
import java.util.Scanner;

public class ShareTrader {
    static int maxProfit; // Static variable to store the maximum profit

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Display the input array
        System.out.print("Input: price[] = {");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");

        // Calculate the maximum profit
        maxProfit = calculateMaxProfit(array);
        System.out.println("Output: " + maxProfit);

        scanner.close();
    }

    public static int calculateMaxProfit(int[] array) {
        //  the first two elements and return the positive difference
        if (array.length < 2) {
            System.out.println("Array must have at least two elements.");
            return 0;
        }

        int a = array[0];
        int b = array[1];
        int v1 = Math.abs(a - b); // Difference returned as v1, +ve

        //  Remove the first two elements
        int[] newArray = Arrays.copyOfRange(array, 2, array.length);

        // Check if there are elements left in the new array
        if (newArray.length == 0) {
            System.out.println("Not enough elements after removing the first two.");
            return v1; // Return only v1 if there are no elements left
        }

        // Find the smallest and largest elements in the new array
        int s = Integer.MAX_VALUE; // Initialize smallest
        int l = Integer.MIN_VALUE; // Initialize largest

        for (int num : newArray) {
            if (num < s) {
                s = num; // Update smallest
            }
            if (num > l) {
                l = num; // Update largest
            }
        }

        // positive difference between largest and smallest
        int v2 = Math.abs(l - s);

        // Step 4: Return the sum of v1 and v2 as max profit
        maxProfit = v1 + v2;

        // Display additional output
        System.out.println("max profit " + maxProfit + " as sum of " + v1 + ", " + v2);
        System.out.println("buys at " + a + ", sells at " + b + ", ");
        System.out.println("buys at " + s + " and sells at " + l);

        return maxProfit;
    }
}
