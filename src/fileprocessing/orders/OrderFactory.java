package fileprocessing.orders;

import fileprocessing.exceptions.Type1ErrorException;

/**
 * A factory class for creating the right kind of orders based on a
 * orders command line.
 */
public class OrderFactory {

	private static final int ORDER_TYPE_INDEX = 0;
	private static final String SEPARATE_SIGN = "#";

	public static Order generateOrder(String line) throws InvalidOrderNameException{
		String[] orderFields = line.split(SEPARATE_SIGN);
		Order order;
		if(orderFields[ORDER_TYPE_INDEX].equals("abs")){
			order = new AbsOrder();
		}
		else if(orderFields[ORDER_TYPE_INDEX].equals("type")){
			order = new TypeOrder();
		}
		else if(orderFields[ORDER_TYPE_INDEX].equals("size")){
			order = new SizeOrder();
		}
		else {
			throw new InvalidOrderNameException();
		}
		if(orderFields[orderFields.length-1].equals("REVERSE")){
			order = new ReverseOrder(order);
		}
		return order;
	}

	/*
	 *
	 */
	private static class InvalidOrderNameException extends Type1ErrorException {}
}
