package filesprocessing;

import java.io.IOException;

import static org.junit.Assert.*;

public class DirectoryProcessorTest {

    @org.junit.Test
    public void basic_test() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }
}