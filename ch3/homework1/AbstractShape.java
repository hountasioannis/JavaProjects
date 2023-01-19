package gr.aueb.cf.ch3.homework1;

/**
 * IShape skeletal impl.
 */
public abstract class AbstractShape implements IShape {
    private long id;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
