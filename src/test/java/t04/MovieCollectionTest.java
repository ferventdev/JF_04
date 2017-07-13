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

    @Test
    public void movieCollectionTest() throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Set<Movie> movies = new HashSet<>();
        oos.writeObject(movies);

        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        System.out.println(ois.readObject());

        ois.close();
        fis.close();
    }
}