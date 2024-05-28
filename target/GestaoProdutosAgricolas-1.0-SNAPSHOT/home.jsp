<%-- 
    Document   : home
    Created on : 26/05/2024, 13:27:29
    Author     : valdemar
--%>

<%@page import="DAO.ProdutoDAO"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ProdutoDAO produtoDao = new ProdutoDAO();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css">
        <title>Produtos Agricolas</title>
    </head>
    <body>
        <h3>Usuario: <% if(request.getParameter("usuario") != null) {%> <%= request.getParameter("usuario") %> <%}%> <a href="index.jsp">sair</a></h3>
        <h1>Produtos Agricolas</h1>
        
        <div>
            <form method="POST" action="CadastroProduto" onsubmit="return validarCampos()">
                
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" placeholder="Nome do Produtp" required>
                <br>
                <label for="qtd">Quantidade</label>
                <input type="number" id="qtd" name="quantidade" placeholder="Quantidade" required>
                <br>
                <label for="preco">Preço</label>
                <input type="number" id="preco" name="preco" placeholder="Preço Unidade" required> Kz
                <br><br>
                <input type="submit" value="Cadastrar Produto">
                
            </form>
        </div>
        
        <br><br>
        
        <table id="tabela-produtos">
            <tr>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Preço</th>
            </tr>
            <%
                for(Produto produto : produtoDao.listar())
                {
            %>
            <tr>
                <td><%= produto.getDescricao() %></td>
                <td><%= produto.getQuantidade() %></td>
                <td><%= produto.getPreco() %></td>
            </tr>
            <%}%>
        </table>
        
        <script>
            function validarCampos(){
                var nome = document.getElementById("nome").value;
                var qtd = document.getElementById("quantidade").value;
                var preco = document.getElementById("preco").value;
                
                if(nome.trim() === "" || qtd.trim() === "" || preco.trim() === "")
                    return false;
                
                return true;
            }
        </script>
        
    </body>
</html>
