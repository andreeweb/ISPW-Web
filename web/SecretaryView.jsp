<%--
  Created by IntelliJ IDEA.
  User: Andrea Cerra
--%>

<%

    // Check if this is new comer on your Webpage.
    if (session.getAttribute("login") == null){
        // New location to be redirected
        response.sendRedirect("login.jsp");
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
                    <form method="POST" action="controller/logout.jsp">
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

            <div class="row">
                <div class="cell" data-title="Username">
                    ninjalug
                </div>
                <div class="cell" data-title="Email">
                    misterninja@hotmail.com
                </div>
                <div class="cell" data-title="Password">
                    ************
                </div>
                <div class="cell" data-title="Active">
                    Yes
                </div>
                <div class="cell" data-title="pippo">
                    A1
                </div>
                <div class="cell" data-title="pippo">
                    <a href="www.google.it"> Dettagli </a>
                </div>
            </div>

        </div>
    </div>

    <div id="info" class="tabcontent">
        <h3>Informazioni</h3>
    </div>

</div>

<footer>Copyright &copy; Andrea Cerra ISPW 2017/2018</footer>

<script>
    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>

</body>
</html>