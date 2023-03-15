/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Utils.RandomCodeGen;
import Utils.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Nezio
 */
public class ConfirmController extends HttpServlet {

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
            String confCode = request.getParameter("confCode");
            HttpSession session = request.getSession();
            if (request.getParameter("confirm") != null) {
                if (confCode.equals(session.getAttribute("confCode"))) {
                    request.getRequestDispatcher("Register_2.jsp").forward(request, response);
                } else {
                    request.setAttribute("returnMsg", "Wrong Confirm code");
                    request.getRequestDispatcher("ConfirmEmail.jsp").forward(request, response);
                }
            }
            if (request.getParameter("retry")!=null) {
                RandomCodeGen rcg = new RandomCodeGen();
                        String confCode2 = rcg.CodeGenerator();
                        System.out.println((String)session.getAttribute("email"));
                        try {
                            SendEmail.SendMail((String)session.getAttribute("email"), "Confirm code: "+confCode2);
                        } catch (Exception e) {
                            System.out.println("sendMail err: " + e.getMessage());
                        }
                        session.setAttribute("confCode", confCode);
                request.getRequestDispatcher("ConfirmEmail.jsp").forward(request, response);
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
