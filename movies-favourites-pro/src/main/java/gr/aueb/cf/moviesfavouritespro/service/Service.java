package gr.aueb.cf.moviesfavouritespro.service;

import gr.aueb.cf.moviesfavouritespro.dto.UserMoviesDTO;
import gr.aueb.cf.moviesfavouritespro.model.UserMovies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {
   private static final List<List<String>> favorites = new ArrayList<>();

    public boolean insertUserMovies(UserMoviesDTO userMoviesDTO) {

        UserMovies userMovies = mapUserMovies(userMoviesDTO);
        if ((userMovies.getUserId() == null) || (userMovies.getMovieId() == null)) {
            return false;
        } else {
            favorites.add(Arrays.asList(userMovies.getUserId(), userMovies.getMovieId(), userMovies.getMovieTitle()));
            favorites.forEach(System.out::println);
            return true;
        }
    }

    public List<List<String>> getFavorites(String userId) {
         List<List<String>> favorites2 = new ArrayList<>();
        System.out.println("ok");
        for (int i = 0; i < favorites.size(); i++) {
                if (favorites.get(i).get(0).equals(userId)) {
                    favorites2.add(Arrays.asList(favorites.get(i).get(0), favorites.get(i).get(1), favorites.get(i).get(2)));
                }
        }

        return favorites2;
    }


    public List<List<String>> remove(String userId, String movieId) {
        for (int i = 0; i < favorites.size(); i++) {
            if ((favorites.get(i).get(0).equals(userId)) && (favorites.get(i).get(1).equals(movieId))) {
                favorites.remove(favorites.get(i));
            }
        }

        return getFavorites(userId);
    }



    private UserMovies mapUserMovies(UserMoviesDTO userMoviesDTO) {
        return new UserMovies(userMoviesDTO.getUserId(), userMoviesDTO.getMovieId(), userMoviesDTO.getMovieTitle());
    }
}
