package gr.aueb.cf.schoolapp.rest;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("/teachers")
public class TeacherRestController {
    @Inject
    private ITeacherService teacherService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachersByLastname(@QueryParam("lastname") String lastname) {
        List<Teacher> teachers;
        try {
            teachers = teacherService.getTeachersByLastname(lastname);
            List<TeacherDTO> teachersDTO = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teachersDTO.add(new TeacherDTO(teacher.getId(),teacher.getFirstname(),teacher.getLastname()));
            }
            return Response.status(Response.Status.OK).entity(teachersDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }

    @Path("/{teacherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher(@PathParam("teacherId") Long teacherId) {
        Teacher teacher;
        try {
            teacher = teacherService.getTeacherById(teacherId);
            TeacherDTO dto = new TeacherDTO(teacher.getId(),teacher.getFirstname(), teacher.getLastname());
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTeacher(TeacherDTO dto, @Context UriInfo uriInfo){
        try {
            Teacher teacher = teacherService.insertTeacher(dto);
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setFirstname(teacher.getFirstname());
            teacherDTO.setLastname(teacher.getLastname());
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(teacherDTO.getId())).build()).entity(teacherDTO).build();
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("teacher already exists").build();
        }
    }


    @Path("/{teacherId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") Long teacherId, TeacherDTO dto) {
        try {
            dto.setId(teacherId);
            Teacher teacher = teacherService.updateTeacher(dto);
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setFirstname(teacher.getFirstname());
            teacherDTO.setLastname(teacher.getLastname());
            return Response.status(Response.Status.OK).entity(teacherDTO).build();
        }  catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }

    @Path("/{teacherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeacher(@PathParam("teacherId") Long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            teacherService.deleteTeacher(teacherId);
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setFirstname(teacher.getFirstname());
            teacherDTO.setLastname(teacher.getLastname());
            return Response.status(Response.Status.OK).entity(teacherDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
        }
    }
}
