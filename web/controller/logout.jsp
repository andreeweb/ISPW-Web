<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%
    session.invalidate();
    response.sendRedirect("../login.jsp");
%>