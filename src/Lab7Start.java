import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * Lab 7 CSIT 150 spring 2017
 * Created by harmssk on 3/2/2017.
 *
 * A binary file named doubles.dat is provided with this lab.
 * This file has a list of numbers (doubles) such as 10.2, 20, 5.1, 8, 9.9.
 * This is a binary file, so it is not readable.
 * It does not contain the commas between the numbers.
 * We do not know how many numbers are in the file.

 Read the numbers stored in the binary file into an array or ArrayList.
 Output the values and their average.

 Use Exceptions to verify the file is found.
 use exceptions to keep reading from the file until the end of file is found.
 *
 *
 */
public class Lab7Start {

    /**
     * Prompts the user to enter the name of the file they wish to analyze
     *
     * @return file name to analyze
     */
    public static String getFileName() {
        boolean typeFlag = false;
        String fileName = "";
        while (!typeFlag) {
            String prompt = "Please input the name of the file you wish\n"
                    + "to analyse. You will be asked to enter a\n"
                    + "file name until a valid file name is provided.\n"
                    + "The file name must have a '.dat' or '.csv' extension.\n";
            fileName = JOptionPane.showInputDialog(null, prompt,
                    "Enter File Name", 1);
            if (fileName.endsWith("dat") || fileName.endsWith("csv")) {
                typeFlag = true;
            }
        }
        return fileName;
    }

    /**
     * This method accepts .dat file type and attempts to establish a connection
     * with the file and will prompt the user to enter another file name if the file is not found.
     * Uses a try/catch block to catch "FileNotFoundException".
     * @return the data input stream to read the contents of the binary file
     */
    public static DataInputStream openBinaryFile(String fileName) {
        boolean fileFound = false;
        DataInputStream fileDIS = null;
            /*
            Your code goes here to
            within a try/catch block,
            Ask user for an input file (call the getFileName method)
            if the input file name it is a valid file,
             open the input file as a DataInputStream
            If it is not a valid file, keep asking the user for a valid file.
             */
            while (!fileFound) {
                try {
                    fileDIS = new DataInputStream(new FileInputStream(fileName));
                    fileFound = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    fileFound = false;
                }
            }
        return fileDIS;
    }

    /**
     * This method reads a .dat file and assigns each double to an array or array list - your choice
     * use a try/catch block and catch the "EOFException" to stop reading the numbers from the file.
     * you will need to catch other errors as well.
     * If you use an array, you will earn bonus points.
     * @param fileName name of file to make an inputStream of
     * @return numbers array list or array containing the doubles in the .dat file
     */
    public static double[] getNumbers(String fileName) {
        boolean endOfFile = false;
        double[] numbers = null;
        int count = 0;
        DataInputStream inputFile = openBinaryFile(fileName);
        try{
            while(!endOfFile){
                try {
                    System.out.println(inputFile.readDouble());
                    count++;
                } catch (EOFException e){
                    endOfFile = true;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            inputFile.close();
            numbers = new double[count];
            inputFile = openBinaryFile(fileName);
            for (int i = 0; i < numbers.length; i++){
                numbers[i] = inputFile.readDouble();
            }
            inputFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return numbers;
    }

    /**
     * Outputs the numbers in the array
     * @param numbers
     */
    public static void outputNumbers (double[] numbers){
        for (int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
    }

    /**
     * Outputs the average of the numbers in the array
     * @param numbers
     */
    public static void outputAverage (double[] numbers){
        double average = 0, sum = 0;
        for (int i = 0; i < numbers.length; i++){
            sum += numbers[i];
        }
        average = sum / (numbers.length);
        System.out.print(String.format("%.2f", average));
    }

    /**

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //determines which methods to run based on file type
        String fileName = getFileName();
        double[] numbers = getNumbers(fileName);
        System.out.println("The numbers in the file are:");
        outputNumbers(numbers);
        System.out.print("The average of these numbers is: ");
        outputAverage(numbers);
    }

}

