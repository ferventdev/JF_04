package t01;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 * 777_abc - check for the wrong Java identifier
 */
public class ByteIO {

    static final String[] javaKeywords = {
            "abstract", "continue", "for", "new", "switch",
            "assert", "default", "goto", "package", "synchronized",
            "boolean", "do", "if", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws",
            "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile",
            "const", "float", "native", "super", "while"
    };

    static List<String> getWords(String filename) {
        List<String> words = new ArrayList<>();
        try (InputStream f = new BufferedInputStream(new FileInputStream(filename))) {
            StringBuilder word = new StringBuilder();
            boolean firstLetter = true;
            boolean wrongWord = false;
            int b = 0;
            while ((b = f.read()) != -1) {
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

    static Map<String, Integer> countKeywords(List<String> allWords) {
        Set<String> keywords = Stream.of(javaKeywords).collect(Collectors.toSet());
        Map<String, Integer> stats = new HashMap<>();
        for (String word : allWords) {
            if (keywords.contains(word)) {
                Integer newValue = stats.getOrDefault(word, 0) + 1;
                stats.put(word, newValue);
            }
        }
        return stats;
    }

    static void writeStatsToFile(String filename, Map<String, Integer> stats) {
        try (OutputStream f = new BufferedOutputStream(new FileOutputStream(filename, false))) {
            Charset charset = Charset.forName("UTF-16");
            String sep = System.getProperty("line.separator");
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                String str = entry.getKey() + " : " + entry.getValue() + sep;
                f.write(str.getBytes(charset));
            }
            f.flush();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}