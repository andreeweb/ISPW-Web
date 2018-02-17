<%@ page import="it.uniroma2.dicii.ispw.bean.IssueBean" %>
<%@ page import="it.uniroma2.dicii.ispw.controller.IssueManagementController" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %>
<%@ page import="it.uniroma2.dicii.ispw.enumeration.IssueState" %>
<%@ page import="it.uniroma2.dicii.ispw.enumeration.UserRole" %>
<%@ page import="it.uniroma2.dicii.ispw.model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%

    // Check if this is new comer on your Webpage.
    if (session.getAttribute("login") == null || session.getAttribute("role") != "secretary"){
        // New location to be redirected
        response.sendRedirect("loginView.jsp");
        return;
    }
%>

<%!
    private IssueBean issueBean;
    private IssueManagementController controller;
%>

<%
    String roleString = (String) session.getAttribute("role");
    UserRole role = UserRole.valueOf(roleString.toUpperCase());
    controller = new IssueManagementController(role);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../stylesheets/style.css">
    <script src="../script/main.js"></script>
</head>
<body>

<header>
    <img id="logo-top" src="../images/elephant-alone-w64.png" alt="Elephant App">
</header>

<div class="container">

    <div id="top-bar">
        <table id="table-top">
            <tr>
                <td align="left">Benvenuto!</td>
                <td align="right">
                    <form method="POST" action="controller/logoutController.jsp">
                        <input class="submit-exit" type="submit" value="Esci">
                    </form>
                </td>
            </tr>
        </table>
    </div>


    <div id="main">

        <div id="left-box">

            <div class="detail">

                <form method="POST" action="controller/secretaryViewDetailController.jsp">

                    <%
                        try {

                            issueBean = controller.getIssueBean(Integer.parseInt(request.getParameter("issueId")));

                        } catch (DaoException | NumberFormatException e) {

                            session.setAttribute("error-msg", "Guasto non trovato sul database.");
                            response.sendRedirect("../secretaryView.jsp");

                            e.printStackTrace();
                        }

                    %>

                    <div class="block">
                        <h3>Descrizione</h3>
                        <textarea id="desc-textarea" name="description" title="description"><% out.print(issueBean.getDescription()); %></textarea>
                    </div>

                    <div class="block">
                        <div class="left">
                            <h4>Caratteristica</h4>
                        </div>
                        <div class="right">
                            <h4>Aula</h4>
                        </div>
                    </div>

                    <div class="block">
                        <div class="left">
                            <p><% out.print(issueBean.getFeature().getName()); %></p>
                        </div>
                        <div class="right">
                            <p><% out.print(issueBean.getClassroom().getName()); %></p>
                        </div>
                    </div>

                    <div class="block">
                        <div class="full">
                            <h4>Seleziona nuovo stato</h4>
                        </div>
                    </div>

                    <div class="block">
                        <div class="full">
                            <select name="state" id="soflow">
                            <%
                                try {

                                    List<IssueState> states = controller.getPossibleStateListForIssue(issueBean);

                                    for (IssueState state : states) {

                                        %>
                                        <option value="<% out.print(state); %>"><% out.print(state); %></option>
                                        <%
                                    }

                                    if (states.size() == 0){
                                        %>
                                        <option disabled="disabled">Non &egrave; possibile selezionare ulteriori stati.</option>
                                        <%
                                    }

                                } catch (DaoException e) {

                                    session.setAttribute("error-msg", "Guasto non trovato sul database.");
                                    response.sendRedirect("../secretaryView.jsp");

                                    e.printStackTrace();
                                }

                            %>
                            </select>
                        </div>
                    </div>

                    <div class="block">
                        <div class="full">
                            <input type="hidden" name="concrete_issue_id" value="<% out.print(issueBean.getFeature().getId()); %>">
                            <input class="long-button-blue" type="submit" value="Conferma">
                            <input class="long-button-red" onclick="window.history.go(-1); return false;" value="Annulla">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div id="right-box">

            <div class="detail">

                <h3>Storico guasto</h3>

                <div id="issues_list">

                    <div class="table" id="table-story">

                        <div class="row header blue">
                            <div class="cell">
                                Stato
                            </div>
                            <div class="cell">
                                Data
                            </div>
                        </div>

                        <%
                            try {

                                for (IssueBean iBean : controller.getStateStoryListForIssue(issueBean)) {

                                    %>
                                    <div class="row">
                                        <div class="cell" data-title="state">
                                            <% out.print(iBean.getState()); %>
                                        </div>
                                        <div class="cell" data-title="date">
                                            <% out.print(iBean.getDate()); %>
                                        </div>
                                    </div>
                                    <%
                                }

                            } catch (DaoException e) {

                                session.setAttribute("error-msg", "Guasto non trovato sul database.");
                                response.sendRedirect("../secretaryView.jsp");

                                e.printStackTrace();
                            }

                        %>

                    </div>
                </div>

            </div>
        </div>

    </div>

</div>

</body>
</html>