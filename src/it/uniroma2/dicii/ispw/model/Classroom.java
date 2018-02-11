package it.uniroma2.dicii.ispw.model;

/**
 *
 * Class for entity Class room
 *
 * STUB VERSION
 * The development of this class is not expected in this version of the system.
 *
 * @author Andrea Cerra
 */
public class Classroom {

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
