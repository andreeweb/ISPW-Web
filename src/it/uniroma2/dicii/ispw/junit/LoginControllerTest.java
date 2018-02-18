package it.uniroma2.dicii.ispw.junit;

import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.controller.LoginController;
import it.uniroma2.dicii.ispw.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class LoginControllerTest {

    private UserBean userBean;

    @Before
    public void init(){

        userBean = new UserBean();
        userBean.setUsername("fakeusername");
        userBean.setPassword("fakepassword");
    }

    @Test
    public void testLoginExceptionMethod1()  {

        LoginController loginController = new LoginController();
        try {
            loginController.validateLogin(userBean);
            Assert.fail();
        } catch (DaoException e) {
            e.printStackTrace();
            Assert.assertEquals("User not found", e.getMessage());
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoginExceptionMethod2() throws DaoException {

        thrown.expect(DaoException.class);
        thrown.expectMessage(containsString("User not found"));

        LoginController loginController = new LoginController();
        loginController.validateLogin(userBean);
    }

    @Test (expected = DaoException.class)
    public void testLoginExceptionMethod3() throws DaoException {

        LoginController loginController = new LoginController();
        loginController.validateLogin(userBean);
    }

}
