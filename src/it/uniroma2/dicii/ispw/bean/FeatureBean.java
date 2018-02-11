package it.uniroma2.dicii.ispw.bean;

/**
 * Bean class for entity Feature
 *
 * @author Andrea Cerra
 */
public class FeatureBean {

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
