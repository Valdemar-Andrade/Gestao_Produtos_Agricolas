<%-- 
    Document   : home
    Created on : 26/05/2024, 13:27:29
    Author     : valdemar
--%>

<%@page import="java.util.List"%>
<%@page import="DAO.ProdutoDAO"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtos = produtoDao.listar();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css">
        <title>Produtos Agricolas</title>
    </head>
    <body>
        <h3>Usuario: <% if ( request.getParameter( "usuario" ) != null ) {%> <%= request.getParameter( "usuario" ) %> <%}%> <a href="index.jsp">sair</a></h3>
        <h1>Produtos Agricolas</h1>

        <div id="form-cadastrar-produto">
            <form method="POST" action="GerirProduto" onsubmit="return validarCampos()">

                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" placeholder="Nome do Produto" required>
                <br>
                <label for="qtd">Quantidade</label>
                <input type="number" id="qtd" name="quantidade" placeholder="Quantidade" required>
                <br>
                <label for="preco">Preço</label>
                <input type="number" id="preco" name="preco" placeholder="Preço Unidade" required> Kz
                <br><br>
                <input type="hidden" name="usuario" value="<%= request.getParameter( "usuario") %>">
                <input type="submit" value="Cadastrar Produto" name="form-cadastro-produto">

            </form>
        </div>

        <br><br>

        <table id="tabela-produtos">
            <tr>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Ação</th>
            </tr>
            <%
                for ( Produto produto : produtos ) {
                    System.out.println( "Produto: " + produto.getDescricao() );
            %>
            <tr>
                <td><%= produto.getDescricao() %></td>
                <td><%= produto.getQuantidade() %></td>
                <td><%= produto.getPreco() %></td>
                <td>
                    <form method="POST" action="GerirProduto">
                        <input type="hidden" name="pk" value="<%= produto.getPkProduto() %>">
                        <input type="text" name="novo" placeholder="Nova descrição" required>
                        <input type="hidden" name="usuario" value="<%= request.getParameter( "usuario") %>">
                        <input type="submit" name="form-editar-produto" value="editar">
                    </form>

                    <form method="POST" action="GerirProduto">
                        <input type="hidden" name="pk" value="<%= produto.getPkProduto() %>">
                        <input type="hidden" name="usuario" value="<%= request.getParameter( "usuario") %>">
                        <input type="submit" name="form-eliminar-produto" value="eliminar">
                    </form>
                </td>
            </tr>
            <%}%>
        </table>

        <script>
            function validarCampos() {
                var nome = document.getElementById("nome").value;
                var qtd = document.getElementById("quantidade").value;
                var preco = document.getElementById("preco").value;

                if (nome.trim() === "")
                    return false;
                
                if(qtd.trim() === "")
                    return false;
                
                if(preco.trim() === "")
                    return false;

                return true;
            }
        </script>

    </body>
</html>
