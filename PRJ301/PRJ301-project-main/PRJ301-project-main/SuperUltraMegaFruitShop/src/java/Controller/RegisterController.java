/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.UserDAO;
import Utils.RandomCodeGen;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Utils.SendEmail;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Nezio
 */
public class RegisterController extends HttpServlet {

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
            if (request.getParameter("login") != null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
            String username = "";
            String email = "";
            String password = "";
            String re_enterPass = "";

            UserDAO u = new UserDAO();
            HttpSession session = request.getSession();

            if (request.getParameter("register") != null) {
                username = request.getParameter("username");
                email = request.getParameter("email");
                password = request.getParameter("password");
                re_enterPass = request.getParameter("reEnterPass");
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || re_enterPass.isEmpty()) {
                    request.setAttribute("returnMsg", "Fill all the form please");
                    request.setAttribute("retUsername", username);
                    request.setAttribute("retEmail", email);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }
                if (!password.equals(re_enterPass)) {
                    request.setAttribute("returnMsg", "Password and re-enter password don't match.");
                    request.setAttribute("retUsername", username);
                    request.setAttribute("retEmail", email);
                } else {
                    if (u.checkIfAccountExist(username)) {
                        request.setAttribute("returnMsg", "Account existed. Please log in");
                    } else if (u.checkIfAccountExist(email)) {
                        request.setAttribute("returnMsg", "Email existed. Please log in");
                    } else {
                        //u.saveCredential(username, email, password);

                        RandomCodeGen rcg = new RandomCodeGen();
                        String confCode = rcg.CodeGenerator();
                        try {
                            SendEmail.SendMail(email, "Confirm code: " + confCode);
                        } catch (Exception e) {
                            System.out.println("sendMail err: " + e.getMessage());
                        }

                        session.setAttribute("email", email);
                        session.setAttribute("confCode", confCode);
                        session.setAttribute("username", username);
                        session.setAttribute("password", password);
                        request.setAttribute("confEmail", email);
                        request.getRequestDispatcher("ConfirmEmail.jsp").forward(request, response);

                        return;

                        //request.setAttribute("returnMsg", "Sign up successfully");
                    }
                }
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
            String fullname;
            String phone;
            String address;

            String role;

            if (request.getParameter("register2") != null) {
                fullname = request.getParameter("Fullname");
                phone = request.getParameter("Phone");
                address = request.getParameter("Address");

                //role = request.getParameter("select");
                if (!u.checkPhone(phone)) {
                    request.setAttribute("returnMsg", "Phone number error");
                    request.getRequestDispatcher("Register_2.jsp").forward(request, response);
                }

                u.saveCredential((String) session.getAttribute("username"), (String) session.getAttribute("password"), fullname, phone, (String) session.getAttribute("email"), address, "Customer");
                request.setAttribute("returnMsg", "Successfully. Please log in");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
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
