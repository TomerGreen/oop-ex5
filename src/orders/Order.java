package orders;

import java.util.Comparator;
import java.io.File;

/**
 *  The order in which the filtered files are printed.
 *  it is an abstract class, and extends Comparator of java
 */
public abstract class Order implements Comparator<File>{
}