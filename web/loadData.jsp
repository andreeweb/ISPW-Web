<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%@ page import="it.uniroma2.dicii.ispw.controller.LoginController" %>

<jsp:useBean id="user" class="it.uniroma2.dicii.ispw.bean.UserBean" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<%

    LoginController controller = new LoginController();
    controller.loadData();
    response.sendRedirect("../loginView.jsp");

%>