package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

public class TeacherNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher) {
        super("teacher does not exist");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
