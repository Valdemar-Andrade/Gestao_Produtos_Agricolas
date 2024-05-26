/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DAO.LoginDAO;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Login;
import model.Usuario;

/**
 *
 * @author valdemar
 */
@WebServlet(name = "CadastroUsuario", urlPatterns = { "/CadastroUsuario" })
public class CadastroUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        if ( request.getServletPath().equals( "/CadastroUsuario" ) ) {

            String usuario = request.getParameter( "usuario" );
            String nome = request.getParameter( "nome" );
            String email = request.getParameter( "email" );
            String senha = request.getParameter( "senha" );

            UsuarioDAO usuarioDao = new UsuarioDAO();
            LoginDAO loginDao = new LoginDAO();

            Usuario novoUsuario = new Usuario( 0, nome, email );
            List<Usuario> usuarios;

            try {

                usuarioDao.adicionar( novoUsuario );
                usuarios = usuarioDao.listar();

                Login login = new Login( 0, usuario, senha, usuarios.get( usuarios.size() - 1 ).getPkUsuario() );
                loginDao.adicionar( login );
                
                response.sendRedirect( "index.jsp");

            }
            catch ( SQLException ex ) {
                ex.printStackTrace();
            }

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
