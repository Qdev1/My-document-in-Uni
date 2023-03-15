/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Categorise;
import Model.CategoryDao;
import Model.ProductDAO;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author duonn
 */
public class ShopControll extends HttpServlet {

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
        final int PAGE_SIZE = 6;
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CategoryDao cateDao = new CategoryDao();
            List<Categorise> listCate = cateDao.getAllCate();
            ProductDAO productDAO = new ProductDAO();

            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            int totalProducts = productDAO.getTotalProducts();
            int totalPages = totalProducts / PAGE_SIZE;
            if (totalPages % PAGE_SIZE != 0) {
                totalPages += 1;
            }
            List<Products> listAllProducts = productDAO.getProductsWithPagging(page, PAGE_SIZE);

            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listAllProducts", listAllProducts);
            request.setAttribute("Categorise", listCate);
            session.setAttribute("UrlHistory", "shop");
            request.getRequestDispatcher("shop.jsp").forward(request, response);
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
