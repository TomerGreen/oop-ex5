package filesprocessing;

import filesprocessing.orders.*;
import java.io.File;
import java.util.LinkedList;

public class DirectoryProcessor {

    private CommandParser commandParser;

    public DirectoryProcessor(String commandFilePath) throws Type2ErrorException {
        commandParser = new CommandParser(commandFilePath);
    }

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
