package delivery.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractFile {

    protected String fileName;
    protected Scanner fileScanner;
    protected File file;
    protected FileWriter fileWriter;
    protected PrintWriter printWriter;

    public AbstractFile() {
    }

    public static String getDELIMITER() {
        return ";";
    }

    public abstract void readFromFile(String fileName);

    public abstract void writeToFile(String fileName);


}
