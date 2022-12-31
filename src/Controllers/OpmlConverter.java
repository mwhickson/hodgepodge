package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpmlConverter {
    
    public static String ReadOpmlFileAsString(Path p, Charset c) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(p, c)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }

        return sb.toString();
    }

    public static boolean WriteOpmlStringToFile(String s, Path p, Charset c, boolean shouldOverwrite) {
        boolean success = false;

        try (BufferedWriter writer = Files.newBufferedWriter(p, c)) {
            writer.write(s);
            success = true;
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }

        return success;
    }

}
