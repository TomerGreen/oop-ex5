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
public class CommandParser  implements FileFilter {

    private static final String FILTER_SUBSECTION_TITLE = "FILTER";
    private static final String ORDER_SUBSECTION_TITLE = "ORDER";
    private static final String FILE_NOT_FOUND_MSG = "Could not find command file with path ";
    private static final String FILTER_SUBSECTION_MISSING_MSG = "FILTER sub-section missing.";
    private static final String ORDER_SUBSECTION_MISSING_MSG = "ORDER sub-section missing.";

    /* The file reader object being parsed. */
    private BufferedReader lineReader;

    /* The current line being read. */
    private String currLine;

    /* The number of the current line. */
    private int lineCounter;

    /* A list of Type I warnings thrown when parsing the command file. */
    private LinkedList<String> warnings;

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
        warnings = new LinkedList<>();
        lineCounter = 0;
        try {
            lineReader = new BufferedReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new CommandFileNotFoundException(FILE_NOT_FOUND_MSG + filepath);
        }
        this.advanceLine();
        sections = parseCommandFile();
    }

    @Override
    public boolean accept(File file) {
        if (!file.isFile()) {
            return false;
        }
        for (Section section : sections) {
            // If the section's filter doesn't accept the file, return false.
            if (!section.getFilter().accept(file)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return The parsed sections list.
     */
    public LinkedList<Section> getSections() {
        return sections;
    }

    /**
     * @return The warnings list.
     */
    public LinkedList<String> getWarnings() {
        return warnings;
    }

    /*
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
     * The method expects that when called currLine is the first line in the command file.
     * @return The Section list.
     * @throws MissingSubsectionException
     * @throws InvalidCommandFileException
     */
    public LinkedList<Section> parseCommandFile() throws MissingSubsectionException,
            InvalidCommandFileException {
        LinkedList<Section> sections = new LinkedList<>();
        while (currLine != null) {
            if (!currLine.equals(FILTER_SUBSECTION_TITLE)) {
                throw new MissingSubsectionException(FILTER_SUBSECTION_MISSING_MSG);
            }
            advanceLine();
            Filter currFilter = parseFilterLine();
            advanceLine();
            if (!currLine.equals(ORDER_SUBSECTION_TITLE)) {
                throw new MissingSubsectionException(ORDER_SUBSECTION_MISSING_MSG);
            }
            advanceLine();
            Order currOrder = parseOrderLine();
            Section currSection = new Section(currFilter, currOrder);
            sections.add(currSection);
            advanceLine();
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
            warnings.add("Warning in line " + Integer.toString(lineCounter));
            return new AllFilter();
        }
    }

    /*
     * Parse the current command line into an Order object.
     * @return A corresponding order object.
     */
    private Order parseOrderLine() {
        try {
            return OrderFactory.generateOrder(currLine);
        }
        catch (Type1ErrorException e) {
            warnings.add("Warning in line " + Integer.toString(lineCounter));
            return new AbsOrder();
        }
    }

    /* Thrown when an ORDER or FILTER line does not appear where it should. */
    private class MissingSubsectionException extends Type2ErrorException {
        public MissingSubsectionException(String message) { super(message); }
    }

    /* Thrown when a line in the command file cannot be read by the FileReader. */
    private class InvalidCommandFileException extends Type2ErrorException {
        public InvalidCommandFileException(String message) {
            super(message);
        }
    }

    /* Thrown when the command file is not found. */
    private class CommandFileNotFoundException extends Type2ErrorException {
        public CommandFileNotFoundException(String message) { super(message); }
    }

}