/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tampvn.cart.CartObject;

/**
 *
 * @author Polaris
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        
        try {
           //1. Customers go to cart place 
            HttpSession session = request.getSession(false);
            if(session != null){
                //2. Customer takes cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if(cart != null){
                    //3. Customer takes items
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        //4. Get all selected items 
                        //lay chuoi String 
                        String[]  removedItems = request.getParameterValues("chkItem");
                        if(removedItems != null){
                            //5. Remove each item for cart
                            for(String item : removedItems){
                                cart.removeItemFromCart(item);
                            }//end traverse each item 
                            session.setAttribute("CART", cart);
                        }// end removedItems had choiced
                    }//end items have existed
                }//end if cart has existed
            }//session has existed
        }finally{
            //6. refresh = call view cart 
            String urlRewritting = "DispatchController"
                    + "?btnAction=View your cart";
            
            response.sendRedirect(urlRewritting);
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
