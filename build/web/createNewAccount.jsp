<%-- 
    Document   : createNewAccount
    Created on : Sep 29, 2021, 10:25:37 PM
    Author     : Polaris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
        <h1>Create Account</h1>
        <form action="DispatchController" method="POST">
            Username <input type="text" name="txtUsername" value="" />(6 -20 chars)<br>
            Password <input type="password" name="txtPassword" value="" />(6 -30 chars)<br>
            Confirm <input type="password" name="txtConfirm" value="" /><br>
            Lastname <input type="text" name="txtLastname" value="" />(2 to 50 chars)<br>
            <input type="submit" value="Create New Account" name="btnAction"/>
            <input type="reset" value="Reset" />
        </form>
</html>
