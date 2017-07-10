package t02;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Aleksandr Shevkunenko on 10.07.2017.
 */
public class SymbolicIOTest {
    private String input = "src\\main\\java\\t02\\SymbolicIO.java";
    private String output = "src\\main\\resources\\JavaKeywordsStats2.dat";

    @Test
    public void symbolicIOTest() throws Exception {
        String[] wordsArray = new String(Files.readAllBytes(Paths.get(input))).split("[^\\w$]");
        List<String> words1 = Arrays.stream(wordsArray)
                .filter(str -> !str.isEmpty() && Character.isJavaIdentifierStart(str.charAt(0)))
                .collect(Collectors.toList());
        List<String> words2 = SymbolicIO.getWords(input);

        assertThat(words2, is(words1));

        SymbolicIO.writeStatsToFile(output, SymbolicIO.countKeywords(words2));
    }

}