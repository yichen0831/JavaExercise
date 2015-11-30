package practice.mathematics;

import java.util.ArrayList;
import java.util.Collections;
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
	// the last one is for positive/negative sign
	int len = (values.size() / 8 + 1) * 8;
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

    public int getNonZeroSize() {
	int size = getSize();
	int zeroOut = 0;

	boolean stop = false;

	while (!stop && zeroOut < size) {
	    zeroOut++;
	    if (isNegative()) {
		if (getIndex(size - zeroOut - 1) != 9999) {
		    stop = true;
		}
	    } else {
		if (getIndex(size - zeroOut - 1) != 0) {
		    stop = true;
		}
	    }
	}

	return size - zeroOut;
    }

    public BigInteger add(BigInteger other) {

	List<Integer> tmp = new ArrayList<>();

	int len = Math.max(getSize(), other.getSize());

	if (getSize() < len) {
	    increaseZeroOut(len);
	}

	if (other.getSize() < len) {
	    other.increaseZeroOut(len);
	}

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

    private void increaseZeroOut(int length) {

	if (length > getSize()) {
	    while (length > getSize()) {
		values.add(getIndex(getSize() - 1));
	    }

	    if (length % 8 != 0) {
		for (int i = 0; i < 8 - (length % 8); i++) {
		    values.add(getIndex(getSize() - 1));
		}
	    }
	}

    }

    // for internal usage
    private BigInteger multiplyInternal(int value, int shift) {
	// value and shift must be positive numbers
	List<Integer> tmp = new ArrayList<>();

	for (int i = 0; i < shift; i++) {
	    tmp.add(new Integer(0));
	}

	for (int i = shift; i < getSize(); i++) {
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

    private BigInteger divideInternal(int value) {
	// both this and value must be positive numbers
	List<Integer> tmp = new ArrayList<>();

	int reminder = 0;
	for (int i = getSize() - 1; i >= 0; i--) {
	    int number = getIndex(i) + reminder;
	    tmp.add(new Integer(number / value));
	    reminder = (number % value) * 10_000;
	}

	Collections.reverse(tmp);
	values = tmp;

	return this;
    }

    public BigInteger divide(BigInteger other) {
	BigInteger op1 = new BigInteger(this);
	BigInteger op2 = new BigInteger(other);

	boolean op1Negative = isNegative();
	boolean op2Negative = other.isNegative();

	if (op1Negative) {
	    op1.toComplement();
	}

	if (op2Negative) {
	    op2.toComplement();
	}

	BigInteger one = new BigInteger("1");
	BigInteger left = new BigInteger("0");
	BigInteger right = new BigInteger(op1);

	while (right.greaterThanOrEqualTo(left)) {
	    BigInteger x = new BigInteger(left).add(right).divideInternal(2);
	    if (x.lessThanOrEqualToQuotient(op1, op2)) {
		left = x.add(one);
	    } else {
		right = x.subtract(one);
	    }
	}

	values = left.subtract(one).getValues();

	if (op1Negative ^ op2Negative) {
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
	BigInteger tmp = new BigInteger(this);
	return op1.greaterThanOrEqualTo(tmp.multiply(op2));
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
	BigInteger d = new BigInteger("3600");
	BigInteger e = new BigInteger("-4");

	System.out.println("a + b: " + a.add(b));
	System.out.println("b - c: " + b.subtract(c));
	System.out.println("c * d: " + c.multiply(d));
	System.out.println("d / e: " + d.divide(e));
    }

}
