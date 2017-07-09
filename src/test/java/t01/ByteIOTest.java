package t01;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 */
public class ByteIOTest {
    private String input = "src\\main\\java\\t01\\ByteIO.java";
    private String output = "src\\main\\resources\\JavaKeywordsStats.dat";

    @Test
    public void byteIOTest() throws Exception {
        String[] wordsArray = new String(Files.readAllBytes(Paths.get(input))).split("[^\\w$]");
        List<String> words1 = Arrays.stream(wordsArray)
                .filter(str -> !str.isEmpty() && Character.isJavaIdentifierStart(str.charAt(0)))
                .collect(Collectors.toList());
        List<String> words2 = ByteIO.getWords(input);

        assertThat(words2, is(words1));

        ByteIO.writeStatsToFile(output, ByteIO.countKeywords(words2));
    }

}