<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 31/01/18
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<hmtl>
<head>
  <title>Dashboard</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="stylesheets/style.css">
</head>
<body>
<%

  // Check if this is new comer on your Webpage.
  if (session.getAttribute("login") == null){
    // New location to be redirected
    response.sendRedirect("loginView.jsp");
  }

%>

</body>
</hmtl>
