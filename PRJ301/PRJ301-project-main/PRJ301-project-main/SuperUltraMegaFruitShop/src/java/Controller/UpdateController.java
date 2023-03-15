/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.ProductDAO;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;

/**
 *
 * @author Nezio
 */
public class UpdateController extends HttpServlet {

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
            HttpSession session = request.getSession();
            UserDAO u = new UserDAO();
            ProductDAO p = new ProductDAO();
            if (request.getParameter("customer") != null) {
                String uID = request.getParameter("uID");
                String Username = request.getParameter("username");
                String Fullname = request.getParameter("fullname");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String city = request.getParameter("city");
                //String role = request.getParameter("role");

                if (uID == null || Username == null || Fullname == null || phone == null || email == null || city == null) {
                    request.setAttribute("ErrorGoesHere", "Fill the form plzzzz.");
                    request.getRequestDispatcher("Edit?id=1&mod=update&type=user").forward(request, response);
                } else {
                    if (session.getAttribute("account").equals("admin")) {
                        u.updateUser(uID, Username, Fullname, phone, email, city, "Customer");
                        response.sendRedirect("adminController?customer=1");
                    }
                    else {
                        u.updateUser(uID, Username, Fullname, phone, email, city, "Customer");
                        response.sendRedirect("UserDetail?account="+session.getAttribute("account"));
                    }
                }

            } else {
                String p_id = request.getParameter("pID");
                String pname = request.getParameter("pname");
                String qty = request.getParameter("qty");
                String cate = request.getParameter("cate");
                String date_manu = String.valueOf(Date.valueOf(request.getParameter("date_manu")));
                String Date_Exp = String.valueOf(Date.valueOf(request.getParameter("exp")));
                String price = request.getParameter("price");
                String img = request.getParameter("img");
                img = img.substring(1, img.length() - 1);
                p.updateProduct(p_id, pname, qty, cate, date_manu, Date_Exp, price, img);
                response.sendRedirect("adminController?product=1");
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
