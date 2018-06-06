package filesprocessing;

import filesprocessing.orders.*;
import java.io.File;
import java.util.LinkedList;

/**
 * Processes files in a source directory according to a command file.
 * The files that passed all the filters in the command file are then printed
 * according to the order that results from applying all "order" commands in order.
 */
public class DirectoryProcessor {

    /**
     * Creates a processor object.
     */
    public DirectoryProcessor() {
    }

    /**
     * Returns an ordered list of the files that passed processing.
     * @param sourceDirPath The path of the directory which contains the files.
     * @return An ordered list of files.
     */
    private static LinkedList<File> getProcessedFileList(String sourceDirPath, CommandParser commandParser) {
        File[] fileArray = new File(sourceDirPath).listFiles(commandParser);
        LinkedList<File> fileList = new LinkedList<>();
        for (File file : fileArray) {
            fileList.add(file);
        }
        for (Section section : commandParser.getSections()) {
            fileList.sort(section.getOrder());
        }
        return fileList;
    }

    /**
     * Prints all the Type I warnings encountered when parsing the command file,
     * and the names of files that passed processing, in order.
     * @param args User entered paths for the source directory and command file, respectively.
     */
    public static void main(String[] args) {
        try {
            CommandParser parser = new CommandParser(args[1]);
            LinkedList<File> processedFiles = getProcessedFileList(args[0], parser);
            for (String warning : parser.getWarnings()) {
                System.err.println(warning);
            }
            for (File file : processedFiles) {
                System.out.println(file.getName());
            }
        }
        catch (Type2ErrorException e) {
            System.err.println(e.getMessage());
        }
    }
}