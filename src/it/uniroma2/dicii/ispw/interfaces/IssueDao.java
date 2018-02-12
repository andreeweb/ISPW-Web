package it.uniroma2.dicii.ispw.interfaces;

import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.model.Issue;

import java.util.List;

/**
 * Interface DAO for entity Issue
 *
 * @author Andrea Cerra
 */

public interface IssueDao {

    /**
     * Get all issue from database
     *
     * @return array
     */
    List<Issue> getIssues() throws DaoException;

    /**
     * Get issue from database by ID
     *
     * @return issue
     */
    Issue getIssue(Integer databaseId) throws DaoException;

    /**
     * Get all possibile state for issue from db
     *
     * @return List contains Issue State
     * @throws DaoException error in db
     * @see IssueState
     */
    List<IssueState> getStates() throws DaoException;

    /**
     * Get all issue state with data
     *
     * @return List contains Issue Object with field state and data
     * @throws DaoException error in db
     */
    List<Issue> getIssueStateStory(Issue issue) throws DaoException;

    /**
     * Update issue data on db
     *
     * @param issue to update
     * @throws DaoException error in db
     */
    void updateIssue(Issue issue) throws DaoException;
}
