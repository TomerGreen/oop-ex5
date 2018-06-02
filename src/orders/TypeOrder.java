package orders;
import java.io.File;

/**
 *  A subclass of the Order superclass,  compare between files based on their file type, going from ‘a’ to
 *  ‘z’.
 */
public class TypeOrder extends Order {

	//constant for even values
	private static final int EQUAL_TYPE = 0;

	private AbsOrder absOrder;

	//constructor
	public TypeOrder() {
		absOrder = new AbsOrder();
	}

	@Override
	public int compare(File o1, File o2) {
		int compareValue = getType(o1).compareTo(getType(o2));
		if(compareValue != EQUAL_TYPE){
			return compareValue;
		}
		else {
			return absOrder.compare(o1, o2);
		}
	}
	/*
	 * return the type of a file.
	 */
	private String getType(File file){
		String fileName = file.getName();
		int indexOfLastDot = fileName.lastIndexOf('.');
		if(indexOfLastDot < fileName.length() && indexOfLastDot > 0){
			return fileName.substring(indexOfLastDot + 1);
		}
		return "";
	}
}