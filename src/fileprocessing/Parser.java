package fileprocessing;

import filters.*;
import orders.AbsOrder;
import orders.Order;
import orders.OrderFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Parses a command file into a list of section objects.
 * A parser object is associated with a file via a BufferedReader object, and saves
 * a linked list of warnings for errors encountered during parsing.
 */
public class Parser {

    /** The file reader object being parsed. */
    private BufferedReader lineReader;

    /** The current line being read. */
    private String currLine;

    /** The number of the current line. */
    private int lineCounter;

    /** A list of Type I warnings thrown when parsing the command file. */
    private LinkedList<String> warnings;

    /**
     * Creates a parser object.
     * @param filepath The associated text file.
     * @throws FileNotFoundException
     * @throws InvalidCommandFileException
     */
    public Parser(String filepath) throws FileNotFoundException, InvalidCommandFileException {
        lineCounter = 0;
        lineReader = new BufferedReader(new FileReader(filepath));
        this.advanceLine();
    }

    /**
     * @return The warnings list.
     */
    public LinkedList<String> getWarnings() {
        return warnings;
    }

    /**
     * Advances to the next line.
     * @throws InvalidCommandFileException
     */
    private void advanceLine() throws InvalidCommandFileException {
        try {
            currLine = lineReader.readLine();
        }
        catch (IOException e) {
            throw new InvalidCommandFileException("ERROR: Could not read line " + Integer.toString(lineCounter)
                    + " in command file.");
        }
        lineCounter++;
    }

    /**
     * Parses the associated command file into a linked list of Section objects.
     * @return The Section list.
     * @throws MissingSubsectionException
     * @throws InvalidCommandFileException
     */
    public LinkedList<Section> parseCommandFile() throws MissingSubsectionException,
            InvalidCommandFileException {
        LinkedList<Section> sections = new LinkedList<>();
        while (currLine != null) {
            if (currLine != "FILTER") {
                throw new MissingSubsectionException("ERROR: FILTER sub-section missing.");
            }
            advanceLine();
            Filter currFilter = parseFilterLine();
            advanceLine();
            if (currLine != "ORDER") {
                throw new MissingSubsectionException("ERROR: ORDER sub-section missing.");
            }
            Order currOrder = parseOrderLine();
            Section currSection = new Section(currFilter, currOrder);
            sections.add(currSection);
            advanceLine();
        }
        return sections;
    }

    /**
     * Parses the current command line into a filter object.
     * @return A corresponding filter object.
     */
    private Filter parseFilterLine() {
        try {
            return FilterFactory.generateFilter(currLine);
        }
        catch (BooleanFilter.InvalidBooleanFilterValueException
                | FilterFactory.InvalidFilterNameException
                | SizeFilter.InvalidSizeLimitException e) {
            warnings.add("Warning in line " + Integer.toString(lineCounter));
            return new AllFilter();
        }
    }

    /**
     * Parse the current command line into an Order object.
     * @return A corresponding order object.
     */
    private Order parseOrderLine() {
        try {
            return OrderFactory.generateOrder(currLine);
        }
        catch (OrderFactory.InvalidOrderNameException e) {
            warnings.add("Warning in line " + Integer.toString(lineCounter));
            return new AbsOrder();
        }
    }

    /** Thrown when an ORDER or FILTER line does not appear where it should. */
    private class MissingSubsectionException extends Exception {
        public MissingSubsectionException(String message) {
            super(message);
        }
    }

    /** Thrown when a line in the command file cannot be read by the FileReader. */
    private class InvalidCommandFileException extends Exception {
        public InvalidCommandFileException(String message) {
            super(message);
        }
    }

}