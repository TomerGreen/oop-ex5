package filesprocessing;

import java.io.IOException;

import static org.junit.Assert.*;

public class DirectoryProcessorTest {

    @org.junit.Test
    public void test02_simple() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test02_complex() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test00_simple() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test00/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test00_complex() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test00/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test01_simple() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test01/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test01_complex() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test01/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test03_simple() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test03/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test03_complex() throws IOException {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test03/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }
}