/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tampvn.utils.DBHelper;

/**
 *
 * @author Polaris
 */
public class RegistrationDAO implements Serializable {

    public boolean CheckLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //các bước để Access DB
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {

                //2. Create SQL statement String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //các bước để Access DB
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {

                //2. Create SQL statement String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process
                //search tra ve nhieu ket qua nen dung while 
                while (rs.next()) {
                    //get field/columm
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //create DTO instance 
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastName, role);
                    //add to account list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }

                    //account is available
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //các bước để Access DB
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {

                //2. Create SQL statement String
                String sql = "Delete From Registration "
                        + "Where username = ?";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. Execute Query
                int row = stm.executeUpdate();

                //5. Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
//        try {
        //1. connect DB 
        con = DBHelper.makeConnection();

        //2. Create SQL Statement string 
        String sql = "Update Registration "
                + "SET password = ?, isAdmin = ? "
                + "Where username = ?";
        //3. Create statement to set sql
        stm = con.prepareStatement(sql);
        stm.setString(1, password);
        stm.setBoolean(2, isAdmin);
        stm.setString(3, username);

        int effectedRow = stm.executeUpdate();

        if (effectedRow > 0) {
            return true;
        }
//        }   finally {
//
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
        return false;
    }
}
