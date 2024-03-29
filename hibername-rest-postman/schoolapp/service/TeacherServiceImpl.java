package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import gr.aueb.cf.schoolapp.service.util.LoggerUtil;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Named("teacherServiceImpl")
public class TeacherServiceImpl implements ITeacherService {
    @Inject
    private ITeacherDAO teacherDAO;
    @Override
    public Teacher insertTeacher(TeacherDTO teacherDTO) throws EntityAlreadyExistsException {
        Teacher teacher = null;

        try {
            JPAHelper.beginTransaction();
            teacher = map(teacherDTO);
            if (teacherDTO.getId() == null) {
                teacher = teacherDAO.insert(teacher);
            } else {
                throw new EntityAlreadyExistsException(Teacher.class, teacher.getId());
            }
            JPAHelper.commitTransaction();
        } catch (EntityAlreadyExistsException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("insert teacher " +" rollback " + " entity already exists");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacher;
    }

    @Override
    public Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException {
        Teacher teacherToUpdate = null;
        try {
            JPAHelper.beginTransaction();
            teacherToUpdate = map(teacherDTO);
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
            throw new EntityNotFoundException(Teacher.class, teacherToUpdate.getId());
            }
            teacherDAO.update(teacherToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("update teacher " +" rollback " + " entity not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacherToUpdate;
    }

    @Override
    public void deleteTeacher(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (teacherDAO.getById(id) == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }

            teacherDAO.delete(id);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("delete teacher " +" rollback ");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers;
        try {
            JPAHelper.beginTransaction();
            teachers = teacherDAO.getByLastname(lastname);
            if (teachers.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("get teacher " +" rollback " + " teacher not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teachers;
    }

    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Teacher teacher = null;
        try {
            JPAHelper.beginTransaction();
            teacher = teacherDAO.getById(id);
            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("get teacher by id " +" rollback " + " teacher not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacher;
    }

    private Teacher map(TeacherDTO dt) {
        Teacher teacher = new Teacher();
        teacher.setId(dt.getId());
        teacher.setFirstname(dt.getFirstname());
        teacher.setLastname(dt.getLastname());
        return teacher;
    }
}
