<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%@ page import="it.uniroma2.dicii.ispw.controller.LoginController" %>
<%@ page import="it.uniroma2.dicii.ispw.exception.DaoException" %>
<%@ page import="it.uniroma2.dicii.ispw.utils.Sha" %>

<jsp:useBean id="user" class="it.uniroma2.dicii.ispw.bean.UserBean" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<%

    if ("POST".equalsIgnoreCase(request.getMethod())) {

        // check form fields
        if (request.getParameter("username") == null || request.getParameter("password") == null){
            response.sendRedirect("../login.jsp");
        }

        try {

            LoginController controller = new LoginController();
            user = controller.validateLogin(user);

            // save info in session
            session.setAttribute("login", Sha.sha256(user.getUsername()));

            switch (user.getUserRole()){

                case SECRETARY:
                    System.out.println("UTENTE SEGRETARIO");
                    response.sendRedirect("../SecretaryView.jsp");
                    break;
                case TECHNICIAN:
                    System.out.println("UTENTE TECNICO");
                    response.sendRedirect("../login.jsp");
                    break;
            }

        } catch (DaoException e) {

            session.setAttribute("error-msg", "Username e/o password errati.");
            response.sendRedirect("../login.jsp");

            e.printStackTrace();
        }

    }else{

        response.sendRedirect("../login.jsp");
    }

%>