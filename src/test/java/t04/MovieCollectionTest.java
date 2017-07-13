package t04;

import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 13.07.2017.
 */
public class MovieCollectionTest {

    String filename = "src\\main\\resources\\movies.dat";

    @Test
    public void saveTest() throws Exception {
        MovieCollection mc = new MovieCollection();
        mc.save(filename);
    }
}