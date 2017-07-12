package t04;

import lombok.Value;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr Shevkunenko on 11.07.2017.
 */
@Value
public class Movie implements Serializable {
    private String title;
    private int year;
    private Set<Actor> actors = new HashSet<>();

    private Set<String> getActorsAsStrings() {
        return actors.stream().map(actor -> actor.toString()).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return new StringBuilder(title).append(" [").append(year).append("] (Cast: ")
                .append(String.join(", ", getActorsAsStrings()))
                .append(')').toString();
    }
}
