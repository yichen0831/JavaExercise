package practice.sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {

    List<int[]> resultList;
    
    public Combination(int m, int n) {
        resultList = new ArrayList<>();

        int[] operatingArray = new int[n];
        // initialize operatingArray
        for (int i = 1; i <= n; i++) {
            operatingArray[i - 1] = i;
        }
        
        resultList.add(Arrays.copyOf(operatingArray, n));
        
        boolean flag = true;
        while (flag) {
            
            if (operatingArray[n - 1] < m) {
                operatingArray[n - 1]++;
                resultList.add(Arrays.copyOf(operatingArray, n));
            }
            else {
                // find the position to replace
                int pos = -1;
                for (int i = n - 2; i >= 0; i--) {
                    if (operatingArray[i + 1] - operatingArray[i] > 1) {
                        pos = i;
                        break;
                    }
                }
                
                if (pos == -1) {
                    flag = false;
                }
                else {
                    int v = operatingArray[pos];
                    for (int i = pos; i < n; i++) {
                        operatingArray[i] = ++v;
                    }
                    resultList.add(Arrays.copyOf(operatingArray, n));
                }
            }
        }
        
    }
    
    public void printResult() {
        for (int[] result : resultList) {
            System.out.print("[");
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]);
                if (i < result.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
        System.out.println("Total: " + resultList.size());
    }
    
    public static void main(String[] args) {
        Combination combination = new Combination(5, 3);
        combination.printResult();
    }
}
