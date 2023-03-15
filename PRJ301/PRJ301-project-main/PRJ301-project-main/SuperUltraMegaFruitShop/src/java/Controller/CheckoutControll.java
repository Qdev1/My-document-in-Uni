/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cartt;
import Model.OrderDao;
import Model.OrderDetailDao;
import Model.Orders;
import Model.Shipng;
import Model.ShipngDao;
import Model.User;
import Model.UserDAO;
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
public class CheckoutControll extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Map<Integer, Cartt> carts = (Map<Integer, Cartt>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }
            //tinh tong tien
            double totalMoney = 0;
            for (Map.Entry<Integer, Cartt> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cartt cart = entry.getValue();

                totalMoney += cart.getQuantity() * cart.getProduct().getP_price();

            }
            request.setAttribute("totalMoney", totalMoney);
            User u = new UserDAO().getUserInfo(String.valueOf(session.getAttribute("account")));
            request.setAttribute("name", u.getFullname());
            request.setAttribute("email", u.getEmail());
            request.setAttribute("address", u.getCity());
            request.setAttribute("phone", u.getPhoneNumber());
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        //processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User u = new UserDAO().getUserInfo(String.valueOf(session.getAttribute("account")));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("bill");

        //luu vao DB
        //luu shippng
        Shipng shipng = new Shipng(name, email, phone, address);
        int shipingId = new ShipngDao().createReturnId(shipng);
        //luu order
        Map<Integer, Cartt> carts = (Map<Integer, Cartt>) session.getAttribute("carts");
        if (carts == null) {
            carts = new LinkedHashMap<>();
        }
        //tinh tong tien
        double totalPrice = 0;
        for (Map.Entry<Integer, Cartt> entry : carts.entrySet()) {
            Integer productId = entry.getKey();
            Cartt cart = entry.getValue();

            totalPrice += cart.getQuantity() * cart.getProduct().getP_price();

        }

        Orders od = new Orders(Integer.parseInt(u.getUserID()), totalPrice, shipingId, note);
        int OrderId = new OrderDao().createReturnID(od);

        //luu orderdetail
        new OrderDetailDao().saveCart(OrderId, carts);

        session.removeAttribute("carts");
        response.sendRedirect("grateful.jsp");
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
