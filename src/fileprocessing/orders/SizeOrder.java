package fileprocessing.orders;

import java.io.File;

/**
 *  A subclass of the Order superclass, compare between files based on their file size, going from smallest
 *  to largest.
 */
public class SizeOrder extends Order {

	//constant for even values
	private static final int EQUAL_SIZE = 0;

	private AbsOrder absOrder;

	//constructor
	public SizeOrder() {
		absOrder = new AbsOrder();
	}

	@Override
	public int compare(File o1, File o2) {
		int compareValue = (int)(o1.length()- o2.length());
		if (compareValue != EQUAL_SIZE){
			return compareValue;
		}
		else {
			return absOrder.compare(o1,o2);
		}
	}
}
