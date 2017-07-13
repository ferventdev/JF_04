package t04;

import lombok.Value;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr Shevkunenko on 11.07.2017.
 */
@Value
public class Movie implements Serializable, Comparable<Movie> {
    private String title;
    private int year;
    private List<Actor> actors;

    private List<String> getActorsAsStrings() {
        return actors.stream().map(actor -> actor.toString()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return new StringBuilder(title).append(" [").append(year).append("] (Cast: ")
                .append(String.join(", ", getActorsAsStrings()))
                .append(')').toString();
    }

    @Override
    public int compareTo(Movie o) {
        return title.compareTo(o.getTitle());
    }
}
