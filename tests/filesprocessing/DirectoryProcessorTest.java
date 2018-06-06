package filesprocessing;

import java.io.IOException;

import static org.junit.Assert.*;

public class DirectoryProcessorTest {

    @org.junit.Test
    public void basic_test() throws IOException {
        String sourcedir = "./tests/sourcedirs/simple/";
        String commandFile = "./tests/command_files/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }
}