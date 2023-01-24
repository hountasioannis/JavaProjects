package gr.aueb.cf.ch4.homework2;

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
