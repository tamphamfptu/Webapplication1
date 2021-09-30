/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tampvn.registration.RegistrationDAO;
import tampvn.registration.RegistrationDTO;
import tampvn.registration.RegistrationInsertErrror;

/**
 *
 * @author Polaris
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    
    private final String LOGIN_PAGE ="login.html";
    private final String ERROR_PAGE = "createNewAccount.jsp";
    
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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");
       
            RegistrationInsertErrror errors = new RegistrationInsertErrror();
            boolean foundErr = false;
            String url = ERROR_PAGE;
            
        try {
            //1.Check all user errors 
            if(username.trim().length() < 6 || username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 20 characters");
                 System.out.println("username: " + foundErr);
            }
             if(password.trim().length() < 6 || password.trim().length() > 20){
                foundErr = true;
                errors.setPasswordLengthErr("Password is required from 6 to 20 characters");
                 System.out.println("password" + foundErr);
            }else if (!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
                 System.out.println("confirm" + foundErr);
            }
            if(lastname.trim().length() < 2 || lastname.trim().length() > 50){
                foundErr = true;
                errors.setlastNameLengthErr("Lastname is required from 2 to 50 characters");
                 System.out.println("lastname" + foundErr);
            }
            System.out.println(foundErr);
            System.out.println(url);
            if(foundErr){
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                //.Insert to DB
                //1 Create DTO
                RegistrationDTO dto = 
                        new RegistrationDTO(username, password, lastname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                
                System.out.println("Result" + result);
                
                if(result){
                    //. transfer to Login page
                    url = LOGIN_PAGE;
                }//end account is created
            }
        }catch(SQLException se){
            log("CreateNewAccountServlet _ SQL" + se.getMessage());
        }catch(NamingException se){
            log("CreateNewAccountServlet _ Naming" + se.getMessage());
        }
        finally {
            System.out.println(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
