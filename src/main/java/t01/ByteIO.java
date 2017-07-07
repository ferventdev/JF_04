package t01;

import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 */
public class ByteIO {

    List<String> getWords(String filename) {
        List<String> words = new ArrayList<>();
        try (InputStream file = new BufferedInputStream(new FileInputStream(filename))) {
            StringBuilder word = new StringBuilder();
            boolean firstLetter = true;
            int b = 0;
            while ((b = file.read()) != -1) {
                char ch = (char) b;
                if (firstLetter && Character.isJavaIdentifierStart(ch)) {
                    word.append(ch);
                    firstLetter = false;
                } else if (Character.isJavaIdentifierPart(ch)) word.append(ch);
                else if (word.length() == 0) continue;
                else {
                    words.add(word.toString());
//                    System.out.println(word);
                    word = new StringBuilder();
                    firstLetter = true;
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
