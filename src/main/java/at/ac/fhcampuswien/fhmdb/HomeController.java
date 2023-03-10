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

    private  ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        genreComboBox.setOnAction(actionEvent -> {
            String selectedGenre = genreComboBox.getValue().toString();
            System.out.println(selectedGenre);
            ObservableList<Movie> genreResults = FXCollections.observableArrayList();
            for (Movie movie : allMovies){
                System.out.println(selectedGenre+" "+ movie.getGenres());
                if(movie.getGenres().contains(selectedGenre)) genreResults.add(movie);

            }
            observableMovies.clear();
            observableMovies.addAll(genreResults);
        });

        searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            String searchTerm = newValue.toLowerCase();
            ObservableList<Movie> searchResults = FXCollections.observableArrayList();

            for (Movie movie : allMovies) {
                if (movie.getTitle().toLowerCase().contains(searchTerm) || movie.getDescription().toLowerCase().contains(searchTerm)|| movie.getGenres().contains(searchTerm)) {
                    searchResults.add(movie);
                }
            }

            observableMovies.clear();
            observableMovies.addAll(searchResults);
        });

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
}