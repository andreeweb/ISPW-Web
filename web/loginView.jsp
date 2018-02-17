<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Elephant login</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./stylesheets/style.css">
</head>
<body>

    <jsp:useBean id="userBean" class = "it.uniroma2.dicii.ispw.bean.UserBean" scope="session"/>
    <jsp:setProperty name="userBean" property="*"/>

    <div class="container">

        <header>
            <img id="logo-top" src="./images/elephant-alone-w64.png" alt="Elephant App">
        </header>

        <div id="login">

            <h3>Benvenuto nell'app Gestione Eventi Elephant</h3>
            <h4>Effettua la login per entrare nel sistema</h4>

            <form method="POST" action="controller/loginViewController.jsp">
                <input class="input-login" type="text" name="username" placeholder="Username" value="andrea.cerra@me.com">
                <br>
                <input class="input-login" type="password" name="password" placeholder="Password" value="password">
                <br>
                <input class="submit-login" type="submit" value="Accedi">
            </form>

            <%
                if (session.getAttribute("error-msg") != null) {
                    %>
                    <h4 class="h4red"> <% out.print(session.getAttribute("error-msg")); %> </h4>
                    <%
                    session.removeAttribute("error-msg");
                }

                session.invalidate();
            %>

        </div>

    </div>

</body>
</html>
