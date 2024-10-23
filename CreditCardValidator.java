import java.util.Scanner;

public class CreditCardValidator {
    private int ccNumber; 

    // Constructor to initialize 
    public CreditCardValidator(int ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void validate() {
        
        String ccNumberStr = Integer.toString(ccNumber);

        // checking length of the credit card number
        if (ccNumberStr.length() < 8 || ccNumberStr.length() > 9) {
            System.out.println("Invalid credit card number");
            return;
        }

        // 
        //  a: Remove the last digit
        char lastDigitChar = ccNumberStr.charAt(ccNumberStr.length() - 1);
        int lastDigit = Character.getNumericValue(lastDigitChar);
        String numberWithoutLastDigit = ccNumberStr.substring(0, ccNumberStr.length() - 1);

        //b: Reverse the remaining digits
        String reversedNumber = new StringBuilder(numberWithoutLastDigit).reverse().toString();

        // c: Double the digits in odd positions (1st, 3rd, 5th)
        int sum = 0;
        for (int i = 0; i < reversedNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedNumber.charAt(i));
            // Double the digit if it's in an odd position (0-indexed)
            if (i % 2 == 0) {
                digit *= 2;
                // If the result is a double-digit number, add the digits
                if (digit > 9) {
                    digit = digit / 10 + digit % 10; // Sum the digits
                }
            }
            sum += digit; // Step d: Add to the sum
        }

        // Step e: Subtract the last digit from 10
        int adjustedValue = (10 - lastDigit) % 10;

        // Step f: Compare the result with the last digit
        if (adjustedValue == lastDigit) 
        {
            System.out.println("The credit card number is valid.");
        } 
        else 
        {
            System.out.println("The credit card number is invalid.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
      
        System.out.print("Enter the credit card number: ");
        int ccNumber = scanner.nextInt();

        //  constructor
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        
        
        validator.validate();
        
        scanner.close();
    }
}

