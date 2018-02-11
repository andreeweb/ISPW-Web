package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.enumeration.Persistence;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.exception.DatabaseException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;
import it.uniroma2.dicii.ispw.interfaces.UserDao;

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
     * Return an istance of concrete user dao
     *
     * @param type check enum Persistence
     * @return UserDao object
     * @throws DaoException error with database connection or wrong type in config
     */
    public UserDao getUserDAO(Persistence type) throws DaoException{

        switch (type) {

            case PostgreSQL: return new PGUserDao();
            case File: return new FileUserDao();
            default: throw new DaoException("Invalid type : " + type);

        }
    }


    /**
     * Return an istance of concrete issue dao
     *
     * @param type check enum Persistence
     * @return IssueDao object
     * @throws DaoException error with database connection or wrong type in config
     */
    public IssueDao getIssueDAO(Persistence type) throws DaoException{

        switch (type) {

            case PostgreSQL: return new PGIssueDao();
            default: throw new DaoException("Invalid type : " + type);
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
