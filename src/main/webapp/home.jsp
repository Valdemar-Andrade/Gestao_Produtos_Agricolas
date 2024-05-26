<%-- 
    Document   : home
    Created on : 26/05/2024, 13:27:29
    Author     : valdemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" ref="css/estilo.css">
        <title>Produtos Agricolas</title>
    </head>
    <body>
        <h3>Usuario: <% if(request.getParameter("usuario") != null) {%> <%= request.getParameter("usuario") %> <%}%> <a href="index.jsp">sair</a></h3>
        <h1>Produtos Agricolas</h1>
        <br><br>
        <table>
            <tr>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Preço</th>
            </tr>
        </table>
    </body>
</html>
