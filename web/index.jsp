<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 31/01/18
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<BODY>
<%
  // This is a scriptlet.  Notice that the "date"
  // variable we declare here is available in the
  // embedded expression later on.
  System.out.println( "Evaluating date now" );
  java.util.Date date = new java.util.Date();
%>
Hello!  The time is now <%= date %>
</BODY>
</HTML>
