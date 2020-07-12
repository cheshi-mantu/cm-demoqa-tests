package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadHelper {
    public static String getStringFromFile(String fullFileName) {
        String txtData = "";
        try {
            File myFileObj = new File(fullFileName);
            Scanner fileReader = new Scanner(myFileObj);
                txtData = fileReader.nextLine();
                fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: ");
            e.printStackTrace();
        }
        return txtData;
    }
}
