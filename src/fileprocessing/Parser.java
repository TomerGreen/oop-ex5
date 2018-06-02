package fileprocessing;

import filters.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class that parses a command file into an array of section objects.
 */
public class Parser {

    private int lineCounter;
    private BufferedReader lineReader;
    private String currLine;

    public Parser(String filepath) throws FileNotFoundException, InvalidCommandFileException {
        lineCounter = 0;
        lineReader = new BufferedReader(new FileReader(filepath));
        this.advanceLine();
    }

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

    /** A list of Type I warnings thrown when parsing the command file. */
    LinkedList<String> warnings;

    public LinkedList<Section> parseCommandFile() throws MissingSubsectionException,
            InvalidCommandFileException {
        LinkedList<Section> sections = new LinkedList<>();
        while (currLine != null) {
            if (currLine != "FILTER") {
                throw new MissingSubsectionException("ERROR: FILTER sub-section missing.");
            }
            advanceLine();
            Filter currFilter = parseFilterLine(currLine, lineCounter);
            advanceLine();
            if (currLine != "ORDER") {
                throw new MissingSubsectionException("ERROR: ORDER sub-section missing.");
            }
            // CREATE ORDER OBJECT HERE.
            Section currSection = new Section(currFilter);
            sections.add(currSection);
            advanceLine();
        }
        return sections;
    }

    private Filter parseFilterLine(String line, int lineNum) {
        try {
            return FilterFactory.generateFilter(line);
        }
        catch (BooleanFilter.InvalidBooleanFilterValueException
                | FilterFactory.InvalidFilterNameException
                | SizeFilter.InvalidSizeLimitException e) {
            warnings.add("Warning in line " + Integer.toString(lineNum));
            return new AllFilter();
        }
    }

    private class MissingSubsectionException extends Exception {
        public MissingSubsectionException(String message) {
            super(message);
        }
    }

    private class InvalidCommandFileException extends Exception {
        public InvalidCommandFileException(String message) {
            super(message);
        }
    }

}