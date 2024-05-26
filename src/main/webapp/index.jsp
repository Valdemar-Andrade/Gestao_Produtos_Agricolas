<%-- 
    Document   : login
    Created on : 25/05/2024, 17:28:28
    Author     : valdemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" ref="css/estilo.css">
    </head>
    <body>
        <div id="form-login">
            <h1>Login</h1>
            <h6>
                <% if ( request.getParameter( "msg" ) != null ) {%> <%= request.getParameter( "msg" ) %> <%}%>
            </h6>

            <form method="POST" action="FazerLogin" onsubmit="return validarCampos()">

                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" placeholder="Nome de Usuario" required>
                <br>
                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha" placeholder="Senha" required>
                <br><br>
                <input type="submit" name="form_login" value="Entrar">
                <br><br>
                <a href="cadastro.jsp">cadastrar</a>

            </form>
        </div>
    </body>

    <script>

        function validarCampos() {
            var usuario = document.getElementById("usuario").value;
            var senha = document.getElementById("senha").value;

            if (usuario.trim() === "" || senha.trim() === "") {

                alert("Preencha os campos!");
                return false;

            }

            return true;
        }

    </script>
</html>
