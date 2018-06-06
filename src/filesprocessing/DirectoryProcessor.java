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

    private static final String DIR_NOT_FOUND_MSG = "Could not find source directory.";

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
    private static LinkedList<File> getProcessedFileList(String sourceDirPath, Section section)
            throws SourceDirectoryNotFoundException {
        File dir = new File(sourceDirPath);
        if (!dir.isDirectory()) {
            throw new SourceDirectoryNotFoundException();
        }
        File[] fileArray = new File(sourceDirPath).listFiles(section.getFilter());
        LinkedList<File> fileList = new LinkedList<>();
        for (File file : fileArray) {
            fileList.add(file);
        }
        fileList.sort(section.getOrder());
        return fileList;
    }

    /* Thrown when the command file is not found. */
    private static class SourceDirectoryNotFoundException extends Type2ErrorException {
        public SourceDirectoryNotFoundException() { super(DIR_NOT_FOUND_MSG); }
    }

    /**
     * Prints all the Type I warnings encountered when parsing the command file,
     * and the names of files that passed processing, in order.
     * @param args User entered paths for the source directory and command file, respectively.
     */
    public static void main(String[] args) {
        try {
            CommandParser parser = new CommandParser(args[1]);
            for (String warning : parser.getWarnings()) {
                System.err.println(warning);
            }
            for (Section section : parser.getSections()) {
                LinkedList<File> processedFiles = getProcessedFileList(args[0], section);
                for (File file : processedFiles) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            }
        }
        catch (Type2ErrorException e) {
            System.err.println(e.getMessage());
        }
    }
}