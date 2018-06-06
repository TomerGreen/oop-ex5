package filesprocessing;

import filesprocessing.filters.Filter;
import filesprocessing.orders.Order;

import java.util.LinkedList;

/**
 * Implements a command section, containing a filter and an order clause.
 */
public class Section {

    /** The filter object. */
    private Filter filter;

    /** The order object */
    private Order order;

    /** The section's warnings. */
    private LinkedList<String> warnings;

    /**
     * Creates a section object.
     * @param filter The section's filter.
     * @param order The section's order.
     */
    public Section(Filter filter, Order order) {
        this.filter = filter;
        this.order = order;
        warnings = new LinkedList<>();
    }

    /**
     * @return The filter.
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * @return The order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Adds a warning to the list.
     * @param warning The warning message.
     */
    public void addWarning(String warning) {
        warnings.add(warning);
    }

    /**
     * @return The section warnings.
     */
    public LinkedList<String> getWarnings() {
        return warnings;
    }
}