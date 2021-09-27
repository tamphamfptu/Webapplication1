/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Polaris
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

   private final String LOGIN_PAGE = "login.html";
   private final String LOGIN_CONTROLLER = "LoginServlet";
   private final String SEARCH_LASTNAME_CONTROLLER = "SearchServlet";
   private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
   private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
   private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
   private final String ADD_ITEM_TO_CART_CONTROLLER = "ProcessRequestServlet";
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //ng dung da nhan nut gi
        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;
        
        try{
           if(button == null){
              url = PROCESS_REQUEST_CONTROLLER;  
           }else if(button.equals("Login")){
               url = LOGIN_CONTROLLER;
           }else if(button.equals("Search")){
               url = SEARCH_LASTNAME_CONTROLLER;
           }else if(button.equals("Delete")){
               url = DELETE_ACCOUNT_CONTROLLER;
           }else if(button.endsWith("Update")){
                url = UPDATE_ACCOUNT_CONTROLLER;
           }else if (button.equals("Add Item to your cart")){
               url = ADD_ITEM_TO_CART_CONTROLLER;
           }
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
