package it.uniroma2.dicii.ispw.controller;

import it.uniroma2.dicii.ispw.bean.ClassroomBean;
import it.uniroma2.dicii.ispw.bean.FeatureBean;
import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.dao.DaoFactory;
import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.enumeration.Persistence;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;
import it.uniroma2.dicii.ispw.model.Feature;
import it.uniroma2.dicii.ispw.model.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for Issue Management Use Case
 *
 * @author Andrea Cerra
 */
public class IssueManagementController {

    /**
     * Get all isssue from database
     *
     * @return issue list
     * @throws DaoException error in dao
     */
    public List<IssueBean> getIssueBeanList() throws DaoException {

        IssueDao dao = DaoFactory.getSingletonInstance().getIssueDAO(Persistence.PostgreSQL);
        List<Issue> list = dao.getIssues();
        List<IssueBean> beanList = new ArrayList<IssueBean>();

        for (Issue issue:list) {

            // read data
            IssueBean issueBean = new IssueBean();
            issueBean.setId(issue.getId());
            issueBean.setDescription(issue.getDescription());
            issueBean.setState(issue.getState());

            FeatureBean featureBean = new FeatureBean();
            featureBean.setName(issue.getFeature().getName());
            featureBean.setId(issue.getFeature().getId());

            ClassroomBean classroomBean = new ClassroomBean();
            classroomBean.setName(issue.getClassroom().getName());
            classroomBean.setId(issue.getClassroom().getId());

            issueBean.setFeature(featureBean);
            issueBean.setClassroom(classroomBean);

            beanList.add(issueBean);

        }
        
        return beanList;

    }

    /**
     * Get IssueBean from database by ID
     *
     * @return IssueBean
     * @throws DaoException error in dao
     */
    public IssueBean getIssueBean(Integer databaseId) throws DaoException {

        IssueDao dao = DaoFactory.getSingletonInstance().getIssueDAO(Persistence.PostgreSQL);

        // get issue
        Issue issue = dao.getIssue(databaseId);

        // create bean
        IssueBean issueBean = new IssueBean();
        issueBean.setId(issue.getId());
        issueBean.setDescription(issue.getDescription());
        issueBean.setState(issue.getState());

        FeatureBean featureBean = new FeatureBean();
        featureBean.setName(issue.getFeature().getName());
        featureBean.setId(issue.getFeature().getId());

        ClassroomBean classroomBean = new ClassroomBean();
        classroomBean.setName(issue.getClassroom().getName());
        classroomBean.setId(issue.getClassroom().getId());

        issueBean.setFeature(featureBean);
        issueBean.setClassroom(classroomBean);

        return issueBean;

    }

    /**
     * Get all issue state from database
     *
     * @return issue state list
     * @throws DaoException error in db
     */
    public List<IssueState> getStateList() throws DaoException {

        IssueDao dao = DaoFactory.getSingletonInstance().getIssueDAO(Persistence.PostgreSQL);
        return dao.getStates();

    }

    /**
     * Add new entry in db with new state
     *
     * @param bean with new data
     * @throws DaoException error in db
     */
    public void updateIssue(IssueBean bean) throws DaoException {

        IssueDao dao = DaoFactory.getSingletonInstance().getIssueDAO(Persistence.PostgreSQL);

        Issue issue = new Issue();
        issue.setId(bean.getFeature().getId());
        issue.setDescription(bean.getDescription());
        issue.setState(bean.getState());

        dao.updateIssue(issue);
    }

    /**
     * Return list of all state of the issue passed
     *
     * @param issueBean from boundary
     * @return issuebean contains only state and date
     * @throws DaoException error in db
     */
    public List<IssueBean> getStateListForIssue(IssueBean issueBean) throws DaoException {

        IssueDao dao = DaoFactory.getSingletonInstance().getIssueDAO(Persistence.PostgreSQL);

        Issue is = new Issue();
        is.setId(issueBean.getId());

        Feature feature = new Feature();
        feature.setName(issueBean.getFeature().getName());
        feature.setId(issueBean.getFeature().getId());

        is.setFeature(feature);

        List<Issue> list = dao.getIssueStateStory(is);

        List<IssueBean> beanList = new ArrayList<IssueBean>();

        for (Issue issue:list) {

            // read data
            IssueBean isB = new IssueBean();
            isB.setState(issue.getState());
            isB.setDate(issue.getDate());

            beanList.add(isB);

        }

        return beanList;
    }
}
