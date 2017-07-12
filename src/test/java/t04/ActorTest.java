package t04;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 12.07.2017.
 */
public class ActorTest {
    @Test
    public void actorTest() throws Exception {
        Actor[] actor = {   new Actor("Christian", "Bale", "USA"),
                            new Actor("Oleg", "Yankovskiy", "USSR"),
                            new Actor("Aura", "Garrido", "Spain")       };

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(actor[0]);
        oos.writeObject(actor[1]);
        oos.writeObject(actor[2]);
//        oos.reset();
        oos.writeObject(actor[0]);

        oos.close();
        out.close();

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(in);

        Actor[] actorX = new Actor[4];
        for (int i = 0; in.available() > 0; i++) {
            actorX[i] = (Actor) ois.readObject();
//            System.out.println(actorX[i]);
        }

        ois.close();
        in.close();

        for (int i = 0; i < actor.length; i++) assertTrue(actor[i].equals(actorX[i]));
        for (int i = 0; i < actor.length; i++) assertFalse(actor[i] == actorX[i]);

        assertFalse(actorX[0].equals(actorX[1]));
        assertFalse(actorX[1].equals(actorX[2]));
        assertFalse(actorX[2].equals(actorX[3]));

        assertTrue(actorX[0].equals(actorX[3]));        // equal object
        assertTrue(actorX[0] == actorX[3]);   // even same object, if there was no reset
    }
}