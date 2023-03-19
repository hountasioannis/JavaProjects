package gr.aueb.cf.moviesfavouritespro.dto;

public class UserMoviesDTO {
    private String userId;
    private String movieId;
    private String movieTitle;

    public UserMoviesDTO() {
    }

    public UserMoviesDTO(String userId, String movieId, String movieTitle) {
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
}
