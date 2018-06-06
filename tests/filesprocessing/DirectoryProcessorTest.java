package filesprocessing;

import java.io.IOException;

import static org.junit.Assert.*;

public class DirectoryProcessorTest {

    @org.junit.Test
    public void test02_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test02_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test02/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test00_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test00/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test00_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test00/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test01_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test01/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test01_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test01/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test03_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test03/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test03_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test03/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test04_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test04/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test04_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test04/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test05_simple() {
        String sourcedir = "./tester_files/files_to_filter/simple/";
        String commandFile = "./tester_files/tests/test05_greater_than/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }

    @org.junit.Test
    public void test05_complex() {
        String sourcedir = "./tester_files/files_to_filter/complex/";
        String commandFile = "./tester_files/tests/test05_greater_than/commands.txt";
        String[] args = {sourcedir, commandFile};
        DirectoryProcessor.main(args);
    }
}