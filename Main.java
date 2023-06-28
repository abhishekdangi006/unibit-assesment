import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the array elements
        System.out.print("Enter the array elements (comma-separated): ");
        String[] arrStr = scanner.nextLine().split(",");
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i].trim());
        }

        // Read the target value
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();
        
        List<List<Integer>> firstCombination = findCombinations(arr, target);

        System.out.println("First Combination For " + target + " : " + firstCombination);

        int[] mergedArr = mergeArray(firstCombination);
        System.out.println("Merge Into a single Array: " + Arrays.toString(mergedArr));

        int doubleTarget = target * 2;
        List<List<Integer>> secondCombination = findCombinations(mergedArr, doubleTarget);
        System.out.println("Second Combination For " + doubleTarget + " : " + secondCombination);
    }

    public static List<List<Integer>> findCombinations(int[] arr, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();
        List<List<Integer>> combinations = new ArrayList<>();

        for (int num : arr) {
            if (complementMap.containsKey(num)) {
                combinations.add(Arrays.asList(num, complementMap.get(num)));
            }
            int complement = target - num;
            complementMap.put(complement, num);
        }

        return combinations;
    }

    public static int[] mergeArray(List<List<Integer>> combinations) {
        int size = 0;
        for (List<Integer> combination : combinations) {
            size += combination.size();
        }

        int[] mergedArray = new int[size];
        int index = 0;
        for (List<Integer> combination : combinations) {
            for (int num : combination) {
                mergedArray[index++] = num;
            }
        }

        Arrays.sort(mergedArray);
        return mergedArray;
    }
}
