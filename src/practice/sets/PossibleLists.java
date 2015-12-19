package practice.sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PossibleLists {

	private int[] elements;
	private int[] op;
	
	private List<int[]> possibleList;
	
	public PossibleLists(int[] elements) {
		this.elements = elements;
		
		generatePossibleList();
	}
	
	private void generatePossibleList() {
		possibleList = new ArrayList<>(1 << elements.length);
		op = new int[elements.length];
		possibleList.add(Arrays.copyOf(op, op.length));
		boolean done = false;
		while (!done) {
			int i = 0;
			boolean encounterZero = false;
			while (!encounterZero) {
				if (op[i] == 0) {
					op[i] = 1;
					encounterZero = true;
				}
				else {
					op[i] = 0;
					i++;
				}
				
				if (i >= op.length) {
					encounterZero = true;
					done = true;
				}
			}
			
			if (!done) {
				possibleList.add(Arrays.copyOf(op, op.length));
			}
		}
	}
	
	
	public void printAllPossibleLists() {
		List<Integer> result = new ArrayList<>();
		
		for(int[] list : possibleList) {
			for (int i = 0; i < list.length; i++) {
				if (list[i] != 0) {
					result.add(elements[i]);
				}
			}
			System.out.println(result.toString());
			result.clear();
		}
	}
	
	public static void main(String[] args) {
		PossibleLists possibleLists = new PossibleLists(new int[]{1, 2, 3, 4});
		possibleLists.printAllPossibleLists();
	}
}
