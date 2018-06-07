package filesprocessing;

import filesprocessing.orders.*;
import filesprocessing.filters.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Parses a command file into a list of section objects.
 * A parser object is associated with a file via a BufferedReader object, and saves
 * a linked list of sections and warnings.
 */
public class CommandParser {

    private static final String FILE_NOT_FOUND_MSG = "Could not find command file with path ";
    private static final String FILTER_SUBSECTION_MISSING_MSG = "FILTER sub-section missing.";
    private static final String ORDER_SUBSECTION_MISSING_MSG = "ORDER sub-section missing.";

    /* The file reader object being parsed. */
    private BufferedReader lineReader;

    /* The current line being read. */
    private String currLine;

    /* The line after the current line. */
    private String firstNextLine;

    /* Two lines ahead of the current line. */
    private String secondNextLine;

    /* The number of the current line. */
    private int lineCounter;

    /* Filter and Order warnings in the current section. */
    private String filterWarning;
    private String orderWarning;

    /* A list of sections parsed from the command file. */
    private LinkedList<Section> sections;

    /**
     * Creates a parser object.
     * @param filepath The associated text file.
     * @throws CommandFileNotFoundException When the command file is not found.
     * @throws InvalidCommandFileException When one of the lines in the command file cannot be read.
     */
    public CommandParser(String filepath) throws CommandFileNotFoundException, InvalidCommandFileException,
            MissingSubsectionException {
        lineCounter = 1;
        try {
            lineReader = new BufferedReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new CommandFileNotFoundException(FILE_NOT_FOUND_MSG + filepath);
        }
        try {
            currLine = lineReader.readLine();
            firstNextLine = lineReader.readLine();
            secondNextLine = lineReader.readLine();
        }
        catch (IOException e) {
            throw new InvalidCommandFileException();
        }
        sections = parseCommandFile();
    }

    /**
     * @return The parsed sections list.
     */
    public LinkedList<Section> getSections() {
        return sections;
    }

    /*
     * Advances to the next line.
     * @throws InvalidCommandFileException
     */
    private void advanceLine() throws InvalidCommandFileException {
        try {
            currLine = firstNextLine;
            firstNextLine = secondNextLine;
            secondNextLine = lineReader.readLine();
        }
        catch (IOException e) {
            throw new InvalidCommandFileException();
        }
        lineCounter++;
    }

    /**
     * Parses the associated command file into a linked list of Section objects.
     * The method expects that when called currLine is the first line in the command file.
     * @return The Section list.
     * @throws MissingSubsectionException
     * @throws InvalidCommandFileException
     */
    public LinkedList<Section> parseCommandFile() throws MissingSubsectionException,
            InvalidCommandFileException {
        LinkedList<Section> sections = new LinkedList<>();
        while (currLine != null) {
            if (!currLine.equals("FILTER")) {
                throw new MissingSubsectionException(FILTER_SUBSECTION_MISSING_MSG);
            }
            advanceLine();
            Filter currFilter = parseFilterLine();
            advanceLine();
            if (currLine == null || !currLine.equals("ORDER")) {
                throw new MissingSubsectionException(ORDER_SUBSECTION_MISSING_MSG);
            }
            advanceLine();
            Order currOrder = parseOrderLine();
            Section currSection = new Section(currFilter, currOrder);
            currSection.addWarning(filterWarning);
            currSection.addWarning(orderWarning);
            filterWarning = null;
            orderWarning = null;
            sections.add(currSection);
        }
        return sections;
    }

    /*
     * Parses the current command line into a filter object.
     * @return A corresponding filter object.
     */
    private Filter parseFilterLine() {
        try {
            return FilterFactory.generateFilter(currLine);
        }
        catch (Type1ErrorException e) {
            filterWarning = "Warning in line " + Integer.toString(lineCounter);
            return new AllFilter();
        }
    }

    /*
     * Parse the current command line into an Order object.
     * @return A corresponding order object.
     */
    private Order parseOrderLine() throws InvalidCommandFileException {
        Order order;
        try {
            // Order title is last line in file.
            if (currLine == null) {
                return new AbsOrder();
            }
            // Current line could be a filter title or an invalid order name.
            else if (currLine.equals("FILTER")) {
                // Current line is FILTER title, apparently..
                if (firstNextLine == null) {
                    return new AbsOrder();
                }
                // Current line could be subsection title or invalid order name.
                else if (firstNextLine.equals("FILTER")) {
                    // Current line is subsection title.
                    if (secondNextLine == null) {
                        return new AbsOrder();
                    }
                    // Current line is invalid order name.
                    else {
                        order = OrderFactory.generateOrder(currLine);
                    }
                }
                // Current line is subsection title.
                else {
                    return new AbsOrder();
                }
            }
            else {
                order = OrderFactory.generateOrder(currLine);
            }
        }
        catch (Type1ErrorException e) {
            orderWarning = "Warning in line " + Integer.toString(lineCounter);
            advanceLine();
            return new AbsOrder();
        }
        //System.out.println("Advancing past order line");
        advanceLine();
        return order;
    }

    /* Thrown when an ORDER or FILTER line does not appear where it should. */
    private class MissingSubsectionException extends Type2ErrorException {
        public MissingSubsectionException(String message) { super(message); }
    }

    /* Thrown when a line in the command file cannot be read by the FileReader. */
    private class InvalidCommandFileException extends Type2ErrorException {
        public InvalidCommandFileException() {
            super("ERROR: Could not read line " + Integer.toString(lineCounter)
                    + " in command file.");
        }
    }

    /* Thrown when the command file is not found. */
    private class CommandFileNotFoundException extends Type2ErrorException {
        public CommandFileNotFoundException(String message) { super(message); }
    }

}