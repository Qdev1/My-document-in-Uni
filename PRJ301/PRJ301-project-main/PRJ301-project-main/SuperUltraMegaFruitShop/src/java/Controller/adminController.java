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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nezio
 */
public class adminController extends HttpServlet {

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
        final int PAGE_SIZE = 10;
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("customer") != null) {
                UserDAO u = new UserDAO();
                request.setAttribute("table_creator", "<tr>\n"
                        + "                <th>\n"
                        + "                    UserID\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Username\n"
                        + "                </th>\n"
                        + "                <th>Password</th>\n"
                        + "                <th>\n"
                        + "                    Fullname\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    PhoneNumber\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Email\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Address\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Role\n"
                        + "                </th>\n"
                        + "            </tr>");

                ArrayList<User> list = u.getAll();

                request.setAttribute("list", list);

            }

            if (request.getParameter("product") != null) {
                ProductDAO p = new ProductDAO();
                request.setAttribute("table_creator", "<tr>\n"
                        + "                <th>\n"
                        + "                    ID\n"
                        + "                </th>\n"
                        + "                <th>Product_Name</th>\n"
                        + "                <th>\n"
                        + "                    Quantity\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Category_ID\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Date_manufacture\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    Date_expiration\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    List_Price\n"
                        + "                </th>\n"
                        + "                <th>\n"
                        + "                    img_Link\n"
                        + "                </th>\n"
                        + "            </tr>");
                List<Products> list2 = p.getAllProducts();
                request.setAttribute("list2", list2);
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int totalProducts = p.getTotalProducts();
                int totalPages = totalProducts / PAGE_SIZE;
                if (totalPages % PAGE_SIZE != 0) {
                    totalPages += 1;
                }
                List<Products> listAllProducts = p.getProductsWithPagging(page, PAGE_SIZE);

                request.setAttribute("page", page);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("list2", listAllProducts);
            }
            request.getRequestDispatcher("AdminIndex.jsp").forward(request, response);
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
