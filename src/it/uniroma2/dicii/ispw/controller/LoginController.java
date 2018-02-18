package it.uniroma2.dicii.ispw.controller;

import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.dao.DaoFactory;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.UserDao;
import it.uniroma2.dicii.ispw.model.User;
import it.uniroma2.dicii.ispw.thread.SimpleThread;
import it.uniroma2.dicii.ispw.utils.Sha;

/**
 * Controller class for Login Use Case
 *
 * @author Andrea Cerra
 */

public class LoginController {

    /**
     * Passing a UserBean, this method takes care of
     * validating the username and password received from the boundary.
     *
     * @param userBean user bean which contains username and password received from the boundary
     * @throws DaoException error in user search
     */
    public UserBean validateLogin(UserBean userBean) throws DaoException {

        UserDao dao = DaoFactory.getSingletonInstance().getUserDAO();
        User user = dao.getUserByUsernameAndPassword(userBean.getUsername(), Sha.sha256(userBean.getPassword()));

        userBean.setUserRole(user.getUserRole());
        userBean.setName(user.getName());
        userBean.setSurname(user.getSurname());

        return userBean;
    }

    /**
     *
     * Dummy method, thorugh this we start fill db with dummy data from login page.
     * Check the JSP page loadData.jsp
     *
     * @see it.uniroma2.dicii.ispw.thread.RunnableThread
     * @see SimpleThread
     */
    public void loadData() {
        // Start thread for create dummy data!
        System.out.println("Start main thread");
        SimpleThread thread = new SimpleThread();
        thread.start();

    }
}
