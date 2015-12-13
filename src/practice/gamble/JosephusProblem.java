package practice.gamble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JosephusProblem {

    private final int guyCount = 41;
    private final int count = 3;
    private List<Integer> guys;
    private List<Integer> result;
    
    public JosephusProblem() {
       guys = new ArrayList<>(); 
       
       for(int i = 1; i <= guyCount; i++) {
           guys.add(i);
       }
       
       result = getResult();
    }
    
    public List<Integer> getResult() {
        List<Integer> result = new ArrayList<>();
        
        int i = 1;
        Iterator<Integer> iter = guys.iterator();
        while (!guys.isEmpty()) {
            
            if (i % count == 0) {
                result.add(iter.next());
                iter.remove();
            }
            else {
                iter.next();
            }

            if (!iter.hasNext()) {
                iter = guys.iterator();
            }
            i++;
        }
        
        return result;
    }
    
    public void printResult() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (Iterator<Integer> i = result.iterator(); i.hasNext();) {
            stringBuilder.append(i.next());
            if (i.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        
        System.out.println(stringBuilder.toString());
    }
    
    public static void main(String[] args) {
        JosephusProblem josephusProblem = new JosephusProblem();
        josephusProblem.printResult();
    }
}
