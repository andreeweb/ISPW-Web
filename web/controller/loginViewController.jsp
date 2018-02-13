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
        if (user.getUsername() == null || user.getPassword() == null){

            session.setAttribute("error-msg", "Inserire username e password.");
            response.sendRedirect("../loginView.jsp");
            return;
        }

        try {

            LoginController controller = new LoginController();
            user = controller.validateLogin(user);

            // save info in session
            session.setAttribute("login", Sha.sha256(user.getUsername()));

            switch (user.getUserRole()){

                case SECRETARY:
                    session.setAttribute("role", "secretary");
                    response.sendRedirect("../secretaryView.jsp");
                    break;
                case TECHNICIAN:
                    session.setAttribute("role", "technician");
                    response.sendRedirect("../technicianView.jsp");
                    break;
            }

        } catch (DaoException e) {

            session.setAttribute("error-msg", "Username e/o password errati.");
            response.sendRedirect("../loginView.jsp");

            e.printStackTrace();
        }

    }else{

        response.sendRedirect("../loginView.jsp");
    }

%>