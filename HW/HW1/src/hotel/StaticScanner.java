package hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Scanner class for opening one instance of input file.
 */
abstract class StaticScanner{
    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("input2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("INPUT FILE NOT FOUND!!!");
        }
    }

    public static Scanner getScanner(){
        return scanner;
    }
}
