package it.uniroma2.dicii.ispw.thread;

import it.uniroma2.dicii.ispw.bean.FeatureBean;
import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.controller.LoginController;
import it.uniroma2.dicii.ispw.dao.DaoFactory;
import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleThread extends Thread{

    public void run(){

        System.out.println(Thread.currentThread().getName() + " Start spawn new threads!");

        IssueManagementController controller = new IssueManagementController(UserRole.SECRETARY);

        Lock lock = new ReentrantLock();
        ArrayList<IssueBean> data = new ArrayList<IssueBean>();

        IssueBean issueBean = new IssueBean();
        issueBean.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        issueBean.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean = new FeatureBean();
        featureBean.setId(1);
        issueBean.setFeature(featureBean);
        data.add(issueBean);

        IssueBean issueBean2 = new IssueBean();
        issueBean2.setDescription("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        issueBean2.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean2 = new FeatureBean();
        featureBean2.setId(2);
        issueBean2.setFeature(featureBean2);
        data.add(issueBean2);

        IssueBean issueBean3 = new IssueBean();
        issueBean3.setDescription("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        issueBean3.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean3 = new FeatureBean();
        featureBean3.setId(3);
        issueBean3.setFeature(featureBean3);
        data.add(issueBean3);

        IssueBean issueBean4 = new IssueBean();
        issueBean4.setDescription("Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.");
        issueBean4.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean4 = new FeatureBean();
        featureBean4.setId(4);
        issueBean4.setFeature(featureBean4);
        data.add(issueBean4);

        IssueBean issueBean5 = new IssueBean();
        issueBean5.setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        issueBean5.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean5 = new FeatureBean();
        featureBean5.setId(5);
        issueBean5.setFeature(featureBean5);
        data.add(issueBean5);

        IssueBean issueBean6 = new IssueBean();
        issueBean6.setDescription("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?");
        issueBean6.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean6 = new FeatureBean();
        featureBean6.setId(6);
        issueBean6.setFeature(featureBean6);
        data.add(issueBean6);

        IssueBean issueBean7 = new IssueBean();
        issueBean7.setDescription("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur.");
        issueBean7.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean7 = new FeatureBean();
        featureBean7.setId(7);
        issueBean7.setFeature(featureBean7);
        data.add(issueBean7);

        IssueBean issueBean8 = new IssueBean();
        issueBean8.setDescription("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.");
        issueBean8.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean8 = new FeatureBean();
        featureBean8.setId(8);
        issueBean8.setFeature(featureBean8);
        data.add(issueBean8);

        IssueBean issueBean9 = new IssueBean();
        issueBean9.setDescription("No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.");
        issueBean9.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean9 = new FeatureBean();
        featureBean9.setId(9);
        issueBean9.setFeature(featureBean9);
        data.add(issueBean9);

        IssueBean issueBean10 = new IssueBean();
        issueBean10.setDescription("Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure.");
        issueBean10.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean10 = new FeatureBean();
        featureBean10.setId(10);
        issueBean10.setFeature(featureBean10);
        data.add(issueBean10);

        IssueBean issueBean11 = new IssueBean();
        issueBean11.setDescription("To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it?");
        issueBean11.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean11 = new FeatureBean();
        featureBean11.setId(11);
        issueBean11.setFeature(featureBean11);
        data.add(issueBean11);

        IssueBean issueBean12 = new IssueBean();
        issueBean12.setDescription("But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure.");
        issueBean12.setState(IssueState.valueOf("NEW"));
        FeatureBean featureBean12 = new FeatureBean();
        featureBean12.setId(12);
        issueBean12.setFeature(featureBean12);
        data.add(issueBean12);

        Thread thread1 = new Thread(new RunnableThread(data, lock));
        Thread thread2 = new Thread(new RunnableThread(data, lock));
        Thread thread3 = new Thread(new RunnableThread(data, lock));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
