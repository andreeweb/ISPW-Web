package it.uniroma2.dicii.ispw.model;

import it.uniroma2.dicii.ispw.enumeration.IssueState;

/**
 * Class for entity Issue
 *
 * @author Andrea Cerra
 */
public class Issue {

    private Integer issueId;
    private String description;
    private IssueState state;
    private String date;

    private Feature feature;
    private Classroom classroom;

    public Integer getId() {
        return issueId;
    }

    public String getDescription() {
        return description;
    }

    public IssueState getState() {
        return state;
    }

    public String getDate() {
        return date;
    }

    public Feature getFeature() {
        return feature;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setId(Integer issueId) {
        this.issueId = issueId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
