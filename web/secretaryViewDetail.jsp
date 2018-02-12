<%@ page import="it.uniroma2.dicii.ispw.bean.IssueBean" %>
<%@ page import="it.uniroma2.dicii.ispw.controller.IssueManagementController" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%

    // Check if this is new comer on your Webpage.
    if (session.getAttribute("login") == null){
        // New location to be redirected
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%!
    IssueManagementController controller;
    IssueBean issueBean;
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
                    <form method="POST" action="controller/logout.jsp">
                        <input class="submit-exit" type="submit" value="Esci">
                    </form>
                </td>
            </tr>
        </table>
    </div>


    <div id="main">

        <div id="left-box">

            <div class="detail">


                <%
                    controller = new IssueManagementController();

                    try {

                        issueBean = controller.getIssueBean(Integer.parseInt(request.getParameter("issueId")));

                    } catch (DaoException e) {

                        session.setAttribute("error-msg", "Guasto non trovato sul database.");
                        response.sendRedirect("../secretaryView.jsp");

                        e.printStackTrace();
                    }

                %>

                <div class="block">
                    <h3>Descrizione</h3>
                    <textarea id="desc-textarea"><% out.print(issueBean.getDescription()); %></textarea>
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
                        <select id="soflow">
                            <option value="volvo">Volvo</option>
                            <option value="saab">Saab</option>
                            <option value="mercedes">Mercedes</option>
                            <option value="audi">Audi</option>
                        </select>
                    </div>
                </div>

                <div class="block">
                    <div class="full">
                        <form>
                            <input class="long-button-blue" type="submit" onclick="alert('Hello World!')" value="Conferma">
                            <input class="long-button-red" type="submit" onclick="alert('Hello World!')" value="Annulla">
                        </form>
                    </div>
                </div>

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

                                for (IssueBean iBean : controller.getStateListForIssue(issueBean)) {

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