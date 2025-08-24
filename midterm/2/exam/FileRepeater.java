package exam;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileRepeater {
    public static void main(String[] args) {
        repeatFile();
    }

    private static void repeatFile() {
        Scanner in = new Scanner(System.in);
        boolean fileFound = false;

        while (!fileFound) {
            System.out.println("Enter a file name: ");
            String fname = in.nextLine();
            
            try {
                Scanner freader = new Scanner(new File(fname));
                while (freader.hasNextLine()) {
                    System.out.println(freader.nextLine());
                }
                fileFound = true;
                freader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error -- File fName not found.");
            }
        }
        in.close();
    }
}