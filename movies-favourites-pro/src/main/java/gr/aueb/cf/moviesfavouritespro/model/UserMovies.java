package gr.aueb.cf.moviesfavouritespro.model;

import java.util.Objects;

public class UserMovies {
    private String userId;
    private String movieId;
    private String movieTitle;

    public UserMovies() {
    }

    public UserMovies(String userId, String movieId, String movieTitle) {
        this.userId = userId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMovies that = (UserMovies) o;
        return Objects.equals(userId, that.userId) && Objects.equals(movieId, that.movieId) && Objects.equals(movieTitle, that.movieTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId, movieTitle);
    }
}
