/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cartt;
import Model.ProductDAO;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author duonn
 */
public class CartControll extends HttpServlet {

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
            HttpSession session = request.getSession();
            int productId = Integer.parseInt(request.getParameter("productId"));
            if (session.getAttribute("account") != null) {
                Map<Integer, Cartt> carts = (Map<Integer, Cartt>) session.getAttribute("carts");
                // if sesion null => create new
                if (carts == null) {
                    carts = new LinkedHashMap<>();
                }
                if (carts.containsKey(productId)) {//sản phẩm đã có trên giỏ hàng
                    int oldQuantity = carts.get(productId).getQuantity();
                    carts.get(productId).setQuantity(oldQuantity + 1);
                } else {// san pham chua co trong gio hang
                    Products product = new ProductDAO().getProductById(productId);
                    carts.put(productId, Cartt.builder().product(product).quantity(1).build());
                }
                //luu cart len ss 
                session.setAttribute("carts", carts);
                response.sendRedirect((String) session.getAttribute("UrlHistory"));
            }
            else {
                response.sendRedirect("Login.jsp");
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
