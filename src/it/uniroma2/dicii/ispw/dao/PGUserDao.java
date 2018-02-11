package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.interfaces.UserDao;
import it.uniroma2.dicii.ispw.model.User;
import it.uniroma2.dicii.ispw.exception.DaoException;
import java.sql.*;

/**
 * Concrete implementation of UserDao interfaces
 * this implementation works with PostgreSQL database.
 *
 * @author Andrea Cerra
 *
 */

public class PGUserDao implements UserDao {

    private String USER = "ispw";
    private String PASS = "ispw";
    private String DB_URL = "jdbc:postgresql://localhost/ispw_a";
    private String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    public User getUserByUsernameAndPassword(String username, String password) throws DaoException {

        Statement stmt = null;
        Connection conn = null;
        User user = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "SELECT * " + "FROM system_user where username = '" + username + "' AND password = '" + password + "';";

            // execute
            ResultSet rs = stmt.executeQuery(sql);

            // rs not empty
            if (!rs.first())
                throw new DaoException("User not found");

            // ops..
            boolean moreThanOne = rs.first() && rs.next();
            if (moreThanOne)
                throw new DaoException("Multiple user in DB with same credentials");

            // set cursor
            rs.first();

            // read data
            user = new User();
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setUsername(rs.getString("username"));
            user.setUserRole(UserRole.valueOf(rs.getString("role"))); // todo by name
            //user.setPassword("");

            // Clean-up
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {

            // driver not found, go out
            e.printStackTrace();
            System.exit(1);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {

            try {

                if (stmt != null)
                    stmt.close();

                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }

        return user;
    }

}
