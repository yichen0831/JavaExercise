package practice.sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayCode {

	private List<int[]> grayCodeList;
	private int size;
	
	public GrayCode(int size) {
		this.size = size;
		grayCodeList = new ArrayList<>(1 << size);
		makeGrayCode();
	}
	
	public void makeGrayCode() {
		int total = 1 << (size);
		grayCodeList.add(new int[size]);
		
		int[] prev = grayCodeList.get(0);
		for (int i = 1; i < total; i++) {
			int[] op = Arrays.copyOf(prev, size);
			
			if ((i - 1) % 2 == 0) {
				op[size - 1] = prev[size - 1] == 0 ? 1 : 0;
			}
			else {
				// find the first 1 from the right(last)
				int j = size - 1;
				while(j > 0 && prev[j] != 1) {
					j--;
				}
				if (j - 1 < 0) {
					j = size;
				}
				op[j - 1] = prev[j - 1] == 0 ? 1 : 0;
			}
			grayCodeList.add(op);
			prev = op;
		}
		
	}
	
	public void printResult() {
		for (int[] result : grayCodeList) {
			System.out.println(Arrays.toString(result));
		}
	}
	
	public static void main(String[] args) {
		GrayCode grayCode = new GrayCode(4);
		grayCode.printResult();
	}
}
