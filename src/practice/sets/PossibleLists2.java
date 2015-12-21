package practice.sets;

import java.util.ArrayList;
import java.util.List;

public class PossibleLists2 {

    int[] numbers;
    
    List<List<Integer>> possibleLists;
    
    public PossibleLists2(int num) {
        possibleLists = new ArrayList<>();
        
        List<Integer> operatingList = new ArrayList<>();
        possibleLists.add(new ArrayList<>(operatingList));
        
        boolean flag = true;
        int op = 1;
        while (flag) {
            operatingList.add(op);
            possibleLists.add(new ArrayList<>(operatingList));
            op++;
            
            if (op > num) {
                operatingList.remove(operatingList.size() - 1);
                if (operatingList.isEmpty()) {
                    flag = false;
                }
                else {
                    int m = operatingList.remove(operatingList.size() - 1);
                    op = m + 1;
                }
            }
        }
    }
    
    public void printAllPossibleList() {
        for (List<Integer> possibleList : possibleLists) {
            System.out.println(possibleList.toString());
        }
    }
    
    
    public static void main(String[] args) {
        PossibleLists2 possibleLists2 = new PossibleLists2(5);
        possibleLists2.printAllPossibleList();
    }
    
}
