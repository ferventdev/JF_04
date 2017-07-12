package t04;

import lombok.Value;

import java.io.Serializable;

/**
 * Created by Aleksandr Shevkunenko on 11.07.2017.
 */
@Value
public class Actor implements Serializable {
    private String firstName;
    private String lastName;
    private String nationality;

    @Override
    public String toString() {
        return String.format("%s %s (%s)", firstName, lastName, nationality);
    }
}