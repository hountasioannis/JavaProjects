package gr.aueb.cf.schoolapp.rest;


import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;

import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserRestController {
    @Inject
    private IUserService userService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByUsername(@QueryParam("username") String username) {
        List<User> users;
        try {
            users = userService.getUsersByUsername(username);
            List<UserDTO> usersDTO = new ArrayList<>();
            for (User user : users) {
                usersDTO.add(new UserDTO(user.getId(),user.getPassword(),user.getUsername()));
            }
            return Response.status(Response.Status.OK).entity(usersDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") Long userId) {
        User user;
        try {
            user = userService.getUserById(userId);
            UserDTO dto = new UserDTO(user.getId(),user.getPassword(), user.getUsername());
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(UserDTO dto, @Context UriInfo uriInfo){
        try {
            User user = userService.insertUser(dto);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(userDTO.getId())).build()).entity(userDTO).build();
        } catch (EntityAlreadyExistsException | RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("user already exists").build();
        }
    }


    @Path("/{userId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Long userId, UserDTO dto) {
        try {
            dto.setId(userId);
            User user = userService.updateUser(dto);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            return Response.status(Response.Status.OK).entity(userDTO).build();
        }  catch (EntityNotFoundException  e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("user already exists").build();
        }
    }

    @Path("/{userId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") Long userId) {
        try {
            User user = userService.getUserById(userId);
            userService.deleteUser(userId);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            return Response.status(Response.Status.OK).entity(userDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }
}

