import java.util.Scanner;

class AlphabetValue {
    private String input;     // why private - as it is being accessed only in this class

    public AlphabetValue(String input) {
        this.input = input.toLowerCase(); // Normalize to lowercase
    }

    private int getValue(char c) {
        // Assign values based on the character
        if (c >= 'a' && c <= 'z') {
            switch (c) {
                case 'a':
                case 'b':
                case 'c':
                    return 1; // Assign value 1 to a, b, c
                case 'd':
                case 'e':
                case 'f':
                    return 2; // Assign value 2 to d, e, f
                case 'g':
                case 'h':
                case 'i':
                    return 3; // Assign value 3 to g, h, i
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                    return 4; // Assign value 4 to j, k, l, m
                // Continue with other letters if needed
                default:
                    return 0; // Other characters have no value
            }
        }
        return 0; // Non-alphabet characters, special character 
    }

    public String compareHalves() {
        int mid = input.length() / 2;
        String left = input.substring(0, mid);
        String right = input.substring(mid);

        int sumV1 = calculateSum(left);
        int sumV2 = calculateSum(right);

        if (sumV1 > sumV2) {
            return "left is the winner";
        } else if (sumV1 < sumV2) {
            return "right is the winner";
        } else {
            return "and that's a draw";
        }
    }

    private int calculateSum(String part) {
        int sum = 0;
        for (char c : part.toCharArray()) {
            sum += getValue(c);
        }
        return sum;
    }
}

public class WordWar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        AlphabetValue alphabetValue = new AlphabetValue(input);
        System.out.println(alphabetValue.compareHalves());
        scanner.close();
    }
}
