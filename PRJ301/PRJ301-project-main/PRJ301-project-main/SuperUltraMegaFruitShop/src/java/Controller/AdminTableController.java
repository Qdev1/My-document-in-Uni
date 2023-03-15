/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.ProductDAO;
import Model.Products;
import Model.User;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nezio
 */
public class AdminTableController extends HttpServlet {

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
            UserDAO u = new UserDAO();
            ProductDAO p = new ProductDAO();
            if (request.getParameter("mod").equals("delete")) {
                if (request.getParameter("type").equals("user")) {
                    u.deleteUser(request.getParameter("id"));
                    response.sendRedirect("adminController?customer=1");
                } else {
                    p.deleteProduct(request.getParameter("id"));
                    response.sendRedirect("adminController?product=1");
                }

            }
            if (request.getParameter("mod").equals("update")) {
                if (request.getParameter("type").equals("user")) {
                    User user = u.getUserById(request.getParameter("id"));
                    request.setAttribute("EditGoesHere", "<div class=\"container\">\n"
                            + "  <form action=\"updateGoesBrrr\">\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"uname\">UserID</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input readonly='true' type=\"text\" id=\"uID\" name=\"uID\" value='" + user.getUserID() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"uname\">Username</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input type=\"text\" id=\"uname\" name=\"username\" value='" + user.getAccount() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"fname\">Fullname</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input type=\"text\" id=\"fname\" name=\"fullname\" value='" + user.getFullname() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"fname\">Phone number</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input type=\"text\" id=\"fname\" name=\"phone\" value='" + user.getPhoneNumber() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"fname\">Email</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input type=\"text\" id=\"fname\" name=\"email\" value='" + user.getEmail() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"fname\">City</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input type=\"text\" id=\"fname\" name=\"city\" value='" + user.getCity() + "'>\n"
                            + "    </div>\n"
                            + "  </div>\n"
                            + "<div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"role\">Role</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <select id=\"role\" name=\"role\">\n"
                            + "        <option value=\"Customer\">Customer</option>\n"
                            + "        <option value=\"Shipper\">Shipper</option>\n"
                            + "      </select>\n"
                            + "    </div>\n"
                            + "  </div>"
                            + "  <br>\n"
                            + "  <div class=\"row\">\n"
                            + "    <input type=\"submit\" name='customer' value=\"Submit\">\n"
                            + "  </div>"
                            + "  </form>");
                    request.getRequestDispatcher("adminController").forward(request, response);
                } else {
                    Products prod = p.getProductById(Integer.parseInt(request.getParameter("id")));
                    System.out.println(prod.toString());
                    request.setAttribute("EditGoesHere", "<div class=\"container\">\n"
                            + "  <form action=\"updateGoesBrrr\">\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"uname\">ProductID</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input readonly='true' type=\"text\" id=\"pID\" name=\"pID\" value='" + prod.getP_id() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"pname\">Product name</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"text\" id=\"pname\" name=\"pname\" value='" + prod.getP_name() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"qty\">Quantity</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"text\" id=\"qty\" name=\"qty\" value='" + prod.getP_quantity() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"cate\">Category_id</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"text\" id=\"cate\" name=\"cate\" value='" + prod.getC_id() + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"date_manu\">Date_manu</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"date\" id=\"date_manu\" name=\"date_manu\" value='" + String.valueOf(prod.getDate_manufacture()) + "'>\n"
                            + "    </div></div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"exp\">Date_Exp</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"date\" id=\"exp\" name=\"exp\" value='" + String.valueOf(prod.getDate_expriration()) + "'>\n"
                            + "    </div>\n"
                            + "  </div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"price\">Price</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <input required='true' type=\"number\" id=\"price\" name=\"price\" value='" + prod.getP_price() + "'>\n"
                            + "    </div>\n"
                            + "    </div>\n"
                            + "  <div class=\"row\">\n"
                            + "    <div class=\"col-25\">\n"
                            + "      <label for=\"subject\">img_link</label>\n"
                            + "    </div>\n"
                            + "    <div class=\"col-75\">\n"
                            + "      <textarea id=\"img\" name=\"img\" style=\"height:100px\">'" + prod.getP_img() + "'</textarea>\n"
                            + "    </div>\n"
                            + "  </div>"
                            + "  <br>\n"
                            + "  <div class=\"row\">\n"
                            + "    <input type=\"submit\" value=\"Submit\">\n"
                            + "  </div>"
                            + "  </form>");
                    request.getRequestDispatcher("adminController").forward(request, response);
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
