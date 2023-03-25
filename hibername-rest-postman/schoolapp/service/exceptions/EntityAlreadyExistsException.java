package gr.aueb.cf.schoolapp.service.exceptions;

public class EntityAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(Class<?> entityClass, Long id) {
        super("entity " + entityClass.getSimpleName() + " with id " + id + " already exists");
    }
}
