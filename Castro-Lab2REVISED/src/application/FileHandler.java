// FileHandler.java
// FileHandler class for reading and writing grades data from a text file on disk and performs some simple calculations on that data
package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

public class FileHandler {
    private Scanner input;
    private String inputFile;
    private String outputFile;

    // FileHandler constructor that takes 2 strings - a read from and write to filename
    public FileHandler(String inputFile, String outputFile)
    {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    // FileHandler constructor that receives no parameters
    public FileHandler()
    {
        this.inputFile = "";
        this.outputFile = "";
    }

    // method to set input file
    public void setInputFile(String inFile)
    {
        this.inputFile = inFile;
    }


    // method to open a file to read
    public Boolean isOpenToRead()
    {
        Boolean statusRead = false;
        
        Path path = Paths.get(this.inputFile);
        if (Files.exists(path))
        {
            statusRead = true;
        }
        else
        {
            statusRead = false;
        }
       
        return statusRead;
    }

    // method to open a file to write
    public Boolean isOpenToWrite()
    {
        Boolean statusWrite = false;
        
        Path path = Paths.get(this.outputFile);
        if (Files.exists(path))
        {
            statusWrite = true;
        }
        else
        {
            statusWrite = false;
        }

        return statusWrite;
    }

    // method to write grades to a file
    public Boolean writeGrades(ArrayList<String> writeGrades)
    {
        Boolean statusWriteGrades = false;

        try(Formatter output = new Formatter(this.outputFile))
        {
            for (String p : writeGrades)
            {
                output.format("%s%n", p);
            }
            statusWriteGrades = true;
        }
        catch (SecurityException | FileNotFoundException | FormatterClosedException e)
        {
            e.printStackTrace();
        }

        return statusWriteGrades;
    }

    // method to read grades from a file
    public Boolean readGrades(ArrayList<String> readGrades)
    {
        Boolean statusReadGrades = false;

        try
        {
            this.input = new Scanner(Paths.get(this.inputFile));
            while (this.input.hasNext())
            {
                String grade = this.input.nextLine();
                readGrades.add(grade);
            }
            statusReadGrades = true;
        }
        catch (IOException e)
        {
            System.err.println("Error reading file: " + e.getMessage());
            statusReadGrades = false;
        }
        return statusReadGrades;
    }

}

