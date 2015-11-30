package practice.mathematics;

import java.util.ArrayList;
import java.util.List;

public class BigInteger {

	private List<Integer> values;

	public BigInteger(String value) {
		values = new ArrayList<>();
		int scale = 1;
		int tmp = 0;
		
		boolean negative = false;
		
		if (value.charAt(0) == '-') {
			negative = true;
			value = value.substring(1);
		}
		
		for (int i = value.length() - 1; i >= 0; i--) {
			tmp += (int) (value.charAt(i) - '0') * scale;
			scale *= 10;
			if (scale == 10_000 || i == 0) {
				values.add(new Integer(tmp));
				tmp = 0;
				scale = 1;
			}
		}
		
		// add digits
		int len = (values.size() / 8 + 1) * 8; // the last one is for positive/negative sign
		for (int i = values.size(); i < len; i++) {
			values.add(0);
		}

		if (negative) {
			toComplement();
		}
	}
	
	public BigInteger(BigInteger other) {
		values = new ArrayList<>(other.getValues());
	}
	
	public BigInteger(List<Integer> integerList) {
		values = new ArrayList<>();
		int len = (integerList.size() / 8 + 1) * 8;
		for (int i = 0; i < integerList.size(); i++) {
			values.add(integerList.get(i));
		}
		
		for (int i = integerList.size(); i < len; i++) {
			values.add(new Integer(0));
		}
		surplusCheck();
	}
	
	public Integer getIndex(int i) {
		return values.get(i);
	}

	public int getSize() {
		return values.size();
	}

	public BigInteger add(BigInteger other) {

		List<Integer> tmp = new ArrayList<>();
		
		int len = Math.max(getSize(), other.getSize());
		
		for (int i = 0; i < len; i++) {
			Integer result = (i < getSize() ? getIndex(i) : 0) + (i < other.getSize() ? other.getIndex(i) : 0);
			tmp.add(result);
		}
		
		values = tmp;
		surplusCheck();
		return this;
	}
	
	public BigInteger subtract(BigInteger other) {
		BigInteger otherTmp = new BigInteger(other);
		add(otherTmp.toComplement());
		return this;
	}
	
	// for internal usage 
	private BigInteger multiplyInternal(int value, int shift) { // value and shift must be positive numbers
		List<Integer> tmp = new ArrayList<>();
		
		for(int i = 0; i < shift; i++) {
			tmp.add(new Integer(0));
		}
		
		for(int i = shift; i < getSize(); i++) {
			tmp.add(new Integer(getIndex(i) * value));
		}
		
		return new BigInteger(tmp);
	}
	
	public BigInteger multiply(BigInteger other) {
		List<BigInteger> tmp = new ArrayList<>();
		
		BigInteger otherTmp = new BigInteger(other);
		boolean meNegative = isNegative();
		boolean otherNegative = other.isNegative();
		
		if (meNegative) {
			toComplement();
		}
		
		if (otherNegative) {
			otherTmp.toComplement();
		}
		
		for (int i = 0; i < otherTmp.getSize(); i++) {
			tmp.add(multiplyInternal(otherTmp.getIndex(i), i));
		}
		
		BigInteger result = new BigInteger("0");
		for (BigInteger bigInteger : tmp) {
			result.add(bigInteger);
		}
		
		values = result.getValues();
		
		surplusCheck();
		
		if (meNegative ^ otherNegative) {
			toComplement();
		}
		return this;
	}
	
	public List<Integer> getValues() {
		return values;
	}
	
	public boolean greaterThanOrEqualTo(BigInteger other) {
		BigInteger tmp = new BigInteger(this);
		return !tmp.subtract(other).isNegative();
	}
	
	private boolean lessThanOrEqualToQuotient(BigInteger op1, BigInteger op2) {
		return false;
	}

	public BigInteger toComplement() {
		for (int i = 0; i < values.size(); i++) {
			values.set(i, 9999 - values.get(i));
		}
		values.set(0, values.get(0) + 1);
		return this;
	}
	
	private void surplusCheck() {
		List<Integer> tmp = new ArrayList<>(values);
		int surplus = 0;
		values.clear();
		for (Integer integer : tmp) {
			integer += surplus;
			surplus = 0;
			if (integer >= 10_000) {
				surplus = integer / 10_000;
				integer -= 10_000 * surplus;
			}
			values.add(integer);
		}
	}
	
	public boolean isNegative() {
		return values.get(values.size() - 1) == 9999;
	}
	
	public String getListString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Integer integer : values) {
			stringBuilder.insert(0, String.format("%04d ", integer));
		}
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		boolean negative = false;

		if (values.get(values.size() - 1) == 9999) {
			stringBuilder.append("-");
			negative = true;
		}

		for (int i = values.size() - 2; i >= 0; i--) {
			if (negative) {
				stringBuilder.append(String.format("%04d ", 9999 - values.get(i) + (i == 0 ? 1 : 0)));
			} else {
				stringBuilder.append(String.format("%04d ", values.get(i)));
			}
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		BigInteger a = new BigInteger("9999999999999999");
		BigInteger b = new BigInteger("-1");
		BigInteger c = new BigInteger("-4");
		BigInteger d = new BigInteger("20");
		
		System.out.println("a + b: " + a.add(b));
		System.out.println("b - c: " + b.subtract(c));
		System.out.println("c * d: " + c.multiply(d));
	}

}
