/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.UserDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;
import jakarta.websocket.Session;
import java.util.Enumeration;

/**
 *
 * @author Nezio
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String account = request.getParameter("account");
            String pass = request.getParameter("pass");
            HttpSession session = request.getSession();
            UserDAO u = new UserDAO();
            if (request.getParameter("forgotPass") != null) {
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            }
            if (request.getParameter("login") != null) {
                if (account.isEmpty() || pass.isEmpty()) {
                    request.setAttribute("returnMsg", "Fill all the form please");
                    request.setAttribute("retUsername", account);
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
                if (u.checkCredential(account, pass)) {
                    //System.out.println(u.checkRole(account));
                    session.setAttribute("account", account);
                    switch (u.checkRole(account)) {

                        case "admin":
                            //request.setAttribute(pass, u);
                            response.sendRedirect("AdminIndex.jsp");
                            break;

                        case "Customer":
                            request.getRequestDispatcher("Home.jsp").forward(request, response);
                            break;
                        case "shipper":
                            request.getRequestDispatcher("TransportCenter.jsp").forward(request, response);
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {
                    if (u.checkIfAccountExist(account)) {
                        
                        request.setAttribute("returnMsg", "Wrong Password");
                    } else {
                        System.out.println(u.checkIfAccountExist(account));
                        request.setAttribute("returnMsg", "Account not exist.");
                    }
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
