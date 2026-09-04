// FileHandlerTest.java
// FileHandlerTest class whose main method is to test its functionality by writing test code to check functionality of the 
// functions added to the class
package application;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class FileHandlerTest {
    
    public static void main(String[] args)
    {
        // filenames for input and output files, adjust as needed
        // String inputFileName = "C:\\Users\\gc060\\OneDrive\\Desktop\\School\\National University\\CSC600\\JavaHowToProgram11e_EarlyObjects-master\\Labs\\Castro-Lab2\\src\\application\\input.txt";
        // String outputFileName = "C:\\Users\\gc060\\OneDrive\\Desktop\\School\\National University\\CSC600\\JavaHowToProgram11e_EarlyObjects-master\\Labs\\Castro-Lab2\\src\\application\\output.txt";

        // Prompt user to input a file name for input and output
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter an input file name (.txt extension): ");
        String inputFileName = userInput.next();
        System.out.println("Please enter an output file name (.txt extension): ");
        String outputFileName = userInput.next();

        // Use a looping (repetition) construct that allows the user to enter 
        // as much grade data as possible with a sentinel to end the loop and write the data to disk and close the file.
        System.out.println("Please enter capitalized letter grades (A - F), one per line. When finished, enter Quit: ");
        String sentinel = "Quit";
        String gradesInput = userInput.next();
        try(Formatter enterGrades =  new Formatter(inputFileName))
        {
            while(!gradesInput.equals(sentinel))
            {
                
                enterGrades.format("%s%n", gradesInput);
                gradesInput = userInput.next();
            }
        }
        catch (SecurityException | FileNotFoundException | FormatterClosedException e)
        {
            e.printStackTrace();
        }

        // create FileHandler object for testing
        FileHandler SchoolGradeSystem = new FileHandler(inputFileName, outputFileName);
        
        // test for success on open file to read files written manually on input file
        if(SchoolGradeSystem.isOpenToRead())
        {
            System.out.println("\nThe file is open to read grades");
        }
        else
        {
            System.out.println("The file is not open to read grades, check file name and try again");
        }

        // test for reading grades from the input file into a data structure
        ArrayList<String> listGrades = new ArrayList<String>();    // holds the letter grades
        ArrayList<Double> numberGrades = new ArrayList<Double>();  // holds the number grades

        if(SchoolGradeSystem.readGrades(listGrades))
        {
            System.out.println("Grades read from input file and added to data structure, results below:");

            // Matching letter grades with corresponding number grades
            for (String letterGrade : listGrades)
            {
                switch (letterGrade)
                {
                    case "A": 
                        numberGrades.add(4.0);
                        break;
                    case "A-":
                        numberGrades.add(3.7);
                        break;
                    case "B+":
                        numberGrades.add(3.3);
                        break;
                    case "B":
                        numberGrades.add(3.0);
                        break;
                    case "B-":
                        numberGrades.add(2.7);
                        break;
                    case "C+":
                        numberGrades.add(2.3);
                        break;
                    case "C":
                        numberGrades.add(2.0);
                        break;
                    case "C-":
                        numberGrades.add(1.7);
                        break;
                    case "D+":
                        numberGrades.add(1.3);
                        break;
                    case "D":
                        numberGrades.add(1.0);
                        break;
                    case "D-":
                        numberGrades.add(0.7);
                        break;
                    case "F":
                        numberGrades.add(0.0);
                        break;
                    default:
                        System.out.println("Invalid entry, please try again: " + letterGrade);
                }
            }

            // display grades nicely
            for (int i = 0; i < listGrades.size(); i++)
            {
                System.out.printf("%s = %.2f%n", listGrades.get(i), numberGrades.get(i));
            }


        }
        else
        {
            System.out.println("error - please try again for reading grades from file");
        }

        // calculate GPA and display results
        double GPA = 0.0;
        double total = 0.0;
        for (int j = 0; j < numberGrades.size(); j++)
        {
            total = total + numberGrades.get(j);
        }
        GPA = total / (double) numberGrades.size();
        System.out.printf("Average GPA: %.2f%n", GPA);
        
        // test for writing grades to an output file
       if(SchoolGradeSystem.isOpenToWrite())
        {
            System.out.println("\nThe file is open to write grades to an output file");
        }
        else
        {
            System.out.println("The file is not open to write grades to, check file name and try again");
        }

        if(SchoolGradeSystem.writeGrades(listGrades))
        {
             System.out.println("Please check output file for grades data placed");
        }
        else
        {
            System.out.println("Error, the output file did not receive the grades data");
        }
    }
}


    
