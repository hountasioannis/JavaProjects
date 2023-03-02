package gr.aueb.cf.schoolapp.dto;

import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDTOTest {

    @Test
    void gettersSetters() {
        TeacherDTO teacher = new TeacherDTO();
        teacher.setId(1);
        assertEquals(teacher.getId(), 1);

        teacher.setFirstname("thanasis");
        assertEquals(teacher.getFirstname(), "thanasis");

        teacher.setLastname("androtsos");
        assertEquals(teacher.getLastname(), "androtsos");
    }

    @Test
    void overloadedConstructor() {
        TeacherDTO teacher = new TeacherDTO(2, "anna", "giannoutsou");
        assertEquals(teacher.getId(), 2);
        assertEquals(teacher.getFirstname(), "anna");
        assertEquals(teacher.getLastname(), "giannoutsou");
    }
}