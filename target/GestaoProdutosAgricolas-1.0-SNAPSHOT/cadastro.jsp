<%-- 
    Document   : cadastro
    Created on : 26/05/2024, 13:43:28
    Author     : valdemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>

        <h1>Efetue um Cadastro</h1>

        <form method="POST" action="CadastroUsuario" onsubmit="return validarCampos()">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Nome de Utilizador" required>
            <br>
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" placeholder="Nome" required>
            <br>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="exemplo@gmail.com" required>
            <br>
            <label for="senha">Senha</label>
            <input type="password" id="senha" name="senha" placeholder="Senha" required>
            <br><br>
            <input type="submit" name="form-cadastro" value="Cadastrar">
            <br><br>
            <a href="index.jsp">login</a>
        </form>

    </body>

    <script>

        function validarCampos() {

            var usuario = document.getElementById("usuario");
            var nome = document.getElementById("nome");
            var email = document.getElementById("email");
            var senha = document.getElementById("senha");

            if (usuario.trim() === "" || nome.trim() === "" || email.trim() === "" || senha.trim() === "") {

                alert("Preencha os campos!");
                return false;

            }

            return true;

        }

    </script>

</html>
