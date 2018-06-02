package fileprocessing;

import filters.Filter;

/**
 * Implements a command section, containing a filter and an order clause.
 */
public class Section {

    /** The filter object. */
    private Filter filter;

    public Section(Filter filter) {
        this.filter = filter;
    }
}