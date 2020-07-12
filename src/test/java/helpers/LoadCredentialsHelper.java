package helpers;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LoadCredentialsHelper {

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static String readStringFromFile(String filePath) {
        return new String(readBytesFromFile(filePath));
    }

    public static ArrayList<String> getCredentialsFromJson(String filePath){
        JSONObject jsonCredentials = new JSONObject(readStringFromFile(filePath));
        ArrayList<String> readCredentials = new ArrayList<String>();

        readCredentials.add(jsonCredentials.getString("name"));
        readCredentials.add(jsonCredentials.getString("secret"));

        return readCredentials;
    }

}
