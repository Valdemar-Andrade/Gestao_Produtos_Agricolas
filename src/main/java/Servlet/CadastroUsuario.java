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

            List<Usuario> usuarios;
            List<Login> logins;
            
            int pkUsuario = 1, pkLogin = 1;

            try {
                
                usuarios = usuarioDao.listar();
                logins = loginDao.listar();
                
                if( usuarios.size() > 0 && logins.size() > 0){
                    
                    pkUsuario = usuarios.get( usuarios.size() - 1).getPkUsuario() + 1;
                    pkLogin = logins.get( logins.size() - 1 ).getPkLogin() + 1;
                    
                }
                
                Usuario novoUsuario = new Usuario( pkUsuario, nome, email );
                usuarioDao.adicionar( novoUsuario );
                
                Login login = new Login( pkLogin, usuario, senha, pkUsuario );
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
