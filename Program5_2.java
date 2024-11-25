import java.util.Scanner;
interface WaterConservationSystem
{
    int calculateTrappedWater(int[] blockHeights);
}
abstract class RainySeasonConservation implements WaterConservationSystem
{
    int calculateTrappedWater(int[] blockHeights)
    {
        int arr_size = blockHeights.length;
        //logic to calculate how much water can be trapped
        {
            if(blockHeights[0] == 0 || blockHeights[arr_size - 1] == 0) // a better condition can be suited for this
            System.out.println("first and last element can't be zero");
            return -1;

        }
    // Logic to calculate water trapping
    int unit_storage = (arr_size - 1) * 4;
    int min_height = Math.min(blockHeights[0], blockHeights[arr_size - 1]);

    int sum = 0;
    for (int i = 1; i < arr_size - 1; i++) {
        sum += blockHeights[i];
    }

    int unit_removal = (arr_size - 2) * min_height - sum;

    return unit_storage + unit_removal;
    }

    //FOR Various block configurations(conditions)
}

class CityBlockConservation extends RainySeasonConservation
{
    //Implement calculate trapped water method
}

class Program5_2
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in); // for inputing the array
        System.out.println("Enter the size of the array : ");
        int arr_size = 0;
        if(scan.hasNextInt())
        {
            arr_size = scan.nextInt();
        }

        int[] blockHeights = new int[arr_size];
        
        System.out.println("Enter the element of the array : ");
        for (int i =0; i<arr_size ; i++)
        {
            if(scan.hasNextInt())
            {
                blockHeights[i] = scan.nextInt();
            }
        }
        CityBlockConservation cityConservation = new CityBlockConservation();
        int result = cityConservation.calculateTrappedWater(blockHeights);

        if (result >= 0) 
        {
            System.out.println("The total water trapped is: " + result + " units.");
        }

    }
}
