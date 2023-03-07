package at.ac.fhcampuswien.fhmdb.models;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

    private final String title;
    private final String description;
    // TODO add more properties here

    private static List<Genre> genres = new ArrayList<>(Arrays.asList(
            Genre.ACTION, Genre.ADVENTURE, Genre.ANIMATION, Genre.BIOGRAPHY, Genre.COMEDY,
            Genre.CRIME, Genre.DRAMA, Genre.DOCUMENTARY, Genre.FAMILY, Genre.FANTASY,
            Genre.HISTORY, Genre.HORROR, Genre.MUSICAL, Genre.MYSTERY, Genre.ROMANCE,
            Genre.SCIENCE_FICTION, Genre.SPORT, Genre.THRILLER, Genre.WAR, Genre.WESTERN
    ));


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

    public static List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // add genres to the List
        movies.add(new Movie("The Godfather", "A classic crime drama about the Corleone family.", Arrays.asList(genres.get(1))));
        movies.add(new Movie("Star Wars: A New Hope", "A space opera about a group of rebels fighting against the evil Empire.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movies.add(new Movie("The Shawshank Redemption", "A drama about a man's journey in prison and his friendship with a fellow inmate.", Arrays.asList(Movie.Genre.DRAMA)));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "An epic fantasy adventure about a hobbit and his companions on a quest to destroy a powerful ring.", Arrays.asList(Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movies.add(new Movie("Forrest Gump", "A comedy-drama about a man with a low IQ who witnesses and unwittingly influences several defining historical events in the 20th century USA.", Arrays.asList(Movie.Genre.COMEDY, Movie.Genre.DRAMA)));
        // return the list of movies
        return movies;
    }


}
