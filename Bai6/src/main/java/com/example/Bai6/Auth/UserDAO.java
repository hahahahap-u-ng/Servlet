package com.example.Bai6.Auth;

import com.example.Bai6.context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static User validate(String name, String pass) {
        boolean status = false;
        User user = new User();
        try {

            Connection con  = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = con.prepareStatement(
                    "select * from user where name=? and pass=?");
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
}
