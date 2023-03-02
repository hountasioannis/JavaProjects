package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceImplTest {

    private static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherService = new TeacherServiceImpl(teacherDAO);
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyTeachers();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetTeacher() throws TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "anna", "gianoutsou");
        teacherService.insertTeacher(teacherDTO);

        List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());
        assertEquals(teachers.size(), 1);
        assertEquals(teachers.get(0).getLastname(), teacherDTO.getLastname());
        assertEquals(teachers.get(0).getFirstname(), teacherDTO.getFirstname());
    }

    @Test
    public void updateTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "thanasis2", "andoutsos2");
        teacherService.updateTeacher(teacherDTO);

        List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());

        assertEquals(teachers.get(0).getLastname(),teacherDTO.getLastname());
        assertEquals(teachers.get(0).getFirstname(),teacherDTO.getFirstname());
    }

    @Test
    public void updateInvalidTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(30, "thanos", "andr");


        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.updateTeacher(teacherDTO);
        });
    }

    @Test
    public void deleteTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "thanasis" , "androutsos");
        teacherService.deleteTeacher(teacherDTO.getId());

        List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());
        assertEquals(0, teachers.size());
    }

    @Test
    public void deleteInvalidTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(40, "thanos", "andr");


        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(teacherDTO.getId());
        });
    }

    public static void createDummyTeachers() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("thanasis");
        teacher.setLastname("androutsos");
        teacherDAO.insert(teacher);


        teacher = new Teacher();
        teacher.setFirstname("alice");
        teacher.setLastname("c");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("john");
        teacher.setLastname("lenon");
        teacherDAO.insert(teacher);

    }
}