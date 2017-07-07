package t01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 07.07.2017.
 */
public class ByteIOTest {
    @Test
    public void getWords() throws Exception {
        for (String str : getWords("src\\main\\java\\t01\\ByteIO.java"))
            System.out.println(str);
    }

}