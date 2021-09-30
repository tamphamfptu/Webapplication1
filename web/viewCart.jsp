<%-- 
    Document   : viewCart
    Created on : Sep 27, 2021, 9:46:23 PM
    Author     : Polaris
--%>

<%@page import="java.util.Map"%>
<%@page import="tampvn.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart include </h1>
        <%
            //1.Customer goes to cart place 
            if (session != null) {
                //2.Customer takes cart 
                CartObject cart = (CartObject) session.getAttribute("CART");
                //check cart is existed 
                if (cart != null) {
                    //3. Customer takes items from cart 
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4.show items 
        %>
        
        <form action="DispatchController">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <!--remove item-->
                    <td>
                        <input type ="checkbox" name ="chkItem" 
                               value="<%= key%>"/>
                    </td>
                </tr> 
                <%
                    }//end get item from items 
                %>
                <tr>
                    <td colspan="3">
                        <a href="onlineShopping.html">Add more Items to cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Selected Items" name="btnAction" />
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
        <%
                        return;
                    }//items have values
                }//cart has existed 
            }//session has existed 
        %>
        <h2>
            No cart is existed
        </h2>
        
        <a href="onlineShopping.html">Back</a>
    </body>
</html>
