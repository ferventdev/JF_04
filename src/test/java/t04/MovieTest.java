package t04;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 12.07.2017.
 */
public class MovieTest {
    @Test
    public void movieTest() throws Exception {
        Actor mRodriguex = new Actor("Michelle", "Rodriguez", "USA");
        List<Actor> actorsA = Arrays.asList(
            new Actor("Sam", "Worthington", "UK"),
            new Actor("Sigourney", "Weaver", "USA"),
            mRodriguex);
        List<Actor> actorsF = Arrays.asList(
                new Actor("Vin", "Diesel", "USA"),
                new Actor("Paul", "Walker", "USA"),
                mRodriguex);

        Movie avatar = new Movie("Avatar", 2009, actorsA);
        Movie theFastAndTheFurious = new Movie("The Fast and the Furious", 2001, actorsF);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(avatar);
        oos.writeObject(theFastAndTheFurious);
//        oos.reset();
        oos.writeObject(avatar);

        oos.close();
        out.close();

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(in);

        Movie _avatar1 = (Movie) ois.readObject();
        Movie _theFastAndTheFurious = (Movie) ois.readObject();
        Movie _avatar2 = (Movie) ois.readObject();
//        System.out.println(_avatar1);
//        System.out.println(_theFastAndTheFurious);
//        System.out.println(_avatar2);

        ois.close();
        in.close();

        assertEquals(avatar, _avatar1);
        assertEquals(avatar, _avatar2);
        assertEquals(_avatar1, _avatar2);
        assertEquals(theFastAndTheFurious, _theFastAndTheFurious);
        assertFalse(theFastAndTheFurious == _theFastAndTheFurious);
        assertFalse(avatar == _avatar1);
        assertFalse(avatar == _avatar2);
        assertTrue(_avatar1 == _avatar2);
    }
}