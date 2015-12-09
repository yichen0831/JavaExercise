package practice.mathematics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class PerfectNumber {

	private PerfectNumber() {}
	
	public static List<Integer> getPerfectNumbers(int number) {
		List<Integer> perfectNumberList = new ArrayList<>();
		for (int i = 2; i <= number; i++) {
			if (isPerfectNumber(i)) {
				perfectNumberList.add(i);
			}
		}
		return perfectNumberList;
	}
	
	public static boolean isPerfectNumber(int number) {
		Map<Integer, Integer> primeFactorMap = PrimeFactorization.primeFactorize(number);
		List<Integer> toMultiply = new ArrayList<>();
		
		for (Integer k : primeFactorMap.keySet()) {
			int pow = 1;
			int sum = 1;
			
			for(int i = 0; i < primeFactorMap.get(k); i++) {
				pow *= k;
				sum += pow;
			}
			
			toMultiply.add(sum);
		}
		
		if (toMultiply.isEmpty()) {
			return false;
		}
		
		Optional<Integer> result = toMultiply.stream().reduce(new BinaryOperator<Integer>() {
			
			@Override
			public Integer apply(Integer arg0, Integer arg1) {
				return arg0 * arg1;
			}
		});
		
		return result.get() == number * 2;
	}
	
	public static void main(String[] args) {
		List<Integer> perfectNumbers = getPerfectNumbers(10_000);
		
		System.out.println(perfectNumbers.toString());
		
	}
}
