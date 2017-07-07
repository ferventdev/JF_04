package t01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 */
public class ByteIOTest {
    private String filename = "src\\main\\java\\t01\\ByteIO.java";
    @Test
    public void getWords() throws Exception {
        for (String str : ByteIO.getWords(filename))
            System.out.print(str + " ");
    }

}