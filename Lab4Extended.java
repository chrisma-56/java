import java.util.Scanner;

abstract class Robber {
    // Abstract methods
    abstract int RowHouse(int[] arr1);   
    abstract int CircleHouse(int[] arr2); 
    abstract int SquareHouse(int[] arr3); 
    abstract int MuliHouseBuilding(int[] arr4); 

    // Static method 
    static void RobberClass() {
        System.out.println("MSc AIML");
    }
}

interface Interface1 {
    // Default method 
    default void MachineLearning() {
        System.out.println("I love Machine Learning");
    }
}

class JavaProffessionalRobber extends Robber implements Interface1
 {
    // Max money robbed from row houses (non-adjacent)
    public int RowHouse(int[] arr1) {
        if (arr1.length == 0) 
        return 0;
        if (arr1.length == 1) 
        return arr1[0];
        
        int prevMax = 0, currMax = arr1[0];
        
        for (int i = 1; i < arr1.length; i++) {
            int newMax = Math.max(currMax, prevMax + arr1[i]);
            prevMax = currMax;
            currMax = newMax;
        }
        
        return currMax;
    }

    //  circular houses 
    public int CircleHouse(int[] arr2) {
        if (arr2.length == 0) 
        return 0;
        if (arr2.length == 1) 
        return arr2[0];
        if (arr2.length == 2) 
        return Math.max(arr2[0], arr2[1]);

        // Max of robbing the houses without first one or last one
        int excludeFirst = rob(arr2, 0, arr2.length - 1);
        int excludeLast = rob(arr2, 1, arr2.length);

        return Math.max(excludeFirst, excludeLast);
    }

    private int rob(int[] arr, int start, int end) {
        int prevMax = 0, currMax = arr[start];
        
        for (int i = start + 1; i < end; i++) {
            int newMax = Math.max(currMax, prevMax + arr[i]);
            prevMax = currMax;
            currMax = newMax;
        }
        
        return currMax;
    }

    //
    public int SquareHouse(int[] arr3) {
        return RowHouse(arr3);  // RowHouse logic
    }

    
    public int MuliHouseBuilding(int[] arr4) {
        return RowHouse(arr4);  //  RowHouse logic
    }
}

public class Lab4Extended {
    public static void main(String[] args) {
        
           
        Scanner scan_obj = new Scanner(System.in);

        // Input the arrays
        System.out.println("Enter size then the array for row houses");
        int size = scan_obj.nextInt();
        int[] arr1 = new int[size];
        System.out.println("Enter the elements for row houses:");
        for (int i = 0; i < size; i++) {
            arr1[i] = scan_obj.nextInt();
        }

        System.out.println("Enter the size of circle houses:");
        int size1 = scan_obj.nextInt();
        int[] arr2 = new int[size1];
        System.out.println("Enter the elements for circle houses:");
        for (int i = 0; i < size1; i++) {
            arr2[i] = scan_obj.nextInt();
        }

        System.out.println("Enter the size of square houses:");
        int size2 = scan_obj.nextInt();
        int[] arr3 = new int[size2];
        System.out.println("Enter the elements for square houses:");
        for (int i = 0; i < size2; i++) {
            arr3[i] = scan_obj.nextInt();
        }

        System.out.println("Enter the size of multiple house types:");
        int size3 = scan_obj.nextInt();
        int[] arr4 = new int[size3];
        System.out.println("Enter the elements for multi-type building houses:");
        for (int i = 0; i < size3; i++) {
            arr4[i] = scan_obj.nextInt();
        }
        // object of JavaProfessionalRobber
        JavaProffessionalRobber obj = new JavaProffessionalRobber();
        
    
        // Calling the methods for each house type
        int rowHouseMoney = obj.RowHouse(arr1);
        System.out.println("Max money robbed from Row Houses: " + rowHouseMoney);

        int circleHouseMoney = obj.CircleHouse(arr2);
        System.out.println("Max money robbed from Circle Houses: " + circleHouseMoney);

        int squareHouseMoney = obj.SquareHouse(arr3);
        System.out.println("Max money robbed from Square Houses: " + squareHouseMoney);

        int multiHouseMoney = obj.MuliHouseBuilding(arr4);
        System.out.println("Max money robbed from Multi-House Building: " + multiHouseMoney);

        // Print RobberClass message
        Robber.RobberClass();

        // Print MachineLearning message from interface
        obj.MachineLearning();
        
    }
}
