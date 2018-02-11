package it.uniroma2.dicii.ispw.bean;

/**
 * Bean class for entity Classroom
 *
 * @author Andrea Cerra
 */
public class ClassroomBean {

    private Integer classroomId;
    private String  name;

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
        return classroomId;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param classroomId
     */
    public void setId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}
