package it.uniroma2.dicii.ispw.bean;

import it.uniroma2.dicii.ispw.enumeration.IssueState;

public class IssueBean {

    private Integer issueId;
    private String description;
    private IssueState state;
    private String date;
    private FeatureBean feature;
    private ClassroomBean classroom;

    /**
     *
     * @return
     */
    public Integer getId() {
        return issueId;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public IssueState getState() {
        return state;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public FeatureBean getFeature() {
        return feature;
    }

    /**
     *
     * @return
     */
    public ClassroomBean getClassroom() {
        return classroom;
    }

    /**
     *
     * @param issueId
     */
    public void setId(Integer issueId) {
        this.issueId = issueId;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param state
     */
    public void setState(IssueState state) {
        this.state = state;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param feature
     */
    public void setFeature(FeatureBean feature) {
        this.feature = feature;
    }

    /**
     *
     * @param classroom
     */
    public void setClassroom(ClassroomBean classroom) {
        this.classroom = classroom;
    }


    @Override
    public String toString() {
        return "IssueBean{" +
                "issueId=" + issueId +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", date='" + date + '\'' +
                ", feature=" + feature +
                ", classroom=" + classroom +
                '}';
    }
}
