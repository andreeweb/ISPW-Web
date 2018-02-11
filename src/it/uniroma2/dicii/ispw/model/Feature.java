package it.uniroma2.dicii.ispw.model;

/**
 *
 * Class for entity Feature
 *
 * STUB VERSION
 * The development of this class is not expected in this version of the system.
 *
 * @author Andrea Cerra
 */
public class Feature {

    private Integer featureId;
    private String name;

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return featureId;
    }

    /**
     *
     * @param title
     */
    public void setName(String title) {
        this.name = title;
    }

    /**
     *
     * @param featureId
     */
    public void setId(Integer featureId) {
        this.featureId = featureId;
    }
}
