package it.uniroma2.dicii.ispw.thread;

import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.controller.LoginController;
import it.uniroma2.dicii.ispw.exception.DaoException;

public class SimpleThread extends Thread{

    public void run(){

        System.out.println(Thread.currentThread().getName() + " - Hello i am a Thread.");

        System.out.println(Thread.currentThread().getName() + " - Login.");

        UserBean bean = new UserBean("pinco.pallino@me.com", "password");

        LoginController controller = new LoginController();
        try {
            bean = controller.validateLogin(bean);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - logged as: " + bean.getUserRole().toString());

    }

}
