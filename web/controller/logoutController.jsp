<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%
    session.invalidate();
    response.sendRedirect("../loginView.jsp");
%>