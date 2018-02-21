package it.uniroma2.dicii.ispw.junit;

import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.exception.DaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class ParametrizedIssueManagementTest {

    private Integer expected;
    private Integer parameter;

    @Parameterized.Parameters
    public static Collection<Integer[]> getParameters(){
        return Arrays.asList(new Integer[][]{
                {1617, 1617}, // expected, parameter
                {1618, 1618}, // expected, parameter
        });
    }

    public ParametrizedIssueManagementTest(Integer expected, Integer parameter){
        this.expected = expected;
        this.parameter = parameter;
    }

    @Test
    public void getIssueTest() throws DaoException {

        IssueManagementController controller = new IssueManagementController(UserRole.SECRETARY);
        IssueBean bean = controller.getIssueBean(this.parameter);
        Assert.assertEquals((long)this.expected, bean.getId(), 0);
    }
}
