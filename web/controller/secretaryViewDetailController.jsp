<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%@ page import="it.uniroma2.dicii.ispw.controller.IssueManagementController" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %>
<%@ page import="it.uniroma2.dicii.ispw.bean.IssueBean" %>
<%@ page import="it.uniroma2.dicii.ispw.enumeration.IssueState" %>
<%@ page import="it.uniroma2.dicii.ispw.model.Feature" %>
<%@ page import="it.uniroma2.dicii.ispw.bean.FeatureBean" %>

<%

    if ("POST".equalsIgnoreCase(request.getMethod())) {

        // check form fields
        if (request.getParameter("description") == null ||
                request.getParameter("state") == null ||
                request.getParameter("concrete_issue_id") == null){

            response.sendRedirect("../secretaryView.jsp");
            return;
        }

        try {

            IssueManagementController controller = new IssueManagementController();

            IssueBean issueBean = new IssueBean();
            issueBean.setDescription(request.getParameter("description"));
            issueBean.setState(IssueState.valueOf(request.getParameter("state")));

            FeatureBean featureBean = new FeatureBean();
            featureBean.setId(Integer.valueOf(request.getParameter("concrete_issue_id")));

            issueBean.setFeature(featureBean);

            controller.updateIssue(issueBean);

            response.sendRedirect("../secretaryView.jsp");

        } catch (DaoException e) {

            session.setAttribute("error-msg", "Errore nell'aggiornamento dei dati.");
            response.sendRedirect("../secretaryView.jsp");

            e.printStackTrace();
        }

    }else{

        response.sendRedirect("../secretaryView.jsp");
    }

%>