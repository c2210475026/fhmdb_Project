package at.ac.fhcampuswien.fhmdb.models;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

    private final String title;
    private final String description;
    // TODO add more properties here

    private final List<Genre> genres;


    public enum Genre{
        ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
        CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR,
        MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR,
        WESTERN
    }

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Movies werden erstellt, Array wird zu Liste umgewandelt, da genresList final ist, ist der asList parse fine.
     * @return vorhandenen Filme
     */
    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // add genres to the List
        movies.add(new Movie("The Godfather", "A classic crime drama about the Corleone family.", Arrays.asList(Genre.DRAMA,Genre.ACTION,Genre.THRILLER)));
        movies.add(new Movie("Star Wars: A New Hope", "A space opera about a group of rebels fighting against the evil Empire.", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("Forrest Gump", "A comedy-drama about a man with a low IQ who witnesses and unwittingly influences several defining historical events in the 20th century USA.", Arrays.asList(Genre.COMEDY, Genre.DRAMA)));
        // return the list of movies
        return movies;
    }


}
