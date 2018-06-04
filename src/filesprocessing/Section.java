package filesprocessing;

import filesprocessing.filters.Filter;
import filesprocessing.orders.Order;

/**
 * Implements a command section, containing a filter and an order clause.
 */
public class Section {

    /** The filter object. */
    private Filter filter;

    /** The order object */
    private Order order;

    /**
     * Creates a section object.
     * @param filter The section's filter.
     * @param order The section's order.
     */
    public Section(Filter filter, Order order) {
        this.filter = filter;
        this.order = order;
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

}