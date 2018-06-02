package fileprocessing;

import fileprocessing.filters.Filter;
import fileprocessing.orders.Order;

/**
 * Implements a command section, containing a filter and an order clause.
 */
public class Section {

    /** The filter object. */
    private Filter filter;

    /** The order object */
    private Order order;

    public Section(Filter filter, Order order) {
        this.filter = filter;
        this.order = order;
    }
}