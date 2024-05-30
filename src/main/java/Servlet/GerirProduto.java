/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DAO.ProdutoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author valdemar
 */
@WebServlet(name = "GerirProduto", urlPatterns = { "/GerirProduto" })
public class GerirProduto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        if ( request.getServletPath().equals( "/GerirProduto" ) ) {

            ProdutoDAO produtoDao = new ProdutoDAO();

            if ( request.getParameter( "form-cadastro-produto" ) != null ) {

                if ( request.getParameter( "form-cadastro-produto" ).equalsIgnoreCase( "Cadastrar Produto" ) ) {

                    String nome = request.getParameter( "nome" );
                    String quantidade = request.getParameter( "quantidade" );
                    String preco = request.getParameter( "preco" );

                    List<Produto> produtos;
                    int pk = 1;

                    try {

                        int quantidadeInteiro = Integer.parseInt( quantidade );
                        double precoDouble = Double.parseDouble( preco );

                        produtos = produtoDao.listar();

                        if ( produtos.size() > 0 ) {

                            pk = produtos.get( produtos.size() - 1 ).getPkProduto() + 1;

                        }

                        Produto produto = new Produto( pk, nome, quantidadeInteiro, precoDouble );
                        produtoDao.adicionar( produto );

                    }
                    catch ( NumberFormatException | SQLException ex ) {
                        System.out.println( "Erro ao converter valores do Produto!" );
                    }
                }

            }

            if ( request.getParameter( "form-editar-produto" ) != null ) {

                if ( request.getParameter( "form-editar-produto" ).equalsIgnoreCase( "editar" ) ) {

                    String pk = request.getParameter( "pk" );
                    String novo = request.getParameter( "novo" );

                    try {

                        int pkProduto = Integer.parseInt( pk );
                        Produto produtoAtualizado;

                        produtoAtualizado = produtoDao.listar().stream().filter( produto -> produto.getPkProduto() == pkProduto ).findAny().get();
                        produtoAtualizado.setDescricao( novo );

                        produtoDao.actualizar( pkProduto, produtoAtualizado );

                    }
                    catch ( SQLException | NumberFormatException ex ) {
                    }

                }

            }

            if ( request.getParameter( "form-eliminar-produto" ) != null ) {

                if ( request.getParameter( "form-eliminar-produto" ).equalsIgnoreCase( "eliminar" ) ) {

                    String pk = request.getParameter( "pk" );

                    try {

                        int pkProduto = Integer.parseInt( pk );
                        produtoDao.remover( pkProduto );

                    }
                    catch ( SQLException | NumberFormatException ex ) {
                    }

                }

            }

            response.sendRedirect( "home.jsp" );
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo () {
        return "Short description";
    }// </editor-fold>

}
