package fileprocessing;


import fileprocessing.exceptions.Type2ErrorException;

import java.util.LinkedList;

public class DirectoryProcessor {

    public void getProcessedFileArray(String dirpath, String commandFilePath) {
        try {
            CommandParser commandParser = new CommandParser(commandFilePath);
            LinkedList<Section> sectionList = CommandParser
        }
        catch (Type2ErrorException e){

        }

    }

    public static void main(String[] args) {

    }
}
