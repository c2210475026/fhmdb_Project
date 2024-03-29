package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();
    @FXML
    public JFXButton resetBtn;

    private  ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    List<Movie> genreResults = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupListWithOutNullObjects(allMovies);
        observableMovies.addAll(allMovies);         // add dummy data to observable list


        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data



        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY",
                "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR",
                "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE_FICTION", "SPORT", "THRILLER", "WAR",
                "WESTERN");
        //genreComboBox.setValue("Filter by Genre");


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(actionEvent -> {
            String selectedGenre;
            try {
                selectedGenre = genreComboBox.getValue().toString();
            }
            catch (Exception e){
                selectedGenre="Filter by Genre";
            }

            searchGenre(selectedGenre);
            searchText(genreResults,searchField.getText());});

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortList(observableMovies,true);
                sortBtn.setText("Sort (desc)");
            } else {
                sortList(observableMovies,false);
                sortBtn.setText("Sort (asc)");
            }
        });

        resetBtn.setOnAction(actionEvent -> {
            resetMovieFilter(observableMovies,allMovies);
            genreComboBox.setValue("Filter by Genre");
            searchField.clear();
        });


    }

    /**
     * currentMovieList will be replaced by initMovieList
     * @param currentMovieList MovieList that is currently displayed
     * @param initMovieList List that should be displayed after reseting Filter
     */
    public void resetMovieFilter(List<Movie> currentMovieList,List<Movie> initMovieList){
        currentMovieList.clear();
        currentMovieList.addAll(initMovieList);
    }

    public void searchGenre(String selectedGenre){

        observableMovies.clear();
        genreResults.clear();
        if(selectedGenre.equals("Filter by Genre")){
            genreResults.addAll(allMovies);
            return;
        }
        //System.out.println(selectedGenre);
        for (Movie movie : allMovies){
            String movieGenres=movie.getGenres().toString();
            if(movieGenres.contains(selectedGenre)){
                genreResults.add(movie);
                //System.out.println(movie.getTitle());
            }

        }
        /*for (Movie movie : genreResults){
            System.out.println(movie.getTitle());
        }*/

    }


    public List<Movie> searchText(List<Movie> genreSearchList,String textValue){
        observableMovies.clear();
        List<Movie> searchResults = FXCollections.observableArrayList();
        String searchTerm = textValue.toLowerCase();
        searchResults.clear();

            for (Movie movie : genreSearchList) {
                if (movie.getTitle().toLowerCase().contains(searchTerm) || movie.getDescription().toLowerCase().contains(searchTerm)) {
                    searchResults.add(movie);
                }
            }
           observableMovies.addAll(searchResults);
            return searchResults;
    }

    /**
     * List will be sorted ascended or descended
     * @param movieList list that has to be sorted
     * @param ascendedOrder true: ascended, false: descended
     */
    public void sortList(List<Movie> movieList, boolean ascendedOrder){
        if (ascendedOrder){
            Collections.sort(movieList);
        }else {
            Collections.sort(movieList,Collections.reverseOrder());
        }
        nullTitlesToEndOfList(movieList);
    }

    /**
     * All Movies with Null as Title are put to the end of the List
     * @param movieList List who will be changed
     */
    public void nullTitlesToEndOfList(List<Movie> movieList){
        List<Movie> nullList = new ArrayList<>();
        for (Movie movie: movieList){
            if(movie.getTitle() == null){
                nullList.add(movie);
            }
        }
        movieList.removeAll(nullList);
        movieList.addAll(nullList);
    }

    /**
     * Delete all Null Objects in the List
     * @param list list that will be changed
     */
    public void setupListWithOutNullObjects(List<Movie> list){
        //while(list.remove(null));
        list.removeAll(Collections.singleton(null));
    }
}