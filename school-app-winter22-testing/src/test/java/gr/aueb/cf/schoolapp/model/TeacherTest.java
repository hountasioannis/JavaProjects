package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void gettersSetters() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        assertEquals(teacher.getId(), 1);

        teacher.setFirstname("thanasis");
        assertEquals(teacher.getFirstname(),"thanasis");

        teacher.setLastname("androtsos");
        assertEquals(teacher.getLastname(),"androtsos");
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(2, "anna", "giannoutsou");
        assertEquals(teacher.getId(),2);
        assertEquals(teacher.getFirstname(),"anna");
        assertEquals(teacher.getLastname(),"giannoutsou");
        String expected = "2, anna, giannoutsou";
        assertEquals(expected,teacher.toString());
    }

}