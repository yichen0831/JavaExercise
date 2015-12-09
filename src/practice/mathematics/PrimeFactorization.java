package practice.mathematics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeFactorization {
	
	private PrimeFactorization() {}

	public static Map<Integer, Integer> primeFactorize(int number) {
		Map<Integer, Integer> primeFactorMap = new HashMap<>();
		
		SieveOfEratosthenes soe= new SieveOfEratosthenes(number);
		List<Integer> primeList = soe.getPrimeList();
		
		for(int i : primeList) {
			int count = 0;
			while(number % i == 0) {
				number /= i;
				count++;
				primeFactorMap.put(i, count);
			}
		}
		
		return primeFactorMap;
	}
	
	public static void printPrimeFactors(Map<Integer, Integer> map) {
		StringBuffer stringBuffer = new StringBuffer();
		
		if (map.isEmpty()) {
			System.out.println("No prime factors.");
			return;
		}
		
		for (int k : map.keySet()) {
			stringBuffer.append(k + "^" + (int)map.get(k)).append("+");
		}
		stringBuffer.setLength(stringBuffer.length() - 1);
		
		System.out.println(stringBuffer.toString());
	}
	
	public static void main(String[] args) {
		Map<Integer, Integer> map = primeFactorize(135);
		printPrimeFactors(map);
	}
}
