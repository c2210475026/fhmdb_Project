package at.ac.fhcampuswien.fhmdb.models;



import javafx.scene.shape.MoveTo;

import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie>{

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
        try{
            return title;
        }catch (NullPointerException e){
            return null;
        }
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
        movies.add(new Movie("The Godfather", "A classic crime drama about the Corleone family.", Arrays.asList(Movie.Genre.CRIME, Movie.Genre.DRAMA)));
        movies.add(new Movie("Star Wars: A New Hope", "A space opera about a group of rebels fighting against the evil Empire.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movies.add(new Movie("The Shawshank Redemption", "A drama about a man's journey in prison and his friendship with a fellow inmate.", Arrays.asList(Movie.Genre.DRAMA)));
        movies.add(null);
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "An epic fantasy adventure about a hobbit and his companions on a quest to destroy a powerful ring.", Arrays.asList(Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movies.add(new Movie("Forrest Gump", "A comedy-drama about a man with a low IQ who witnesses and unwittingly influences several defining historical events in the 20th century USA.", Arrays.asList(Movie.Genre.COMEDY, Movie.Genre.DRAMA)));
        movies.add(new Movie("The Godfather: Part II", "A sequel to The Godfather, continuing the story of the Corleone family.", Arrays.asList(Movie.Genre.CRIME, Movie.Genre.DRAMA)));
        movies.add(new Movie("The Dark Knight", "A superhero film featuring Batman as he battles the Joker and other villains in Gotham City.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.CRIME, Movie.Genre.DRAMA)));
        movies.add(new Movie("The Matrix", "A science fiction action film about a computer hacker who discovers the truth about reality.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.SCIENCE_FICTION)));
        movies.add(new Movie("Pulp Fiction", "A crime film featuring multiple storylines and characters in Los Angeles.", Arrays.asList(Movie.Genre.CRIME, Movie.Genre.DRAMA)));
        movies.add(new Movie("Jurassic Park", "A science fiction adventure film about a theme park with genetically cloned dinosaurs.", Arrays.asList(Movie.Genre.ADVENTURE, Movie.Genre.SCIENCE_FICTION)));
        // return the list of movies
        return movies;
    }
    @Override
    public int compareTo(Movie o) {
        if (this.getTitle() == null || o.getTitle() == null) {
            if (this.getTitle() == null) {
                if (o.getTitle() == null) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }else{
            return this.getTitle().compareTo(o.getTitle());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie){
            Movie other = (Movie) obj;
            return this.getTitle() == other.getTitle() && this.getDescription() == other.getDescription() && this.getGenres() == other.getGenres();
        }else {
            return false;
        }
    }
}
