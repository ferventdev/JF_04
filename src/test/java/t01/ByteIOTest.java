package t01;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 */
public class ByteIOTest {
    private String filename = "src\\main\\java\\t01\\ByteIO.java";

    @Test
    public void getWordsTest() throws Exception {
        String[] wordsArray = new String(Files.readAllBytes(Paths.get(filename))).split("[^\\w$]");
        List<String> words1 = Arrays.stream(wordsArray)
                .filter(str -> !str.isEmpty() && Character.isJavaIdentifierStart(str.charAt(0)))
                .collect(Collectors.toList());
        List<String> words2 = ByteIO.getWords(filename);

        assertThat(words2, is(words1));
    }

}