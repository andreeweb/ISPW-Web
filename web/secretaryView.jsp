<%@ page import="it.uniroma2.dicii.ispw.controller.IssueManagementController" %>
<%@ page import="it.uniroma2.dicii.ispw.bean.IssueBean" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %><%--
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

    <!-- Tab links -->
    <div class="tab">
        <button class="tablinks" onclick="selectTab(event, 'issues_list')" id="defaultOpen">Lista Guasti</button>
        <button class="tablinks" onclick="selectTab(event, 'info')">Informazioni</button>
    </div>

    <%
        if (session.getAttribute("error-msg") != null) {

            %>
            <h4 class="h4red"> <% out.print(session.getAttribute("error-msg")); %> </h4>
            <%

            session.removeAttribute("error-msg");
        }
    %>

    <!-- Tab content -->
    <div id="issues_list" class="tabcontent">
        <div class="table">

            <div class="row header blue">
                <div class="cell">
                    ID
                </div>
                <div class="cell">
                    Descrizione
                </div>
                <div class="cell">
                    Caratteristica
                </div>
                <div class="cell">
                    Stato
                </div>
                <div class="cell">
                    Aula
                </div>
                <div class="cell">
                    Dettaglio
                </div>
            </div>

            <%
                try {

                    IssueManagementController controller = new IssueManagementController();

                    for (IssueBean bean : controller.getIssueBeanList()) {

                        %>
                        <div class="row">
                            <div class="cell" data-title="ID">
                                <% out.print(bean.getId()); %>
                            </div>
                            <div class="cell" data-title="Description">
                                <% out.print(bean.getDescription()); %>
                            </div>
                            <div class="cell" data-title="characteristic">
                                <% out.print(bean.getFeature().getName()); %>
                            </div>
                            <div class="cell" data-title="State">
                                <% out.print(bean.getState()); %>
                            </div>
                            <div class="cell" data-title="Room">
                                <% out.print(bean.getClassroom().getName()); %>
                            </div>
                            <div class="cell" data-title="Detail">
                                <a href="secretaryViewDetail.jsp?issueId=<%out.print(bean.getId());%>"> Dettaglio </a>
                            </div>
                        </div>
                        <%
                    }

                } catch (DaoException e) {

                    session.setAttribute("error-msg", "Errore nella connessione con il database.");
                    response.sendRedirect("../loginView.jsp");

                    e.printStackTrace();
                }

            %>

        </div>
    </div>

    <div id="info" class="tabcontent">
        <h3>Informazioni</h3>
    </div>

</div>

<script>
    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>

</body>
</html>