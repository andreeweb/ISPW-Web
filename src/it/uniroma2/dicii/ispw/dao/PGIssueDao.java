package it.uniroma2.dicii.ispw.dao;

import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.enumeration.UserRole;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.interfaces.IssueDao;
import it.uniroma2.dicii.ispw.model.Classroom;
import it.uniroma2.dicii.ispw.model.Feature;
import it.uniroma2.dicii.ispw.model.Issue;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PGIssueDao implements IssueDao {

    private String USER = "ispw";
    private String PASS = "ispw";
    private String DB_URL = "jdbc:postgresql://localhost/ispw_a";
    private String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    @Override
    public List<Issue> getIssues() throws DaoException{

        List<Issue> list = new ArrayList<Issue>();

        Statement stmt = null;
        Connection conn = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "SELECT DISTINCT ON(concrete_feature.feature_id) concrete_feature.feature_id AS concrete_id, " +
                    "issue.issue_id, " +
                    "issue.description, " +
                    "issue.state, " +
                    "classroom.classroom_id, " +
                    "classroom.name AS classroom_name, " +
                    "abstract_feature.name AS feature_name, " +
                    "abstract_feature.feature_id AS feature_id FROM issue " +
                    "JOIN concrete_feature ON (issue.concrete_feature = concrete_feature.classroom_id) " +
                    "JOIN classroom ON (concrete_feature.classroom_id = classroom.classroom_id) " +
                    "JOIN abstract_feature ON (concrete_feature.abstract_id = abstract_feature.feature_id) " +
                    "ORDER BY concrete_feature.feature_id, data desc;";

            // execute
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                // read data
                Issue issue = new Issue();
                issue.setId(rs.getInt("issue_id"));
                issue.setDescription(rs.getString("description"));
                issue.setState(IssueState.valueOf(rs.getString("state")));

                Feature feature = new Feature();
                feature.setName(rs.getString("feature_name"));
                feature.setId(rs.getInt("concrete_id"));

                Classroom classroom = new Classroom();
                classroom.setName(rs.getString("classroom_name"));
                classroom.setId(rs.getInt("classroom_id"));

                issue.setFeature(feature);
                issue.setClassroom(classroom);

                list.add(issue);
            }

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

        return list;

    }

    @Override
    public Issue getIssue(Integer databaseId) throws DaoException {

        Issue issue = null;

        Statement stmt = null;
        Connection conn = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "SELECT issue.issue_id, issue.description, issue.state, classroom.classroom_id, " +
                    "classroom.name AS classroom_name, " +
                    "abstract_feature.name AS feature_name, " +
                    "abstract_feature.feature_id AS feature_id, " +
                    "concrete_feature.feature_id AS concrete_id " +
                    "FROM issue " +
                    "JOIN concrete_feature ON (issue.concrete_feature = concrete_feature.classroom_id) " +
                    "JOIN classroom ON (concrete_feature.classroom_id = classroom.classroom_id) " +
                    "JOIN abstract_feature ON (concrete_feature.abstract_id = abstract_feature.feature_id)" +
                    "WHERE issue.issue_id = " + databaseId;

            // execute
            ResultSet rs = stmt.executeQuery(sql);

            // rs empty
            if (!rs.first())
                throw new DaoException("Issue not found");

            // set cursor
            rs.first();

            // read data
            issue = new Issue();
            issue.setId(rs.getInt("issue_id"));
            issue.setDescription(rs.getString("description"));
            issue.setState(IssueState.valueOf(rs.getString("state")));

            Feature feature = new Feature();
            feature.setName(rs.getString("feature_name"));
            feature.setId(rs.getInt("concrete_id"));

            Classroom classroom = new Classroom();
            classroom.setName(rs.getString("classroom_name"));
            classroom.setId(rs.getInt("classroom_id"));

            issue.setFeature(feature);
            issue.setClassroom(classroom);

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

        return issue;
    }

    /*@Override
    public List<IssueState> getStates() throws DaoException{

        List<IssueState> list = new ArrayList<IssueState>();

        Statement stmt = null;
        Connection conn = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "SELECT * FROM fault_state;";

            // execute
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                // read data
                list.add(IssueState.valueOf(rs.getString("type")));
            }

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

        return list;

    }*/

    @Override
    public List<Issue> getIssueStateStory(Issue issue) throws DaoException {

        List<Issue> list = new ArrayList<Issue>();

        Statement stmt = null;
        Connection conn = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "SELECT concrete_feature.feature_id " +
                    "AS concrete_id, issue.state, issue.data AS date_state " +
                    "FROM issue " +
                    "JOIN concrete_feature ON (issue.concrete_feature = concrete_feature.classroom_id) " +
                    "JOIN classroom ON (concrete_feature.classroom_id = classroom.classroom_id) " +
                    "JOIN abstract_feature ON (concrete_feature.abstract_id = abstract_feature.feature_id) " +
                    "WHERE concrete_feature.feature_id = " + issue.getFeature().getId() +
                    "ORDER BY issue.data;";

            // execute
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                // read data
                Issue is = new Issue();
                is.setState(IssueState.valueOf(rs.getString("state")));
                is.setDate(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(rs.getTimestamp("date_state")));

                list.add(is);
            }

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

        return list;
    }

    @Override
    public void updateIssue(Issue issue) throws DaoException {

        Statement stmt = null;
        Connection conn = null;

        try {

            // get connection
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // create statement
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // query
            String sql = "INSERT INTO issue (description, concrete_feature, state, data) " +
                    "VALUES ('" + issue.getDescription() + "'," + issue.getFeature().getId() + ", '" + issue.getState() + "', now());";

            // execute
            stmt.executeUpdate(sql);

            // Clean-up
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

    }
}
