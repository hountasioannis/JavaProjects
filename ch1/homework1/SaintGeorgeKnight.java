package gr.aueb.cf.ch1.homework1;

/**
 * Defines a Singleton Saint George knight
 * with eager instantiation.
 */
public class SaintGeorgeKnight {
    private static final SaintGeorgeKnight INSTANCE = new SaintGeorgeKnight();

    private SaintGeorgeKnight() {}

    public static SaintGeorgeKnight getInstance() {
        return INSTANCE;
    }

    public void embarkOnMission() {
        System.out.println("embark on mission");
    }
}
