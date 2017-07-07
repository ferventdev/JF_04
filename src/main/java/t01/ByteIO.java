package t01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 * 777_abc - check for the wrong Java identifier
 */
public class ByteIO {

    static List<String> getWords(String filename) {
        List<String> words = new ArrayList<>();
        try (InputStream file = new BufferedInputStream(new FileInputStream(filename))) {
            StringBuilder word = new StringBuilder();
            boolean firstLetter = true;
            boolean wrongWord = false;
            int b = 0;
            while ((b = file.read()) != -1) {
                char ch = (char) b;
                if (firstLetter && Character.isJavaIdentifierStart(ch)) {
                    word.append(ch);
                    firstLetter = false;
                    wrongWord = false;
                } else if (!wrongWord && firstLetter && Character.isJavaIdentifierPart(ch) && !Character.isJavaIdentifierStart(ch)) {
                    firstLetter = false;
                    wrongWord = true;
                } else if (!wrongWord && !firstLetter && Character.isJavaIdentifierPart(ch)) word.append(ch);
                else if (!Character.isJavaIdentifierPart(ch)) {
                    if (word.length() != 0) {
                        words.add(word.toString());
                        word = new StringBuilder();
                    }
                    firstLetter = true;
                    wrongWord = false;
                }
            }
        } catch (FileNotFoundException | SecurityException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } catch (IOException e) {
            e.printStackTrace();
            return words;
        }
        return words;
    }
}