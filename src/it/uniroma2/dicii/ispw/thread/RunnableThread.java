package it.uniroma2.dicii.ispw.thread;

import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.controller.LoginController;
import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.exception.DaoException;

public class RunnableThread implements Runnable{

    @Override
    public void run() {

        try {

            System.out.println("Runnable Thread:" + Thread.currentThread().getName());
            Thread.sleep(5000);

        } catch (InterruptedException e) {

            System.out.println(e.getMessage());
        }

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

        IssueManagementController ctl = new IssueManagementController();

        IssueBean issueBean = new IssueBean();
        issueBean.setId(4);
        issueBean.setDescription("Thread example description");
        issueBean.setState(IssueState.valueOf("NEW"));

        System.out.println(Thread.currentThread().getName() + " - Insert in db");

        try {

            ctl.updateIssue(issueBean);
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + " - Inserte executed");

        } catch (DaoException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
