package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.interfaces.UserDao;
import it.uniroma2.dicii.ispw.model.User;
import it.uniroma2.dicii.ispw.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Concrete implementation of UserDao interfaces
 * this implementation works with File.
 *
 * @author Andrea Cerra
 *
 */

public class FileUserDao implements UserDao{

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws DaoException {

        ObjectInputStream in;
        User user = null;

        try {

            in = new ObjectInputStream(new FileInputStream("./user.out"));

            ArrayList<User> list = (ArrayList<User>) in.readObject();

            for (User aList : list) {

                user = aList;

                if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)) {
                    return user;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new DaoException(e.getMessage());

        }

        // if reach this line user was not found
        throw new DaoException("User not found");
    }
}
