package gr.aueb.cf.moviesfavouritespro.rest;

import gr.aueb.cf.moviesfavouritespro.dto.UserMoviesDTO;
import gr.aueb.cf.moviesfavouritespro.service.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/favorites")
public class UserMoviesController {

    private Service service = new Service();

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavorites(@PathParam("userId") String userId) {
        List<List<String>> favorites = service.getFavorites(userId);

        if (favorites.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("no favorites movies").build();
        }

        return Response.status(Response.Status.OK).entity(favorites).build();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFavorite(UserMoviesDTO userMoviesDTO) {
        boolean isInserted = service.insertUserMovies(userMoviesDTO);

        if (!isInserted) {
            return Response.status(Response.Status.BAD_REQUEST).entity("bad request").build();
        }

        return Response.status(Response.Status.OK).entity("favorite inserted").build();
    }

    @Path("/{userId}/{movieId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFavorite(@PathParam("userId") String userId, @PathParam("movieId") String movieId) {
        List<List<String>> favorites = service.remove(userId, movieId);

        if (favorites.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("no favorites movies").build();
        }

        return Response.status(Response.Status.OK).entity(favorites).build();
    }
}
