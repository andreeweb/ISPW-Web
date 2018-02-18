package it.uniroma2.dicii.ispw.junit;

import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.exception.DaoException;
import org.junit.Test;

public class IssueManagementControllerTest  {

    @Test (expected = DaoException.class)
    public void getIssueBeanTest() throws DaoException {

        IssueManagementController controller = new IssueManagementController(UserRole.SECRETARY);
        controller.getIssueBean(-99);
    }
}
