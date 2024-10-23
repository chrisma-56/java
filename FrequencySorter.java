import java.util.*;

public class FrequencySorter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Step 1: Take array size and elements from user
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        
        int[] array = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        
        // Step 1 (continued): Take value for k from user
        System.out.print("Enter the value for k: ");
        int k = scanner.nextInt();
        
        // Step 2: Retrieve unique elements and their frequencies using HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 3: Create a list from the frequency map
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        
        // Step 4: Sort the list by frequency (and value if frequencies are the same)
        entryList.sort((a, b) -> {
            int freqCompare = b.getValue().compareTo(a.getValue());
            if (freqCompare == 0) {
                return b.getKey().compareTo(a.getKey());
            }
            return freqCompare;
        });
        
        // Step 5: Retrieve the first k elements and group by frequency
        StringBuilder result = new StringBuilder();
        int count = 0;
        
        for (Map.Entry<Integer, Integer> entry : entryList) {
            if (count >= k) break; // Stop if we've collected k elements
            
            // Get frequency and the value
            int frequency = entry.getValue();
            List<Integer> sameFreqElements = new ArrayList<>();
            
            // Collect all elements with the same frequency
            for (Map.Entry<Integer, Integer> innerEntry : entryList) {
                if (innerEntry.getValue() == frequency) {
                    sameFreqElements.add(innerEntry.getKey());
                }
            }
            
            // Sorting
            Collections.sort(sameFreqElements, Collections.reverseOrder());
            for (Integer element : sameFreqElements) {
                if (count < k) {
                    result.append(element).append(" ");
                    count++;
                } else {
                    break;
                }
            }
        }
        
        // Step 6: Return the result by concatenating the subset
        System.out.println("Result: " + result.toString().trim());
        scanner.close();
    }
}
