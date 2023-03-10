package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void movies_sorted_by_titles_ascended(){
        //setup
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("B","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("C","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.sortList(movieList,true);
        //assert
        assertEquals("A",movieList.get(0).getTitle());
        assertEquals("B",movieList.get(1).getTitle());
        assertEquals("C",movieList.get(2).getTitle());
        assertEquals("D",movieList.get(3).getTitle());
    }

    @Test
    void movies_sorted_by_title_descended(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("B","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("C","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.sortList(movieList,false);
        //assert
        assertEquals("D",movieList.get(0).getTitle());
        assertEquals("C",movieList.get(1).getTitle());
        assertEquals("B",movieList.get(2).getTitle());
        assertEquals("A",movieList.get(3).getTitle());
    }

    @Test
    void movies_sorted_by_title_ascended_when_title_is_null(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("B","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("C","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.sortList(movieList,true);
        //assert
        assertEquals("A",movieList.get(0).getTitle());
        assertEquals("B",movieList.get(1).getTitle());
        assertEquals("C",movieList.get(2).getTitle());
        assertNull(movieList.get(3).getTitle());
    }

    @Test
    void movies_sorted_by_title_descended_when_title_is_null(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("C","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.sortList(movieList,false);
        //assert
        assertEquals("D",movieList.get(0).getTitle());
        assertEquals("C",movieList.get(1).getTitle());
        assertEquals("A",movieList.get(2).getTitle());
        assertNull(movieList.get(3).getTitle());
    }

    @Test
    void movies_with_nullTitle_are_at_the_end_of_list_normalTitles_no_change1(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.nullTitlesToEndOfList(movieList);
        //assert
        assertEquals("A",movieList.get(0).getTitle());
        assertEquals("D",movieList.get(1).getTitle());
        assertNull(movieList.get(2).getTitle());
        assertNull(movieList.get(3).getTitle());
    }

    @Test
    void movies_with_nullTitle_are_at_the_end_of_list_normalTitles_no_change2(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        movieList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        movieList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        //action
        homeController.nullTitlesToEndOfList(movieList);
        //assert
        assertEquals("D",movieList.get(0).getTitle());
        assertEquals("A",movieList.get(1).getTitle());
        assertNull(movieList.get(2).getTitle());
        assertNull(movieList.get(3).getTitle());
    }

    @Test
    void nullObjects_in_list_should_be_deleted(){
        HomeController homeController = new HomeController();
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER));
        Movie movie2 = new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY));
        Movie movie3 = new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY));
        movieList.add(null);
        movieList.add(movie1);
        movieList.add(null);
        movieList.add(movie2);
        movieList.add(movie3);
        //action
        homeController.setupListWithOutNullObjects(movieList);
        //assert
        assertEquals(movie1,movieList.get(0));
        assertEquals(movie2,movieList.get(1));
        assertEquals(movie3,movieList.get(2));
    }

    @Test
    void test_movies_filter_by_genre(){
        //setup
        HomeController homeController = new HomeController();
        homeController.allMovies.clear();
        homeController.allMovies.add(new Movie("Abction Movie", "doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        //action
        //homeController.sortList(HomeController.movieList, false);
        //assert
        //assertEquals("D", movieList.get(0).getTitle());
    }
}