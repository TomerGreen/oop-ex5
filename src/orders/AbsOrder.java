package orders;

import java.io.File;

/**
 *  A subclass of the Order superclass, compare between files based on the ABC orders of their AbsolutePath.
 */
public class AbsOrder extends Order {

	@Override
	public int compare(File o1, File o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
}