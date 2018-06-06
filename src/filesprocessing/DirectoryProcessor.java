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

    /*
     * A parser object that, once created, holds the parsing warnings and parsed sections.
     */
    private CommandParser commandParser;

    /**
     * Creates a processor object.
     * @param commandFilePath The command file path.
     * @throws Type2ErrorException If a fatal error occurs.
     */
    public DirectoryProcessor(String commandFilePath) throws Type2ErrorException {
        commandParser = new CommandParser(commandFilePath);
    }

    /**
     * Returns an ordered list of the files that passed processing.
     * @param sourceDirPath The path of the directory which contains the files.
     * @return An ordered list of files.
     */
    private LinkedList<File> getProcessedFileList(String sourceDirPath) {
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
            DirectoryProcessor processor = new DirectoryProcessor(args[1]);
            LinkedList<File> processedFiles = processor.getProcessedFileList(args[0]);
            for (String warning : processor.commandParser.getWarnings()) {
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