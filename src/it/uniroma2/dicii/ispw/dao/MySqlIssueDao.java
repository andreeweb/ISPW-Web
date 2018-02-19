package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;
import it.uniroma2.dicii.ispw.model.Issue;

import java.util.List;

public class MySqlIssueDao implements IssueDao{

    @Override
    public List<Issue> getIssues() throws DaoException {
        return null;
    }

    @Override
    public Issue getIssue(Integer databaseId) throws DaoException {
        return null;
    }

    @Override
    public List<Issue> getIssueStateStory(Issue issue) throws DaoException {
        return null;
    }

    @Override
    public void updateIssue(Issue issue) throws DaoException {

    }
}
