package practice.sorting;

import java.util.Arrays;
import java.util.Random;

public class BasicSortings {

    public static int[] selectionSort(int[] numbers) {
        int[] list = Arrays.copyOf(numbers, numbers.length);
        int index = 0;
        
        while (index < list.length - 1) {
            int smallest = index;
            // find the smallest
            for (int i = index + 1; i < list.length; i++) {
                if (list[i] < list[smallest]) {
                    smallest = i;
                }
            }
            
            // swap index and smallest
            int tmp = list[index];
            list[index] = list[smallest];
            list[smallest] = tmp;
            
            index++;
        }
        
        return list;
    }
    
    public static int[] insertionSort(int[] numbers) {
        int[] list = Arrays.copyOf(numbers, numbers.length);
        
        int sorted = 0;
        
        while (sorted < list.length) {
            int toSort = list[sorted];
            int pos = 0;
            for (int i = 0; i <= sorted; i++) {
                pos = i;
                if (toSort < list[pos]) {
                    break;
                }
            }
            
            // make room for the toSort number
            for (int i = sorted; i > pos; i--) {
                list[i] = list[i - 1];
            }
            
            // insert
            list[pos] = toSort;
            
            sorted++;
        }
        
        return list;
    }
    
    public static int[] bubbleSort(int[] numbers) {
        int[] list = Arrays.copyOf(numbers, numbers.length);
        
        int sorted = 0;
        
        while (sorted < list.length) {
            
            for (int i = 0; i < list.length - sorted - 1; i++) {
                if (list[i] > list[i + 1]) {
                    // swap
                    int tmp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = tmp;
                }
            }
            
            sorted++;
        }
        
        return list;
    }
    
    
    public static int[] makeRandomList(int length) {
        int[] list = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            list[i] = random.nextInt(length * 1000);
        }
        
        return list;
    }
    
    
    public static void main(String[] args) {
        int[] numbers = makeRandomList(1000);
        
        System.out.println("Original list:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("===========================================");
        
        int[] result;
        
        long run = System.currentTimeMillis();
        result = selectionSort(numbers);
        System.out.println("SelectionSort took " + (System.currentTimeMillis() - run) + " milliseconds");
        System.out.println(Arrays.toString(result));
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        run = System.currentTimeMillis();
        result = insertionSort(numbers);
        System.out.println("InsertionSort took " + (System.currentTimeMillis() - run) + " milliseconds");
        System.out.println(Arrays.toString(result));
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        run = System.currentTimeMillis();
        result = bubbleSort(numbers);
        System.out.println("BubbleSort took " + (System.currentTimeMillis() - run) + " milliseconds");
        System.out.println(Arrays.toString(result));
    }
}
