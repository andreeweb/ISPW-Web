<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%@ page import="it.uniroma2.dicii.ispw.controller.IssueManagementController" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %>
<%@ page import="it.uniroma2.dicii.ispw.bean.IssueBean" %>
<%@ page import="it.uniroma2.dicii.ispw.enumeration.IssueState" %>

<%

    if ("POST".equalsIgnoreCase(request.getMethod())) {

        // check form fields
        if (request.getParameter("description") == null ||
                request.getParameter("state") == null ||
                request.getParameter("issue_id") == null){

            response.sendRedirect("../secretaryView.jsp");
            return;
        }

        try {

            IssueManagementController controller = new IssueManagementController();

            IssueBean bean = new IssueBean();
            bean.setId(Integer.parseInt(request.getParameter("issue_id")));
            bean.setDescription(request.getParameter("description"));
            bean.setState(IssueState.valueOf(request.getParameter("state")));

            controller.updateIssue(bean);

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