package gr.aueb.cf.schoolapp.service.exceptions;

public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("entity " + entityClass.getSimpleName() + " with id " + id + " does not exist");
    }
}
