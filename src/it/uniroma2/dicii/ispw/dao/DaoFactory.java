package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;
import it.uniroma2.dicii.ispw.interfaces.UserDao;
import it.uniroma2.dicii.ispw.utils.Config;

/**
 * Factory class for Data Access Object class
 *
 * @author Andrea Cerra
 */

public class DaoFactory {

    private static DaoFactory instance = null;

    protected DaoFactory(){

    }

    /**
     * Return an istance of concrete user dao using Reflection
     *
     * @return UserDao object
     * @throws DaoException error with database connection or wrong type in config
     */
    public UserDao getUserDAO() throws DaoException{

        String userDaoClass = Config.getSingletonInstance().getProperty("userDaoClass");

        try {

            Class<?> c = Class.forName(userDaoClass);
            return (UserDao) c.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

            e.printStackTrace();
            throw new DaoException("Invalid type : " + userDaoClass, e.getCause());
        }

    }


    /**
     * Return an istance of concrete issue dao using Reflection
     *
     * @return IssueDao object
     * @throws DaoException error with database connection or wrong type in config
     */
    public IssueDao getIssueDAO() throws DaoException{

        String userDaoClass = Config.getSingletonInstance().getProperty("issueDaoClass");

        try {

            Class<?> c = Class.forName(userDaoClass);
            return (IssueDao) c.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

            e.printStackTrace();
            throw new DaoException("Invalid type : " + userDaoClass, e.getCause());
        }
    }

    /**
     * Return or inizialize the factory singleton istance
     *
     * @return reference to singleton istance
     */
    public synchronized static final DaoFactory getSingletonInstance() {

        if (DaoFactory.instance == null)
            DaoFactory.instance = new DaoFactory();
        return instance;
    }
}
