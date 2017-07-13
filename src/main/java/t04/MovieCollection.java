package t04;

import java.io.*;
import java.util.*;

/**
 * Created by Aleksandr Shevkunenko on 13.07.2017.
 */
public class MovieCollection {
    static final String filename = "src\\main\\java\\resources\\movies.dat";

    static final Scanner reader = new Scanner(System.in);
    Set<Movie> movies = new TreeSet<>(Comparator.comparing(Movie::getTitle));

    boolean save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(movies);
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println("The destination file is absent or inaccessible.");
            return false;
        } catch (IOException e) {
            System.out.println("Movie collection saving failed.");
            return false;
        }
        System.out.println("Movie collection was successfully saved.");
        return true;
    }

    boolean open(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            movies = (Set<Movie>) ois.readObject();
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println("The destination file is absent or inaccessible.");
            return false;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Movie collection opening failed.");
            return false;
        }
        System.out.println("Movie collection was successfully loaded.");
        return true;
    }

    boolean removeMovie() {
        for (;;) {
            System.out.println("Please, enter the title (official name) of the movie you'd like to remove from the collection, or press Q for main menu:");
            String input = reader.nextLine();
            if (input.equalsIgnoreCase("q")) return false;
            for (Iterator<Movie> iter = movies.iterator(); iter.hasNext(); ) {
                if (input.equalsIgnoreCase(iter.next().getTitle())) {
                    iter.remove();
                    return true;
                }
            }
            System.out.println("There is no movie with such title in the collection.");
        }
    }

    boolean addMovie() {
        System.out.println("Please, enter the title of the movie you'd like to add into the collection, or press Q for main menu:");
        String title = reader.nextLine();
        if (title.equalsIgnoreCase("q")) return false;
        int year = 0;
        for (;;) {
            System.out.println("Please, enter the production year of the movie, or press Q for main menu:");
            try {
                String input = reader.nextLine();
                if (input.equalsIgnoreCase("q")) return false;
                year = Integer.parseInt(input);
                if (1900 < year && year < 2100) break;
            } catch (NumberFormatException e) {
                System.out.println("The entered year is kinda strange for movie production industry.");
            }
        }
        List<Actor> actorList = null;
        outer: for (;;) {
            System.out.println("Please, enter the actors of the movie one by one in a format: Firstname1 Lastname1 (Nationality1), Firstname2 Lastname2 (Nationality2), ... ");
            System.out.println("They should be separated with commas. Press Enter when done or Q to cancel to main menu.");
            String input = reader.nextLine();
            if (input.equalsIgnoreCase("q")) return false;
            String[] actors = input.split("\\s*,\\s*");
            actorList = new ArrayList<>();
            for (String s : actors) {
                String[] info = s.split("\\s+");
                if (info.length != 3) {
                    System.out.println("One of the actor's information was entered incorrectly.");
                    continue outer;
                }
                actorList.add(new Actor(info[0], info[1], info[2].substring(1, info[2].length() - 1)));
            }
            break;
        }
        movies.add(new Movie(title, year, actorList));
        return true;
    }

    void print() {
        if (movies.isEmpty()) {
            System.out.println("Currently there is no movies in the collection.");
            return;
        }
        System.out.println("The movies in the collections are:");
        movies.forEach(movie -> System.out.println(movie));
    }

    void showMenu() {
        System.out.println("To choose the desired item enter the correspinding number and press Enter.");
        System.out.println("1. Print the whole collection.");
        System.out.println("2. Add a movie to the collection.");
        System.out.println("3. Remove a movie from the collection.");
        System.out.println("4. Save collection to file.");
        System.out.println("5. Reload collection from file.");
        System.out.println("6. Exit.");
    }

    public static void main(String[] args) {
        MovieCollection mc = new MovieCollection();
        mc.open(filename);
        outer: for(;;) {
            mc.showMenu();
            int input = 0;
            try {
                input = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number from 1 to 6 and press Enter.");
                continue outer;
            }
            switch (input) {
                case 1: mc.print(); break;
                case 2: mc.addMovie(); break;
                case 3: mc.removeMovie(); break;
                case 4: mc.save(filename); break;
                case 5: mc.open(filename); break;
                case 6: break outer;
                default: System.out.println("You should enter a number from 1 to 6 and press Enter.");
            }
        }
    }
}
