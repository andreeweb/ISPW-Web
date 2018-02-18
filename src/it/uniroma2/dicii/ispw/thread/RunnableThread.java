package it.uniroma2.dicii.ispw.thread;

import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.exception.DaoException;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class RunnableThread implements Runnable{

    private ArrayList<IssueBean> issueBeans;
    private Lock lock;
    private Random randomGenerator;

    public RunnableThread(ArrayList<IssueBean> issueBeans, Lock reentrantLock) {

        this.issueBeans = issueBeans;
        this.lock = reentrantLock;
        randomGenerator = new Random();
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " Spawn!");

        IssueManagementController controller = new IssueManagementController(UserRole.SECRETARY);

        for (;;){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " Acquire lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " Lock acquired");

            if (issueBeans.size() == 0) {
                lock.unlock();
                break;
            }

            int index = randomGenerator.nextInt(issueBeans.size());
            System.out.println(Thread.currentThread().getName() + " - " + issueBeans.get(index));

            try{

                controller.updateIssue(issueBeans.get(index));
                issueBeans.remove(index);

            }catch (DaoException | IllegalArgumentException e){

                e.printStackTrace();

            }finally {

                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " Lock released");
            }
        }

        System.out.println(Thread.currentThread().getName() + " bye bye");
    }
}
