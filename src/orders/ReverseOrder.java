package orders;

import java.io.File;

/**
 * A subclass of the Order superclass, compare between files based on the opposite of the determined orders.
 */
public class ReverseOrder extends Order {

	private static final int REVERSE_FACTOR = -1;

	private Order order;

	//constructor
	public ReverseOrder(Order order) {
		this.order = order;
	}

	@Override
	public int compare(File o1, File o2) {
		return order.compare(o1,o2)*REVERSE_FACTOR;
	}
}
