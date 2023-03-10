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
    void after_reset_filter_initMovieList_should_be_currentList(){
        HomeController homeController = new HomeController();
        List<Movie> currentList = new ArrayList<>();
        currentList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        currentList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        currentList.add(new Movie(null,"doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        currentList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));

        List<Movie> initList = new ArrayList<>();
        initList.add(new Movie("B","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        initList.add(new Movie("D","doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        initList.add(new Movie("C","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));
        initList.add(new Movie("A","doesnt matter", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.ADVENTURE, Movie.Genre.FANTASY)));

        //action
        homeController.resetMovieFilter(currentList,initList);
        //assert
        assertEquals(currentList,initList);
    }


    @Test
    public void tests_if_title_and_description_is_searchable() {
        //setup
        List<Movie> genreSearchList = new ArrayList<>();
        Movie movie1 = new Movie("A","text1", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION));
        Movie movie2 = new Movie("B","text1", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.ADVENTURE));
        Movie movie3 = new Movie("C","text2", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.FANTASY));
        genreSearchList.add(movie1);
        genreSearchList.add(movie2);
        genreSearchList.add(movie3);

        //action
        HomeController homeController = new HomeController();

        //assert
        List<Movie> searchResults =  homeController.searchText(genreSearchList,"A");
        assertEquals(1, searchResults.size());

        searchResults = homeController.searchText(genreSearchList, "text1");
        assertEquals(2, searchResults.size());

    }

    @Test
    public void tests_if_textfield_is_null_or_invalid_text() {
        //setup
        List<Movie> genreSearchList = new ArrayList<>();
        Movie movie1 = new Movie("A","text1", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION));
        Movie movie2 = new Movie("B","text1", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.ADVENTURE));
        Movie movie3 = new Movie("C","text2", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.FANTASY));
        genreSearchList.add(movie1);
        genreSearchList.add(movie2);
        genreSearchList.add(movie3);

        //action
        HomeController homeController = new HomeController();


        //assert
        List<Movie> searchResults =  homeController.searchText(genreSearchList,"");
        assertEquals(3, searchResults.size());

        searchResults =  homeController.searchText(genreSearchList," ");
        assertEquals(0, searchResults.size());

        searchResults = homeController.searchText(genreSearchList, "253_:_:_.jsj");
        assertEquals(0, searchResults.size());

    }


    @Test
    public void test_if_the_right_movie_has_been_searched_up() {
        //setup
        List<Movie> genreSearchList = new ArrayList<>();
        Movie movie1 = new Movie("A","text1", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION));
        Movie movie2 = new Movie("B","text1", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.ADVENTURE));
        Movie movie3 = new Movie("C","text2", Arrays.asList(Movie.Genre.ACTION,Movie.Genre.FANTASY));
        genreSearchList.add(movie1);
        genreSearchList.add(movie2);
        genreSearchList.add(movie3);

        //action
        HomeController homeController = new HomeController();


        //assert
        List<Movie> searchResults =  homeController.searchText(genreSearchList,"A");
        assertEquals(movie1.getTitle(), searchResults.get(0).getTitle());

        searchResults =  homeController.searchText(genreSearchList,"text2");
        assertEquals(movie3.getDescription(), searchResults.get(0).getDescription());

    }


    @Test
    void tests_movie_filtering_by_genre(){
        //setup
        HomeController homeController = new HomeController();
        homeController.allMovies.clear();
        homeController.allMovies.add(new Movie("Action Movie", "doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.ACTION, Movie.Genre.THRILLER)));
        homeController.allMovies.add(new Movie("Boring Movie", "doesnt matter", Arrays.asList(Movie.Genre.DRAMA, Movie.Genre.THRILLER)));


        //action
        homeController.searchGenre("ACTION");
        //assert
        String testList="";
        for (Movie movie : homeController.genreResults){
            testList+=movie.getTitle().toString();
        }
        assertTrue(testList.contains("Action Movie"));
        assertFalse(testList.contains("Boring Movie"));
    }
}